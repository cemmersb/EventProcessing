package org.emmersberger.ep.camel.route;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Demonstrates the splitting capabilities for event types passed via Camel.
 *
 * @author cemmersb
 */
@Component
public class SplitRoute extends SpringRouteBuilder {

  /**
   * Endpoint configuration to start the split example.
   */
  public static final String DIRECT_START_SPLIT = "direct://start-split";
  /**
   * Endpoint configuration to log messages on INFO level.
   */
  public static final String LOG_SPLIT_ROUTE = "log://split-route?level=INFO";

  @Override
  public void configure() {
    from(DIRECT_START_SPLIT)
        .to(LOG_SPLIT_ROUTE)
        .split(body())
        .to(LOG_SPLIT_ROUTE);
  }
}