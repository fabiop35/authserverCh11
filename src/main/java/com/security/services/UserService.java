package com.security.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;

import com.security.entities.Otp;
import com.security.entities.User;
import com.security.repositories.UserRepository;
import com.security.repositories.OtpRepository;
import com.security.utils.GenerateCodeUtil;


@Service
@Transactional
public class UserService{

 @Autowired
 private UserRepository userRepository;

 @Autowired
 private OtpRepository otpRepository;

 @Autowired PasswordEncoder passwordEncoder;

 private final Logger logger = 
        Logger.getLogger(
           UserService.class.getName() );

 public User addUser(User user){
   
   user.setPassword(passwordEncoder
           .encode( user.getPassword() ) );

   logger.info("Passwd.encode: "+
                  user.getPassword());
    
   return userRepository.save(user);
 }

 public void auth(User user){

  Optional<User> o = 
    userRepository.findUserByUsername(
                    user.getUsername() );
  if (o.isPresent()){
   User u = o.get();
    if(passwordEncoder.matches(
      user.getPassword(), u.getPassword())){
     renewOtp(u);     
    }else{
     throw new BadCredentialsException(
                       "Bad Credentials.");
    }
  }else {
    throw new BadCredentialsException(
                       "Bad Credentials.");
  }

  /*if (findUser(user).isPresent() ){
    if ( checkPassword(user) ){
      Otp otpObj = new Otp();
      otpObj.setCode(
        generateCode(user.getUsername() ));
     otpObj.setUsername(user.getUsername());

      Optional<Otp> otp = 
         Optional.of( otpObj );
     return otp;
    }else{
     return Optional.empty();
    }
  }else{
   return Optional.empty();
  }*/

 }

 public boolean checkPassword(User user){
   
   Optional<User> encodePass =
       userRepository.findUserByUsername(
                      user.getUsername() );

   if ( encodePass.isPresent() ){
   User usrDB = encodePass.get();    
   System.out.println("><> EncodePassw :"
               +usrDB.getPassword() );
   return passwordEncoder.matches(
        user.getPassword(), 
            usrDB.getPassword() );
   }else{
    return false;
   }
 }

 private void renewOtp(User u){

    String code = 
        GenerateCodeUtil.generateCode();
   
    Optional<Otp> userOtp =
    otpRepository.
        findOtpByUsername(u.getUsername());

    if (userOtp.isPresent() ){
     Otp otp = userOtp.get();
     otp.setCode(code);
    }else {
     Otp otp = new Otp();
     otp.setUsername(u.getUsername());
     otp.setCode(code);
     otpRepository.save(otp);
    }
 }

 public boolean check(Otp otpToValidate){

  Optional<Otp> userOtp =
    otpRepository
     .findOtpByUsername(
             otpToValidate.getUsername());
  if(userOtp.isPresent()){
   Otp otp = userOtp.get();
   if(otpToValidate.getCode()
               .equals(otp.getCode())){
     return true;
   }
  }
  return false;
 }  

}
