package com.kajs.billingservice;

import com.kajs.billingservice.dto.Customer;
import com.kajs.billingservice.dto.Product;
import com.kajs.billingservice.entities.Bill;
import com.kajs.billingservice.entities.ProductItem;
import com.kajs.billingservice.find.CustomerRestClient;
import com.kajs.billingservice.find.ProductRestClient;
import com.kajs.billingservice.repository.BillRepository;
import com.kajs.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient customerRestClient,
            ProductRestClient productRestClient
    ) {
        return args -> {
            Collection<Customer> customers = customerRestClient.getCustomers().getContent();
            Collection<Product> products = productRestClient.getProducts().getContent();

            customers.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .customerId(customer.getId())
                        .build();
                billRepository.save(bill);
                products.forEach(product -> {
                    ProductItem productItem = ProductItem.builder()
                            .bill(bill)
                            .productId(product.getId())
                            .quantity(1 + new Random().nextInt(10))
                            .unitPrice(product.getPrice())
                            .build();
                    productItemRepository.save(productItem);
                });
            });

            billRepository.findAll().forEach( bill -> {
                System.out.println("***************************");
                System.out.println(bill.getId());
                System.out.println("***************************");

            });
        };
    }

}
