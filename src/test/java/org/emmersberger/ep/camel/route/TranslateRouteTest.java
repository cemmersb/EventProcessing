/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.route;

import java.io.InputStream;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
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
public class TranslateRouteTest {
  
  @Produce(uri = TranslateRoute.DIRECT_START_TRANSLATE)
  private ProducerTemplate translateTemplate;
  
  private InputStream productInputStream = null;
  
  @Before
  public void initializeProductStream() {
    productInputStream = this.getClass().getResourceAsStream("ProductDocument.xml");
  }
  
  @Test
  public void testTranslateRoute() {
    translateTemplate.sendBody(productInputStream);
  }
}