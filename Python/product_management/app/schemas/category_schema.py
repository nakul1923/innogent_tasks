# from pydantic import BaseModel

# class CategoryBase(BaseModel):
#     name: str

# class CategoryCreate(CategoryBase):
#     pass

# class CategoryResponse(CategoryBase):
#     id: int

#     class Config:
#         orm_mode = True



from pydantic import BaseModel
from typing import Optional

class CategoryBase(BaseModel):
    name: str
    description: Optional[str] = None

class CategoryCreate(CategoryBase):
    pass

class CategoryUpdate(CategoryBase):
    pass

class CategoryOut(CategoryBase):
    id: int
    class Config:
        orm_mode = True
