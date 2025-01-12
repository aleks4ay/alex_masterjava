package ru.javaops.masterjava.xml.util;

import org.junit.Test;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class XPathProcessorTest {
    private static final String filePath = "src/test/resources/payload.xml";
    private static final String cityExpression = "/*[name()='Payload']/*[name()='Cities']/*[name()='City']/text()";
    private static final String userEmailExpression = "/*[name()='Payload']/*[name()='Users']/*[name()='User']/*[name()='email']/text()";
    /*
     *  /@flag + /@city + /@... = /@*
     */
    private static final String userFlagAttributeExpression = "/*[name()='Payload']/*[name()='Users']/*[name()='User']/@*";
    private static final String userFieldExpression = "/*[name()='Payload']/*[name()='Users']/*[name()='User']/*/text()";
    private static final String userGroupExpression = "/*[name()='Payload']/*[name()='Users']/*[name()='User']/*[name()='groups']/*[name()='group']/text()";
    private static final String userAttributeExpression = "/*[name()='Payload']/*[name()='Users']/*/@*";
    private static final String userExpression = userFieldExpression + " | " + userGroupExpression + " | " + userAttributeExpression;
    private static final String userExpression2 = "/*[name()='Payload']/*[name()='Users']/*[name()='User']//text()" + " | " + userAttributeExpression;

    private static final String projectExpression = "/*[name()='Payload']/*[name()='Projects']/*[name()='Project']/text()";
    private static final String groupExpression = "/*[name()='Payload']/*[name()='Groups']/*[name()='Group']/text()";

    @Test
    public void getCities() throws Exception {
        try (InputStream is = Files.newInputStream(Paths.get(filePath).toAbsolutePath())) {
            XPathProcessor processor = new XPathProcessor(is);
            XPathExpression expression = XPathProcessor.getExpression(cityExpression);
            NodeList nodes = processor.evaluate(expression, XPathConstants.NODESET);
            IntStream.range(0, nodes.getLength()).forEach(
                    i -> System.out.println(nodes.item(i).getNodeValue())
            );
        }
    }

    @Test
    public void getAttribute() throws Exception {
        try (InputStream is = Files.newInputStream(Paths.get(filePath).toAbsolutePath())) {

            XPathProcessor processor = new XPathProcessor(is);
            XPathExpression expression = XPathProcessor.getExpression(userExpression2);
            NodeList nodes = processor.evaluate(expression, XPathConstants.NODESET);
            IntStream.range(0, nodes.getLength()).forEach(
                    i -> {
                        if(!(nodes.item(i).getNodeValue().trim().replace(System.lineSeparator(), "")).isEmpty()) {
                            System.out.println("'" + nodes.item(i).getNodeValue() + "'");
                        }
                    }
            );
        }
    }
}