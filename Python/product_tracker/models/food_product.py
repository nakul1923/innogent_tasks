from .product import Product

class FoodProduct(Product):

    def __init__(self, name, stock, price, location, tags, expiry_date):
        super().__init__(name, stock, price, location, tags)
        self.expiry_date = expiry_date

    def describe(self):
        return f"{super().describe()} | expiry Date:{self.expiry_date}"