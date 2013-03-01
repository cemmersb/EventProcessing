package org.emmersberger.ep.camel.aggregator;

import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.emmersberger.ep.camel.common.AggregateProduct;

/**
 *
 * @author cemmersb
 */
public class AggregateAggregator implements AggregationStrategy {
  
  private final static List<AggregateProduct> AGGREGATE_PRODUCTS = new ArrayList<AggregateProduct>();
  
  public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
    final double avgProductPrice = 0;
    
    if(!AGGREGATE_PRODUCTS.contains(oldExchange.getIn().getBody(AggregateProduct.class))) {
      AGGREGATE_PRODUCTS.add(oldExchange.getIn().getBody(AggregateProduct.class));
    } else if(!AGGREGATE_PRODUCTS.contains(newExchange.getIn().getBody(AggregateProduct.class))) {
      AGGREGATE_PRODUCTS.add(newExchange.getIn().getBody(AggregateProduct.class));
    }
    
    oldExchange.getIn().setBody(this);
    return oldExchange;
  }
  
  private double calcAvgProductPrice(double avgProductPrice) {
    for (AggregateProduct aggregateProduct : AGGREGATE_PRODUCTS) {
      avgProductPrice = avgProductPrice + aggregateProduct.getPrice();
    }
    avgProductPrice = (avgProductPrice / AGGREGATE_PRODUCTS.size());
    return avgProductPrice;
  }
}