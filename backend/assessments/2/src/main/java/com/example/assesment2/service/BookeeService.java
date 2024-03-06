package com.example.assesment2.service;

import com.example.assesment2.entity.Bookee;
import com.example.assesment2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookeeService{
    private final BookeeRepository bookeeRepository;
    @Autowired
    public BookeeService(BookeeRepository bookeeRepository) {
        this.bookeeRepository = bookeeRepository;
    }
    public List<Bookee> findAll() {
        return bookeeRepository.findAll();
    }
}
