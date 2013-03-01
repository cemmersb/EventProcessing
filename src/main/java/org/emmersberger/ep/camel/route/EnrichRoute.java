package org.emmersberger.ep.camel.route;

import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.emmersberger.ep.camel.aggregator.EnrichAggregator;
import org.emmersberger.ep.camel.common.EnrichCustomer;
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
  public static final String DIRECT_START_ENRICH = "direct://start-enrich";
  /**
   * Endpoint configuration to start the aggregation route for the enrichment
   * pattern.
   */
  public static final String DIRECT_START_ENRICHMENT = "direct://start-enrichment";
  /**
   * Endpoint configuration to log enrich route messages on INFO level.
   */
  public static final String LOG_ENRICH_ROUTE = "log://enrich-route?level=INFO";
  /**
   * Endpoint configuration to log aggregate route messages on INFO level.
   */
  public static final String LOG_AGGREGATE_ROUTE = "log://aggregate-route?level=INFO";

  @Override
  public void configure() {
    configureMainRoute();
    configureEnrichRoute();
  }

  private void configureMainRoute() {
    from(DIRECT_START_ENRICH)
        .to(LOG_ENRICH_ROUTE)
        .enrich(DIRECT_START_ENRICHMENT, new EnrichAggregator())
        .to(LOG_ENRICH_ROUTE);
  }

  private void configureEnrichRoute() {
    from(DIRECT_START_ENRICHMENT)
        .to(LOG_AGGREGATE_ROUTE)
        // Creating a second exchange object that transports an event that can enrich the original event object
        .process(new Processor() {
      public void process(Exchange exchange) {
        final List<EnrichCustomer> enrichCustomers = new ArrayList<EnrichCustomer>();
        enrichCustomers.add(new EnrichCustomer(1L, "Herbert", "Hausmann", "Hausberg", "1A", "Hebertsfelden", "12345", "Germany"));
        enrichCustomers.add(new EnrichCustomer(2L, "Franz", "Fahnberger", "Fahnenstr.", "20", "Fahnbach", "98765", "Germany"));
        enrichCustomers.add(new EnrichCustomer(3L, "Gerhard", "Gebauer", "Gehweh", "35", "Gehlheim", "54321", "Germany"));
        exchange.getIn().setBody(enrichCustomers);
      }
    })
        .to(LOG_AGGREGATE_ROUTE);
  }
}
