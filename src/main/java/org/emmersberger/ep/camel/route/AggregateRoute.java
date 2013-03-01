/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.route;

import org.apache.camel.spring.SpringRouteBuilder;
import org.emmersberger.ep.camel.aggregator.AggregateAggregator;
import org.springframework.stereotype.Component;

/**
 *
 * @author cemmersb
 */
@Component
public class AggregateRoute extends SpringRouteBuilder {
  
  public static final String DIRECT_START_AGGREGATE = "direct://start-aggregate";
  
  public static final String LOG_ENRICH_ROUTE = "log://enrich-route?level=INFO";
  
  @Override
  public void configure() {
    from(DIRECT_START_AGGREGATE)
        .aggregate(body(), new AggregateAggregator()).completionTimeout(3000)
        .to(LOG_ENRICH_ROUTE);
  }
}
