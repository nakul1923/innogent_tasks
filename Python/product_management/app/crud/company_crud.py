from sqlalchemy.orm import Session
from fastapi import HTTPException
from app.models.company import Company
from app.schemas.company_schema import CompanyCreate

def create_company(db: Session, payload: CompanyCreate):
    existing = db.query(Company).filter(Company.name == payload.name).first()
    if existing:
        raise HTTPException(status_code=400, detail="Company already exists")
    obj = Company(**payload.dict())
    db.add(obj)
    db.commit()
    db.refresh(obj)
    return obj

def get_companies(db: Session):
    return db.query(Company).all()

def get_company(db: Session, company_id: int):
    company = db.query(Company).filter(Company.id == company_id).first()
    if not company:
        raise HTTPException(status_code=404, detail="Company not found")
    return company

def update_company(db: Session, company_id: int, payload: CompanyCreate):
    company = get_company(db, company_id)
    for k, v in payload.dict().items():
        setattr(company, k, v)
    db.commit()
    db.refresh(company)
    return company

def delete_company(db: Session, company_id: int):
    company = get_company(db, company_id)
    db.delete(company)
    db.commit()
    return {"detail": "Company deleted"}
