package org.emmersberger.ep.camel.aggregator;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.emmersberger.ep.camel.common.EnrichCustomer;

import java.util.List;

/**
 *
 * @author cemmersb
 */
public class EnrichAggregator implements AggregationStrategy {
  

  public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
    
    final EnrichCustomer originalCustomer = oldExchange.getIn().getBody(EnrichCustomer.class);
    final List<EnrichCustomer> enrichCustomers = newExchange.getIn().getBody(List.class);
    
    for (EnrichCustomer enrichCustomer : enrichCustomers) {
      if(enrichCustomer.getCustomerId() == originalCustomer.getCustomerId()) {
        originalCustomer.setFirstName(enrichCustomer.getFirstName());
        originalCustomer.setLastName(enrichCustomer.getLastName());
        originalCustomer.setStreetName(enrichCustomer.getStreetName());
        originalCustomer.setHouse(enrichCustomer.getHouse());
        originalCustomer.setZipCode(enrichCustomer.getZipCode());
        originalCustomer.setCity(enrichCustomer.getCity());
        originalCustomer.setCountry(enrichCustomer.getCountry());
        break;
      }
    }
    oldExchange.getIn().setBody(originalCustomer);
    return oldExchange;
  }
}