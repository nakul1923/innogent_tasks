# app/schemas/product_schema.py
from pydantic import BaseModel, Field
from typing import Optional
from app.schemas.company_schema import CompanyOut
from app.schemas.category_schema import CategoryOut

class ProductBase(BaseModel):
    name: str = Field(..., max_length=200)
    description: Optional[str] = None
    price: float
    stock: int
    location: Optional[str] = None
    company_id: int
    category_id: Optional[int] = None

class ProductCreate(ProductBase):
    pass

class ProductUpdate(BaseModel):
    name: Optional[str] = None
    description: Optional[str] = None
    price: Optional[float] = None
    stock: Optional[int] = None
    location: Optional[str] = None
    category_id: Optional[int] = None

class ProductOut(ProductBase):
    id: int
    company: Optional[CompanyOut] = None
    category: Optional[CategoryOut] = None
    class Config:
        orm_mode = True
