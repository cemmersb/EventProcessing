package org.emmersberger.ep.camel.route;

import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.emmersberger.ep.camel.common.AggregateProduct;
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
public class AggregateRouteTest {
  
  @Produce(uri = AggregateRoute.DIRECT_START_AGGREGATE)
  private ProducerTemplate aggregateTemplate;
  
  private final List<AggregateProduct> aggregateProducts = new ArrayList<AggregateProduct>();
  
  @Before
  public void initializeAggregateProduct() {
    aggregateProducts.clear();
    aggregateProducts.add(new AggregateProduct("Small Parcel", "Max Size: 60x30x15, Max Weight: 2kg", 3.90));
    aggregateProducts.add(new AggregateProduct("Parcel", "Max Size: 120x60x60, Max Weight: 10kg", 4.90));
    aggregateProducts.add(new AggregateProduct("Large Parcel", "Max Size: 4m^3, Max Weight: 100kg", 44.90));
  }
  
  @Test
  public void testAggregateRoute() {
    for (AggregateProduct aggregateProduct : aggregateProducts) {
      aggregateTemplate.sendBody(aggregateProduct);
    }
  }
}