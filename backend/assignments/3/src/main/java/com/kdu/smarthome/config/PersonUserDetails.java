package com.kdu.smarthome.config;

import com.kdu.smarthome.exceptions.CustomException;
import com.kdu.smarthome.model.Users;
import com.kdu.smarthome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonUserDetails implements UserDetailsService {
    private final UserService userService;
    @Autowired
    public PersonUserDetails(UserService userService) {
        this.userService = userService;
    }

    /**
     * to identifying the user whose data is required
     * @param username the username identifying the user whose data is required.
     * @return : user object
     * throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users person = null;
        try{
            person=userService.getUserByUsername(username);
        } catch (Exception e){
            throw new CustomException(e.getMessage());
        }
        String personUserName = null;
        String personPassword = null;
        List<GrantedAuthority> authorities = null;

        if(person == null){
            throw new UsernameNotFoundException("User details not found for user : " + username + ". Please register fist.");
        }else{
            personUserName = person.getUsername();
            personPassword = person.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_BASIC"));
        }
        return new User(personUserName, personPassword, authorities);
    }
}