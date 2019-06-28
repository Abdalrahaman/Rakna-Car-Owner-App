package com.example.rakna.raknagraduationproject.Model.hassanModel;

import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * POJO class for an individual location
 */
public class IndividualLocation {

  private String garageId;
  private String name;
  private String address;
  private String hours;
  private String phoneNum;
  private String distance;
  private LatLng location;
  private String status;
  private String rate;
  private String image;

  public IndividualLocation(String garageId, String name, String address, String hours, String phoneNum, LatLng location, String status, String rate, String image) {
    this.garageId = garageId;
    this.name = name;
    this.address = address;
    this.hours = hours;
    this.phoneNum = phoneNum;
    this.location = location;
    this.status = status;
    this.rate = rate;
    this.image = image;
  }

  public String getGarageId() {
    return garageId;
  }

  public void setGarageId(String garageId) {
    this.garageId = garageId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public String getHours() {
    return hours;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public String getDistance() {
    return distance;
  }

  public void setDistance(String distance) {
    this.distance = distance;
  }

  public LatLng getLocation() {
    return location;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
