
from sqlalchemy.orm import Session
from fastapi import HTTPException
from app.models.category import Category
from app.schemas.category_schema import CategoryCreate

def create_category(db: Session, payload: CategoryCreate):
    existing = db.query(Category).filter(Category.name == payload.name).first()
    if existing:
        raise HTTPException(status_code=400, detail="Category already exists")
    obj = Category(**payload.dict())
    db.add(obj)
    db.commit()
    db.refresh(obj)
    return obj

def get_categories(db: Session):
    return db.query(Category).all()

def get_category(db: Session, category_id: int):
    c = db.query(Category).filter(Category.id == category_id).first()
    if not c:
        raise HTTPException(status_code=404, detail="Category not found")
    return c

def update_category(db: Session, category_id: int, payload: CategoryCreate):
    c = get_category(db, category_id)
    for k, v in payload.dict().items():
        setattr(c, k, v)
    db.commit()
    db.refresh(c)
    return c

def delete_category(db: Session, category_id: int):
    c = get_category(db, category_id)
    db.delete(c)
    db.commit()
    return {"detail": "Category deleted"}
