# app/routes/product_routes.py
from fastapi import APIRouter, Depends, Query
from sqlalchemy.orm import Session
from typing import List, Optional
from app.core.db import get_db
from app.schemas.product_schema import ProductCreate, ProductOut, ProductUpdate
from app.crud import product_crud

router = APIRouter(prefix="/products", tags=["products"])

@router.post("/", response_model=ProductOut, status_code=201)
def create_product(payload: ProductCreate, db: Session = Depends(get_db)):
    return product_crud.create_product(db, payload)

@router.get("/", response_model=List[ProductOut])
def list_products(skip: int = 0, limit: int = 10, db: Session = Depends(get_db)):
    return product_crud.list_products(db, skip, limit)

@router.get("/{product_id}", response_model=ProductOut)
def get_product(product_id: int, db: Session = Depends(get_db)):
    return product_crud.get_product(db, product_id)

@router.put("/{product_id}", response_model=ProductOut)
def update_product(product_id: int, payload: ProductUpdate, db: Session = Depends(get_db)):
    return product_crud.update_product(db, product_id, payload)

@router.delete("/{product_id}")
def delete_product(product_id: int, db: Session = Depends(get_db)):
    return product_crud.delete_product(db, product_id)

@router.get("/find/search", response_model=List[ProductOut])
def search_products(
    q: Optional[str] = Query(None, description="search by name, category or price"),
    company_id: Optional[int] = Query(None),
    skip: int = Query(0, ge=0),
    limit: int = Query(10, ge=1, le=100),
    db: Session = Depends(get_db)
):
    items, total = product_crud.search_products(db, q, company_id, skip, limit)
    # You can return metadata in headers or wrap results; for simplicity we return list
    return items
