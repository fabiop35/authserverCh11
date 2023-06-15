package com.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.entities.Otp;

public interface OtpRepository 
    extends JpaRepository<Otp, String>{

 public Optional<Otp> 
      findOtpByUsername(String username);

}
