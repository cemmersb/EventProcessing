/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.route;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.emmersberger.ep.camel.common.EnrichCustomer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests the split capabilites of Camel.
 * 
 * @author cemmersb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class EnrichRouteTest {

  /**
   * Producer template configured to call the enrich route.
   */
  @Produce(uri = EnrichRoute.DIRECT_START_ENRICH)
  private ProducerTemplate enrichTemplate;
  
  final EnrichCustomer enrichCustomer = new EnrichCustomer();
  
  @Before
  public void initializeEnrichCustomer() {
    enrichCustomer.setCustomerId(1L);
  }
  
  /**
   * Testing the enrichment of the enrichCustomer event.
   */
  @Test
  public void enrichTest() throws Exception {
    enrichTemplate.sendBody(enrichCustomer);
  }
}