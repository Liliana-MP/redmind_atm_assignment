package com.example.demo.controllers;

import com.example.demo.models.Bill;
import com.example.demo.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bills")
public class BillController {

    private int calculate;

    @Autowired
    private BillRepository billRepository;

    @GetMapping(path = "/")
    public String greeting() {
        return "Redmind - Backend Developer Assignment";
    }

    @GetMapping(path = "/all")
    public Iterable<Bill> getAllBills() {
        return billRepository.findAll();
    }


    @GetMapping(path = "/{value}")
    public Iterable<Bill> getByValue(@PathVariable int value) {
        return billRepository.findByValue(value);
    }


    @PostMapping(path = "/withdraw/{value}")
    public ResponseEntity withdraw(@PathVariable int value) {


        List<Bill> billsToWithdraw = new ArrayList<>();
        List<Bill> dbCopy = (List<Bill>) billRepository.findAll();
        calculate = value;

        try {
            while (calculate != 0) {

                if (calculate >= 1000 && searchBillList(1000, dbCopy)) {
                    billTransfer(1000, billsToWithdraw, dbCopy);

                } else if (calculate >= 500 && searchBillList(500, dbCopy)) {
                    billTransfer(500, billsToWithdraw, dbCopy);

                } else if (calculate >= 100 && searchBillList(100, dbCopy)) {
                    billTransfer(100, billsToWithdraw, dbCopy);

                }
                else {
                    return new ResponseEntity<>("Not enough bills", HttpStatus.BAD_REQUEST);
                }
            }

            deleteFromDB(billsToWithdraw);

            return ResponseEntity.ok(billsToWithdraw);

        }  catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }

    }

    public void deleteFromDB(List<Bill> billsToWithdraw){
        billsToWithdraw.forEach((bill) -> billRepository.delete(bill));
    }

    public Boolean searchBillList(int valueToCheck, List<Bill> tempList) {
        return tempList.stream().anyMatch((bill -> bill.getValue() == valueToCheck));
    }

    public void billTransfer(int value, List<Bill> billsToWithdraw, List<Bill> tempList){
        Bill bill = tempList.stream().filter((currentBill) -> currentBill.getValue() == value).findFirst().get();
        calculate = calculate - bill.getValue();
        billsToWithdraw.add(bill);
        tempList.remove(bill);
    }
}







