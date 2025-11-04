# app/routes/category_routes.py
from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from typing import List
from app.core.db import get_db
from app.schemas.category_schema import CategoryCreate, CategoryOut
from app.crud import category_crud

router = APIRouter(prefix="/categories", tags=["categories"])

@router.post("/", response_model=CategoryOut, status_code=201)
def create_category(payload: CategoryCreate, db: Session = Depends(get_db)):
    return category_crud.create_category(db, payload)

@router.get("/", response_model=List[CategoryOut])
def list_categories(db: Session = Depends(get_db)):
    return category_crud.get_categories(db)

@router.get("/{category_id}", response_model=CategoryOut)
def get_category(category_id: int, db: Session = Depends(get_db)):
    return category_crud.get_category(db, category_id)

@router.put("/{category_id}", response_model=CategoryOut)
def update_category(category_id: int, payload: CategoryCreate, db: Session = Depends(get_db)):
    return category_crud.update_category(db, category_id, payload)

@router.delete("/{category_id}")
def delete_category(category_id: int, db: Session = Depends(get_db)):
    return category_crud.delete_category(db, category_id)
