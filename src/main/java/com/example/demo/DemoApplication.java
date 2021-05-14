package com.example.demo;

import com.example.demo.models.Bill;
import com.example.demo.repositories.BillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

   @Bean
    public CommandLineRunner billMockData(BillRepository billRepository){

        return args -> {
            Bill oneHundred = new Bill(100);
            Bill oneHundred1 = new Bill(100);
            Bill oneHundred2 = new Bill(100);
            Bill oneHundred3 = new Bill(100);
            Bill oneHundred4 = new Bill(100);


            Bill fiveHundred = new Bill(500);
            Bill fiveHundred1 = new Bill(500);
            Bill fiveHundred2 = new Bill(500);


            Bill oneThousand = new Bill(1000);
            Bill oneThousand1 = new Bill(1000);


            billRepository.save(oneHundred);
            billRepository.save(oneHundred1);
            billRepository.save(oneHundred2);
            billRepository.save(oneHundred3);
            billRepository.save(oneHundred4);

            billRepository.save(fiveHundred);
            billRepository.save(fiveHundred1);
            billRepository.save(fiveHundred2);

            billRepository.save(oneThousand);
            billRepository.save(oneThousand1);

        };
   }

}
