package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Trader implements Entity<Integer> {

  private String country;
  @JsonFormat(pattern="yyyy-MM-dd")
  private Date dob;
  private String email;
  private String firstName;
  private Integer id;
  private String lastName;


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


}