package org.emmersberger.ep.camel.common;

/**
 *
 * @author cemmersb
 */
public class EnrichCustomer {
  /**
   * Identifies a customer via unique number.
   */
  private long customerId;
  /**
   * First name of the customer.
   */
  private String firstName;
  /**
   * Last name of the customer.
   */
  private String lastName;
  /**
   * Street name where he lives.
   */
  private String streetName;
  /**
   * House number where he lives, may contain also characters.
   */
  private String house;
  /**
   * City name where the customer is living.
   */
  private String city;
  /**
   * ZipCode of the city.
   */
  private String zipCode;
  /**
   * Country where he is living.
   */
  private String country;
  
  /**
   * Empty constructor that can be used for partial field initialization in
   * combination with getter and setter methods.
   */
  public EnrichCustomer() {}
  
  /**
   * Full constructor that requires all fields to be passed.
   */
  public EnrichCustomer(long customerId, String firstName, String lastName,
    String streetName, String house, String city, String zipCode, String country) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.streetName = streetName;
    this.house = house;
    this.city = city;
    this.zipCode = zipCode;
    this.country = country;
  }
  
  /**
   * @return the customerId
   */
  public long getCustomerId() {
    return customerId;
  }

  /**
   * @param customerId the customerId to set
   */
  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the streetName
   */
  public String getStreetName() {
    return streetName;
  }

  /**
   * @param streetName the streetName to set
   */
  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  /**
   * @return the house
   */
  public String getHouse() {
    return house;
  }

  /**
   * @param house the house to set
   */
  public void setHouse(String house) {
    this.house = house;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return the zipCode
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * @param zipCode the zipCode to set
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }
}
