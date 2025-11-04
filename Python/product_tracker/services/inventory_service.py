from models.food_product import FoodProduct
from models.product import Product

LOW_STOCK = 5

class InventoryService:

    def __init__(self):
        self.products = []

    def add_product(self,product):
        self.products.append(product)
        print(f"\nProduct {product.name} added successfully")

    def list_product(self):
        if not self.products:
            print("\n No product available")
        for p in self.products:
            print(p.describe())
        
    def low_stock_products(self):
        if not self.products:
            print("\n No products available to check stocks")
            return
        found = False
        for p in self.products:
            if p.stock <= LOW_STOCK:
                print(f"{p.name} | Stock: {p.stock}")
                found = True
            if not found:
                print("All product have sufficient stocks")
    
    def update_stock(self,name,new_stock):
        for p in self.products:
            if name.lower() == p.name.lower():
                p.stock = new_stock
                print(f"\n{p.name} stock updated successfully")
                return
        print("\nNo product found with this name")
        
    def delete_product(self,name):
        for p in self.products:
            if name.lower() == p.name.lower():
                self.products.remove(p)
                print(f"product {p.name} remove successfully")
                return
        print("No product found with this name")
    
    def total_value(self):
        total = 0
        for p in self.products:
            total += p.value()
        print(f"\nTotal value of all product is : {total}")

    def apply_discount(self):
        discounted_items = []
        for p in self.products:
            if "clearance" in p.tags:
                new_price = p.apply_discount()
                discounted_items.append((p.name,p.price,new_price))
        if discounted_items:
            print("\n Disounted products")
            for name,old_price,new_price in discounted_items:
                print(f"{name} | old Price: {old_price} | New Price: {new_price}")
        else:
            print("\n No products are eligible for new products")