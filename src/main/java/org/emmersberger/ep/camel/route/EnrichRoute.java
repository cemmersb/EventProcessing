/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.route;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author cemmersb
 */
@Component
public class EnrichRoute extends SpringRouteBuilder {
  
  /**
   * Endpoint configuration to start the enrich example.
   */
  public static final String DIRECT_START_ENRICH = "direct://enrich-split";
  /**
   * Endpoint configuration to log messages on INFO level.
   */
  public static final String LOG_ENRICH_ROUTE = "log://enrich-route?level=INFO";
  
  @Override
  public void configure() {
    configureMainRoute();
    configureEnrichRoute();
  }
  
  private void configureMainRoute() {
    from(DIRECT_START_ENRICH)
      .to(LOG_ENRICH_ROUTE);
  }
  
  private void configureEnrichRoute() {
    
  }
}
