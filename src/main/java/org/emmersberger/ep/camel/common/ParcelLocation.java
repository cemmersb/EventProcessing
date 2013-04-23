/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author cemmersb
 */
public class ParcelLocation {

  private long parcelId;
  private float longitude;
  private float latitude;

  public ParcelLocation() {
  }

  public ParcelLocation(long parcelId, float longitude, float latitude) {
    this.parcelId = parcelId;
    this.longitude = longitude;
    this.latitude = latitude;
  }

  /**
   * @return the parcelId
   */
  public long getParcelId() {
    return parcelId;
  }

  /**
   * @param parcelId the parcelId to set
   */
  public void setParcelId(long parcelId) {
    this.parcelId = parcelId;
  }

  /**
   * @return the longitude
   */
  public float getLongitude() {
    return longitude;
  }

  /**
   * @param longitude the longitude to set
   */
  public void setLongitude(float longitude) {
    this.longitude = longitude;
  }

  /**
   * @return the latitude
   */
  public float getLatitude() {
    return latitude;
  }

  /**
   * @param latitude the latitude to set
   */
  public void setLatitude(float latitude) {
    this.latitude = latitude;
  }
  
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
        .append("parcelId", parcelId)
        .append("latitude", latitude)
        .append("longitude", longitude)
        .toString();
  }
}
