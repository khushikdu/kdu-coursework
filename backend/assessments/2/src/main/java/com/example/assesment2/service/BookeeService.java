package com.example.assesment2.service;

import com.example.assesment2.entity.Bookee;
import com.example.assesment2.entity.User;
import com.example.assesment2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookeeService{
    private BookeeRepository bookeeRepository;
    private EventRepository eventRepository;
    private CatalogRepository catalogRepository;
    private TransactionRepository transactionRepository;
    @Autowired
    public BookeeService(BookeeRepository bookeeRepository, EventRepository eventRepository, CatalogRepository catalogRepository, TransactionRepository transactionRepository) {
        this.bookeeRepository = bookeeRepository;
        this.eventRepository = eventRepository;
        this.catalogRepository = catalogRepository;
        this.transactionRepository = transactionRepository;
    }
    public List<Bookee> findAll() {
        return bookeeRepository.findAll();
    }
}
