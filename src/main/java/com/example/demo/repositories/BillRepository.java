package com.example.demo.repositories;

import com.example.demo.models.Bill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long> {
    Iterable<Bill> findByValue(int value);
}
