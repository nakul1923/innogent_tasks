# app/crud/product_crud.py
from sqlalchemy.orm import Session
from sqlalchemy import or_, outerjoin
from fastapi import HTTPException
from typing import List, Tuple, Optional
from app.models.product import Product
from app.models.category import Category
from app.schemas.product_schema import ProductCreate, ProductUpdate

def create_product(db: Session, payload: ProductCreate):
    # duplicate per company
    existing = db.query(Product).filter(
        Product.name == payload.name,
        Product.company_id == payload.company_id
    ).first()
    if existing:
        raise HTTPException(status_code=400, detail="Product with same name already exists for this company")
    obj = Product(**payload.dict())
    db.add(obj)
    db.commit()
    db.refresh(obj)
    return obj

def get_product(db: Session, product_id: int):
    p = db.query(Product).filter(Product.id == product_id).first()
    if not p:
        raise HTTPException(status_code=404, detail="Product not found")
    return p

def update_product(db: Session, product_id: int, payload: ProductUpdate):
    p = get_product(db, product_id)
    for k, v in payload.dict(exclude_unset=True).items():
        setattr(p, k, v)
    db.commit()
    db.refresh(p)
    return p

def delete_product(db: Session, product_id: int):
    p = get_product(db, product_id)
    db.delete(p)
    db.commit()
    return {"detail": "Product deleted"}

def list_products(db: Session, skip: int = 0, limit: int = 10) -> List[Product]:
    return db.query(Product).order_by(Product.id).offset(skip).limit(limit).all()

def search_products(db: Session, q: Optional[str], company_id: Optional[int], skip: int = 0, limit: int = 10) -> Tuple[List[Product], int]:
    query = db.query(Product).outerjoin(Category, Product.category_id == Category.id)
    if company_id:
        query = query.filter(Product.company_id == company_id)

    if q:
        q_str = f"%{q}%"
        # try numeric price
        price_val = None
        try:
            price_val = float(q)
        except Exception:
            price_val = None

        query = query.filter(
            or_(
                Product.name.ilike(q_str),
                Category.name.ilike(q_str),
                (Product.price == price_val) if price_val is not None else False
            )
        )

    total = query.count()
    items = query.order_by(Product.id).offset(skip).limit(limit).all()
    return items, total
