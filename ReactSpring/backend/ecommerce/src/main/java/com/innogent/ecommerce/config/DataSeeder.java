package com.innogent.ecommerce.config;

import com.innogent.ecommerce.entity.Product;
import com.innogent.ecommerce.entity.Promocode;
import com.innogent.ecommerce.repository.ProductRepository;
import com.innogent.ecommerce.repository.PromocodeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedProducts(ProductRepository productRepository, PromocodeRepository promocodeRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                List<Product> products = List.of(
                        new Product(null, "Wireless Earbuds", "Bluetooth 5.2 true wireless earbuds with noise cancellation", 2499.0, "Electronics", "https://m.media-amazon.com/images/I/61M1Uu4HjOL._SL1500_.jpg"),
                        new Product(null, "Smart Watch", "Fitness tracker with heart rate and SpO2 monitor", 3499.0, "Electronics", "https://m.media-amazon.com/images/I/61u1VALn6JL._SL1500_.jpg"),
                        new Product(null, "Men's Running Shoes", "Lightweight and comfortable running shoes for men", 1999.0, "Fashion", "https://m.media-amazon.com/images/I/71LQj4hA7+L._SL1500_.jpg"),
                        new Product(null, "Women's Handbag", "Stylish leather handbag with adjustable strap", 1799.0, "Fashion", "https://m.media-amazon.com/images/I/61uQZt0SsmL._SL1500_.jpg"),
                        new Product(null, "Mechanical Keyboard", "RGB backlit mechanical keyboard with blue switches", 4599.0, "Computers", "https://m.media-amazon.com/images/I/71kr3WAj1FL._SL1500_.jpg"),
                        new Product(null, "Gaming Mouse", "High precision gaming mouse with 6 programmable buttons", 1499.0, "Computers", "https://m.media-amazon.com/images/I/61OlnFNdYLL._SL1500_.jpg"),
                        new Product(null, "Ceramic Coffee Mug", "Premium ceramic coffee mug, 350ml capacity", 499.0, "Home & Kitchen", "https://m.media-amazon.com/images/I/61CukAnM8+L._SL1500_.jpg"),
                        new Product(null, "LED Desk Lamp", "Adjustable LED lamp with touch control and USB charging port", 899.0, "Home & Kitchen", "https://m.media-amazon.com/images/I/61i1V8gqEQL._SL1500_.jpg"),
                        new Product(null, "Bluetooth Speaker", "Portable wireless Bluetooth speaker with deep bass", 2299.0, "Electronics", "https://m.media-amazon.com/images/I/71sQ6QkUBeL._SL1500_.jpg"),
                        new Product(null, "Laptop Backpack", "Water-resistant backpack with USB charging port", 1299.0, "Accessories", "https://m.media-amazon.com/images/I/81aJ-R4E+yL._SL1500_.jpg")
                );

                productRepository.saveAll(products);
                System.out.println("✅ 10 sample products seeded successfully!");
            } else {
                System.out.println("ℹ️ Products already exist, skipping seeding...");
            }

            if (promocodeRepository.count() == 0) {
                List<Promocode> promocodes = List.of(
                        new Promocode(null, "WELCOME10", 10.0, LocalDateTime.now().plusDays(30), true),
                        new Promocode(null, "SAVE15", 15.0, LocalDateTime.now().plusDays(45), true),
                        new Promocode(null, "FREESHIP", 0.0, LocalDateTime.now().plusDays(60), true),
                        new Promocode(null, "NEWUSER25", 25.0, LocalDateTime.now().plusDays(15), false),
                        new Promocode(null, "DIWALI30", 30.0, LocalDateTime.now().plusDays(90), true)
                );

                promocodeRepository.saveAll(promocodes);
                System.out.println("✅ 5 Promocodes seeded successfully!");

            } else {

                System.out.println("ℹ️ Promocodes already exist, skipping seeding...");
            }
        };
    }

}