package com.kdu.smarthome.services;

import com.kdu.smarthome.exceptions.CustomException;
import com.kdu.smarthome.model.Users;
import com.kdu.smarthome.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * to add a new user to the user list
     * @param users: new user object
     * @throws CustomException if the user is not valid
     */
    public void addUser(Users users) throws CustomException {
        if(users!=null){
            userRepository.save(users);
        } else {
            throw new CustomException("Invalid parameter");
        }
    }
    /**
     * to get an existing user to the user list
     * @param username: the user to be fetched
     * @throws CustomException if the user is not valid
     */
    public Users getUserByUsername(String username) throws CustomException{
        Optional<Users> user=userRepository.getUsersByUsername(username);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new CustomException("Not found");
        }
    }
}
