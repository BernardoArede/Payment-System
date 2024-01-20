package com.BernardoArede.paymentsystem.service;

import com.BernardoArede.paymentsystem.entity.User;
import com.BernardoArede.paymentsystem.repository.UserRepository;
import com.BernardoArede.paymentsystem.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerUser(User user){

        if(userRepository.findByEmail(user.getEmail())!= null){
            throw new RuntimeException("This email already exist");
        }else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String RandomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(RandomCode);
            user.setEnabled(false);
            User savedUser = userRepository.save(user);



            return savedUser;

        }
    }
}
