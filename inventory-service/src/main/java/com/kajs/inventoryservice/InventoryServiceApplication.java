package com.kajs.inventoryservice;

import com.kajs.inventoryservice.entities.Product;
import com.kajs.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                            .id(UUID.randomUUID().toString()).name("Computer").price(3290).quantity(11)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString()).name("Printer").price(1299).quantity(8)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString()).name("Smart Phone").price(359).quantity(25)
                    .build());
            productRepository.findAll().forEach( product -> {
                System.out.println("***************************");
                System.out.println(product.getId());
                System.out.println(product.getName());
                System.out.println(product.getPrice());
                System.out.println(product.getQuantity());
                System.out.println("***************************");

            });
        };
    }

}
