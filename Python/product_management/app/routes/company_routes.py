from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from typing import List
from app.core.db import get_db
from app.schemas.company_schema import CompanyCreate, CompanyOut
from app.crud import company_crud

router = APIRouter(prefix="/companies", tags=["companies"])

@router.post("/", response_model=CompanyOut, status_code=201)
def create_company_endpoint(payload: CompanyCreate, db: Session = Depends(get_db)):
    return company_crud.create_company(db, payload)

@router.get("/", response_model=List[CompanyOut])
def list_companies(db: Session = Depends(get_db)):
    return company_crud.get_companies(db)

@router.get("/{company_id}", response_model=CompanyOut)
def get_company(company_id: int, db: Session = Depends(get_db)):
    return company_crud.get_company(db, company_id)

@router.put("/{company_id}", response_model=CompanyOut)
def update_company(company_id: int, payload: CompanyCreate, db: Session = Depends(get_db)):
    return company_crud.update_company(db, company_id, payload)

@router.delete("/{company_id}")
def delete_company(company_id: int, db: Session = Depends(get_db)):
    return company_crud.delete_company(db, company_id)
