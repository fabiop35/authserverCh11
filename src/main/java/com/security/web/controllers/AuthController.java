package com.security.web.controllers;

import java.util.Optional;
import java.util.logging.Logger;
  
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

import com.security.entities.Otp;
import com.security.entities.User;
import com.security.services.UserService;

@RestController
public class AuthController {
  
  private String username;

  private final Logger logger = 
    Logger.getLogger(
        AuthController.class.getName() );

 @Autowired
 private UserService userService;

 @ResponseStatus(HttpStatus.CREATED)
 @PostMapping("/user/add")
 public User addUser(
             @RequestBody User user){
   logger.info("+ adding User: "+
             user.getUsername() );
   return userService.addUser(user);
 }

 @PostMapping("/user/auth")
 public void auth(@RequestBody User user){
   
   System.out.println("###############");
   logger.info("》》》auth.Username: "+
                   user.getUsername());
    userService.auth(user);

   /*Optional<Otp> otp = userService
                           .auth(user);
   if ( otp.isPresent() ){
     return new 
        ResponseEntity<Otp>(otp.get(),
                            HttpStatus.OK); 
   }else {
     return new ResponseEntity<>(null, 
                  HttpStatus.UNAUTHORIZED);
   }*/

   /* userDB.ifPresent( 
      usr ->username = usr.getUsername() 
    );*/
 }

 @PostMapping("/otp/check")
 public void check(@RequestBody Otp otp,
              HttpServletResponse response){

   logger.info("><> checkOTP <><");
   if ( userService.check(otp) ){
    response
    .setStatus(HttpServletResponse.SC_OK);
   }else {
    response.setStatus(
         HttpServletResponse.SC_FORBIDDEN);
   }
 }

}
