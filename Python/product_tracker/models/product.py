class Product:
    def __init__(self,name,stock,price,location,tags):
        self.name = name
        self.stock = stock
        self.price = price
        self.location = location
        self.tags = tags
    
    def value(self):
        return self.price * self.stock
    
    def describe(self):
        return f"name :{self.name} | stock :{self.stock} | price :{self.price} | location:{self.location} | tags:{self.tags}"

    def apply_discount(self):
        return self.price - (self.price * 50 /100)