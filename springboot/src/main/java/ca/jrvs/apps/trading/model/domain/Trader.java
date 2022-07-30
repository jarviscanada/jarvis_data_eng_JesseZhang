
package ca.jrvs.apps.trading.model.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

public class Trader implements Entity<Integer> {

  private String country;
  private Date dob;
  private String email;
  private String firstName;
  private Integer id;
  private String lastName;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}