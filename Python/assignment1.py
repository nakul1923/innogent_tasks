class Product :
   
    def __init__(self,name,stock,price,location,tags):
        self.name = name
        self.stock = stock
        self.price = price
        self.location = location
        self.tags = tags

    
    def __str__(self):
        return (f"{self.name} | Stock: {self.stock} | Price: ${self.price:.2f} | "
                f"Location: {self.location} | Tags: {', '.join(self.tags)}")
    
    def is_low_stock(self) :
        return self.stock < 5
    
    def apply_discount(self) :
        
        if "clearance" in self.tags :
            discounted_price = self.price - (self.price * 50 / 100)
            return discounted_price
        return None
    
class Inventory :

    def __init__(self):
        self.products = []

    def add_product(self,product) :
        self.products.append(product)
        print(f"product '{product.name}' added successfully")

    def list_product(self) :
        if not self.products :
            print("There is no product available")
            return
        print("---- All products----")
        for i,p in enumerate(self.products, start=1) :
            print(f"{i}.{p}")
        print()

    def low_stock_warning(self) :
        low_items = [p for p in self.products if p.is_low_stock()]

        if not low_items:
            print("no items has low stock")
            return
        print("Low stocks items are : ")
        for p in low_items:
            print(f"{p.name} (stock: {p.stock})")
        print()

    def update_stock(self,name,new_stock):
        if not self.products:
            print("There is no product available")
        for p in self.products:
            if p.name.lower() == name.lower():
                p.stock = new_stock
                print(f"stock updated for : {p.name}")
                return
            print("Product not found")

    def delete_product(self,name):
        for p in self.products:
            if p.name.lower() == name.lower() :
                self.products.remove(p)
                print(f"Product {name} deleted successfully")
                return
        print("Product not found")

    def total_value(self):
        total = sum(p.price * p.stock for p in self.products)
        print(f"Total products value : {total}")

    def apply_discount(self):
        discounted_items = []
        for p in self.products:
            if "clearance" in p.tags:
                new_price = p.apply_discount()
                discounted_items.append((p.name,p.price,new_price)) 
        
        if discounted_items:
            print("\n Discounted Products: ")
            for name, old_price, new_price in discounted_items:
                print(f"{name}: Old Price ₹{old_price} → New Price ₹{new_price}")
        else:
            print("No product found with this tag")
def main():

    inventory = Inventory()

    while(True):

        print("------------------------------Menu options press the given No. to perform specific task: ------------------------------\n\n")

        print("1.   List All Products \n2.   See low stocks producs \n3.   Add New Product \n4.   Update Stock of a Product \n5.   Delete a Product \n6.   See Total value of all products \n7.   Apply discount on clearance products \n8.   Exit")

        n =  int(input("\n Enter Your choice here :"))
        
        if n==1:
            inventory.list_product()

        elif n==2:
            inventory.low_stock_warning()
        
        elif n==3:
            print("For adding a product, provide the given details: ")
            name = input("Enter Name of the Product: ")
            stock = int(input("Enter stock of the Product: "))
            price = int(input("Enter Price of the Product: "))
            location = input("Enter Location of the Product: ")
            tags = set(input("Enter tags of the Product(Comma separated): ").split(","))

            product = Product(name,stock,price,location,tags)
            inventory.add_product(product)


        elif n==4:
            name = input("Enter the product Name you want to update: ")

            new_stock = int(input("Enter the updated Stock: "))

            inventory.update_stock(name,new_stock)

    
        elif n==5:
            name = input("Enter the product Name you want to Delete: ")

            inventory.delete_product(name)

        elif n==6:
            inventory.total_value()

        elif n==7:
            
            inventory.apply_discount()

        elif n==8:
            break
main()