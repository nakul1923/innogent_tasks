from fastapi import FastAPI
from app.routes import product_routes
from app.routes import company_routes
from app.routes import category_routes
from app.core.db import Base, engine
from app.models import company, product, category

Base.metadata.create_all(bind=engine)

app = FastAPI(title="Product Management API (MySQL + FastAPI)")

app.include_router(product_routes.router)
app.include_router(company_routes.router)
app.include_router(category_routes.router)