package com.example.assesment2.repository;

import com.example.assesment2.entity.Bookee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookeeRepository extends JpaRepository<Bookee,Integer> {
}
