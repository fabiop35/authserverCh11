package com.security.entities;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@Getter
//@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
@Data
@Entity
public class User {
 
 @Id
 private String username;
 private String password;

 /*@OneToMany
 private List<Otp> otps;/*

 /*public String getUsername(){
  return this.username;
 }*/

}
