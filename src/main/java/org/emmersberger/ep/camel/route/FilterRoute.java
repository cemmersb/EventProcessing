/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.route;

import org.apache.camel.Predicate;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author cemmersb
 */
@Component
public class FilterRoute extends SpringRouteBuilder {
  
  public static final String DIRECT_START_HEADER_FILTER = "direct://start-header-filter";
  
  public static final String DIRECT_START_BODY_FILTER = "direct://start-body-filter";
  
  public static final String LOG_BODY_IN_ROUTE = "log://before-body-filter?level=INFO";
  public static final String LOG_BODY_OUT_ROUTE = "log://after-body-filter?level=INFO";
  public static final String LOG_HEADER_IN_ROUTE = "log://before-header-filter?level=INFO";
  public static final String LOG_HEADER_OUT_ROUTE = "log://after-header-filter?level=INFO";
  
  @Override
  public void configure() throws Exception {
    configureFilterHeaderRoute();
    configureFilterBodyRoute();
  }
  
  private void configureFilterHeaderRoute() {
    from(DIRECT_START_HEADER_FILTER)
        .to(LOG_HEADER_IN_ROUTE)
        .filter(header("longitude").isEqualTo(new Float(11.06)))
        .filter(header("latitude").isEqualTo(new Float(49.44)))
        .to(LOG_HEADER_OUT_ROUTE)
        .end();
  }
  
  private void configureFilterBodyRoute() {
    Predicate trackingId = body().contains("1000");
    from(DIRECT_START_BODY_FILTER)
        .to(LOG_BODY_IN_ROUTE)
        .filter(trackingId)
        .to(LOG_BODY_OUT_ROUTE)
        .end();
  }
}
