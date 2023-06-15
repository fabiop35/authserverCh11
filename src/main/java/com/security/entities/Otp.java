package com.security.entities;

import javax.persistence.Id;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Otp {
  
  @Id
  private String username;
  private String code;


}
