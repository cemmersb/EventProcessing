/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.route;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests the split capabilites of Camel.
 * 
 * @author cemmersb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SplitRouteTest {

  /**
   * Producer template configured to call the split route.
   */
  @Produce(uri = SplitRoute.DIRECT_START_SPLIT)
  private ProducerTemplate splitTemplate;
  /**
   * Collection of type String that can be sent via the network.
   */
  private final List<String> strEventTypes = new ArrayList<String>();
  /**
   * Collection of type Double that can be sent via the network.
   */
  private final List<Integer> intEventTypes = new ArrayList<Integer>();
  
  /**
   * Configuring a collection of string objects that can be split.
   */
  @Before
  public void configureSplitStrMsg() {
    strEventTypes.clear();
    strEventTypes.add("First Event");
    strEventTypes.add("Second Event");
    strEventTypes.add("Third Event");
  }
  
  /**
   * Configuring a collection of Integer objects that can be split.
   */
  @Before
  public void configureSplitIntMsg() {
    intEventTypes.clear();
    intEventTypes.add(new Integer(1));
    intEventTypes.add(new Integer(2));
    intEventTypes.add(new Integer(3));
  }
  
  /**
   * Testing the sending and splitting of the String collection.
   */
  @Test
  public void splitStrTest() throws Exception {
    splitTemplate.sendBody(strEventTypes);
  }
  
  /**
   * Testing the sending and splitting of the Integer collection.
   */
  @Test
  public void splitIntTest() throws Exception {
    splitTemplate.sendBody(intEventTypes);
  }
}