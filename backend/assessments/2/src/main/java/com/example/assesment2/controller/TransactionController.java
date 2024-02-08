package com.example.assesment2.controller;

import com.example.assesment2.entity.Transaction;
import com.example.assesment2.logging.Logging;
import com.example.assesment2.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;
    Logging.LoggerType loggerTypeInfo=Logging.LoggerType.INFO;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<String> addEvent(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
        return new ResponseEntity<>("Event Added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found");
    }

    @GetMapping("/allTransactions")
    public ResponseEntity<List<Transaction>> displayAll() {
        List<Transaction> transactions=transactionService.displayAll();
        if (transactions!=null){
            Logging.printLogger("List extracted Successfully",loggerTypeInfo);
            return new ResponseEntity<>(transactions,HttpStatus.OK);
        }
        else {
            return null;
        }
    }
}
