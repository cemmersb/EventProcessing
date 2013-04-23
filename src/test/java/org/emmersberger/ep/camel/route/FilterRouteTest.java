/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.emmersberger.ep.camel.common.ParcelLocation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author cemmersb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FilterRouteTest {
  
  @Produce(uri = FilterRoute.DIRECT_START_HEADER_FILTER)
  private ProducerTemplate filterHeaderTemplate;
  
  @Produce(uri = FilterRoute.DIRECT_START_BODY_FILTER)
  private ProducerTemplate filterBodyTemplate;
  
  private List<ParcelLocation> parcelLocations = new ArrayList<ParcelLocation>();
  
  @Before
  public void initializeEnrichCustomer() {
    parcelLocations.add(new ParcelLocation(1000L, new Float(11.58), new Float(48.15))); // Munich
    parcelLocations.add(new ParcelLocation(1000L, new Float(12.09), new Float(49.01))); // Regensburg
    parcelLocations.add(new ParcelLocation(2000L, new Float(11.58), new Float(48.15))); // Munich
    parcelLocations.add(new ParcelLocation(3000L, new Float(12.09), new Float(49.01))); // Regensburg
    parcelLocations.add(new ParcelLocation(1000L, new Float(11.06), new Float(49.44))); // Nuremberg
  }
  
  @Test
  public void testFilterHeaderRoute() {
    for (ParcelLocation parcelLocation : parcelLocations) {
      Map<String, Object> headers = new HashMap<String, Object>();
      headers.put("longitude", parcelLocation.getLongitude());
      headers.put("latitude", parcelLocation.getLatitude());
      filterHeaderTemplate.sendBodyAndHeaders(parcelLocation, headers);
    }
  }
  
  @Test
  public void testFilterBodyRoute() {
    for (ParcelLocation parcelLocation : parcelLocations) {
      filterBodyTemplate.sendBody(parcelLocation.toString());
    }
  }
}