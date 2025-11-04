from models.food_product import FoodProduct
from models.product import Product
from services.inventory_service import InventoryService

def main():

    inventory = InventoryService()

    while(True):

        print("------------------------------Menu options press the given No. to perform specific task: ------------------------------\n\n")

        print("1.   List All Products \n2.   See low stocks producs \n3.   Add New Product \n4.   Update Stock of a Product \n5.   Delete a Product \n6.   See Total value of all products \n7.   Apply discount on clearance products \n8.   Exit")

        n =  int(input("\n Enter Your choice here :"))
        
        if n==1:
            inventory.list_product()

        elif n==2:
            inventory.low_stock_products()
        
        elif n==3:
            print("For adding a product, provide the given details: ")
            name = input("Enter Name of the Product: ")
            stock = int(input("Enter stock of the Product: "))
            price = int(input("Enter Price of the Product: "))
            location = input("Enter Location of the Product: ")
            tags = set(input("Enter tags of the Product(Comma separated): ").split(","))

            ch = input("Products is Food product - Y/N : ").lower()

            if ch=="y":
                expiry_date = input("\nEnter Expiry date: ")
                product = FoodProduct(name,stock,price,location,tags,expiry_date)

            else:
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