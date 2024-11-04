package com.foodisgood.tootasty.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;

@Service
@RequiredArgsConstructor
public class EncryptionService {
    private  final PasswordEncoder passwordEncoder;

    public String encryptPassword(String password) {return passwordEncoder.encode(password);}

    public boolean verifyPassword(String password, String encryptedPassword) {return passwordEncoder.matches(password, encryptedPassword);}
}
