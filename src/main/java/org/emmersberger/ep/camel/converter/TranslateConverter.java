/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emmersberger.ep.camel.converter;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.camel.Converter;
import org.emmersberger.ep.camel.common.AggregateProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author cemmersb
 */
@Converter
public class TranslateConverter {

  private static final Logger LOGGER = LoggerFactory.getLogger(TranslateConverter.class);

  @Converter
  public AggregateProduct convertInputStreamToAggregateProduct(InputStream inputStream) {

    final XPath xPath = XPathFactory.newInstance().newXPath();
    final Document document = createXMLDocument(inputStream);

    return createAggregateProduct(document);
  }

  private AggregateProduct createAggregateProduct(Document document) {
    final XPath xPath = XPathFactory.newInstance().newXPath();
    final AggregateProduct aggregateProduct = new AggregateProduct();
    try {
      aggregateProduct.setName(xPath.evaluate("/Product/Name", document));
      aggregateProduct.setDescription(
          xPath.evaluate("/Product/Size", document) + " "
          + xPath.evaluate("/Product/Weight", document));
      aggregateProduct.setPrice(Double.parseDouble(
          xPath.evaluate("/Product/Price", document)));
    } catch (XPathExpressionException ex) {
      LOGGER.error("");
    } catch (NumberFormatException ex) {
      LOGGER.error("");
    }

    return aggregateProduct;
  }

  private Document createXMLDocument(InputStream inputStream) {

    try {
      final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      final DocumentBuilder builder = factory.newDocumentBuilder();
      final Document document = builder.parse(inputStream);
      return document;
    } catch (ParserConfigurationException ex) {
      LOGGER.error("Could not create the document builder: '{}'.", ex.getMessage());
    } catch (SAXException ex) {
      LOGGER.error("Unable to parse XML from InputStream: '{}'.", ex.getMessage());
    } catch (IOException ex) {
      LOGGER.error("Unable to read the InputStream: '{}'", ex.getMessage());
    }
    return null;
  }
}