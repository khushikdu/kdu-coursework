package com.example.assesment2.service;

import com.example.assesment2.dao.PersonDAO;
import com.example.assesment2.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartUpDataAddition implements CommandLineRunner {

    @Value("${person1.name}")
    private String namePerson1;
    @Value("${person1.username}")
    private String usernamePerson1;
    @Value("${person1.password}")
    private String passwordPerson1;
    @Value("${person2.name}")
    private String namePerson2;
    @Value("${person2.username}")
    private String usernamePerson2;
    @Value("${person2.password}")
    private String passwordPerson2;
    @Value("${role.admin}")
    private String roleAdmin;
    @Value("${role.user}")
    private String roleUser;

    private PersonDAO personDAO;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public StartUpDataAddition(PersonDAO personDAO, PasswordEncoder passwordEncoder) {
        this.personDAO = personDAO;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * to add persons to the list
     * @param args incoming main method arguments
     * throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        personDAO.addPerson(new Person(namePerson1,usernamePerson1,passwordEncoder.encode(passwordPerson1),roleAdmin));
        personDAO.addPerson(new Person(namePerson2,usernamePerson2,passwordEncoder.encode(passwordPerson2),roleUser));
    }
}