from pydantic import BaseModel
from typing import Optional

class CompanyBase(BaseModel):
    name: str
    location: Optional[str] = None

class CompanyCreate(CompanyBase):
    pass

class CompanyUpdate(CompanyBase):
    pass

class CompanyOut(CompanyBase):
    id: int
    class Config:
        orm_mode = True