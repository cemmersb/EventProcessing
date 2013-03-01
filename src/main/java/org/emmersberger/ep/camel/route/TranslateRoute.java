/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.route;

import org.apache.camel.spring.SpringRouteBuilder;
import org.emmersberger.ep.camel.common.AggregateProduct;
import org.springframework.stereotype.Component;

/**
 *
 * @author cemmersb
 */
@Component
public class TranslateRoute extends SpringRouteBuilder {

  public static final String DIRECT_START_TRANSLATE = "direct://start-translate";
  public static final String LOG_TRANSLATE_ROUTE = "log://translate-route?level=INFO";

  @Override
  public void configure() {
    from(DIRECT_START_TRANSLATE)
        .to(LOG_TRANSLATE_ROUTE)
        .convertBodyTo(AggregateProduct.class)
        .to(LOG_TRANSLATE_ROUTE);
  }
}