/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
  public static final String LOG_AGGREGATE_ROUTE = "log://aggregate-route?level=INFO";

  @Override
  public void configure() {
    final AggregateAggregator aggregateAggregator = new AggregateAggregator();
    
    from(DIRECT_START_AGGREGATE)
        .to(LOG_AGGREGATE_ROUTE)
        .aggregate(body(), aggregateAggregator).completionSize(1)
        .to(LOG_AGGREGATE_ROUTE);
  }
}
