package org.emmersberger.ep.camel.aggregator;

import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.emmersberger.ep.camel.common.AggregateProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cemmersb
 */
public class AggregateAggregator implements AggregationStrategy {

  private final static Logger LOGGER = LoggerFactory.getLogger(AggregateAggregator.class);
  private final static List<AggregateProduct> AGGREGATE_PRODUCTS = new ArrayList<AggregateProduct>();

  public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

    final AggregateProduct newAggregateProduct = getAggregateProductFromExchange(newExchange);

    if (null != newAggregateProduct) {
      if (!AGGREGATE_PRODUCTS.contains(newAggregateProduct)) {
        AGGREGATE_PRODUCTS.add(newAggregateProduct);
      }
    }

    newExchange.getIn().setBody(calcAvgProductPrice());
    return newExchange;
  }

  private AggregateProduct getAggregateProductFromExchange(Exchange exchange) {
    AggregateProduct aggregateProduct = null;
    try {
      aggregateProduct = exchange.getIn().getBody(AggregateProduct.class);
    } catch (Exception ex) {
      LOGGER.warn("Could not read '{}':", AggregateProduct.class.getSimpleName(), ex.getMessage());
    }
    return aggregateProduct;
  }

  private double calcAvgProductPrice() { 
    double avgProductPrice = 0;
    for (AggregateProduct aggregateProduct : AGGREGATE_PRODUCTS) {
      avgProductPrice = avgProductPrice + aggregateProduct.getPrice();
    }
    avgProductPrice = avgProductPrice / AGGREGATE_PRODUCTS.size();
    return avgProductPrice;
  }
}