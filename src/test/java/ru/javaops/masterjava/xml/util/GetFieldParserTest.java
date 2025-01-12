package ru.javaops.masterjava.xml.util;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.javaops.masterjava.xml.schema.Payload;

import javax.xml.bind.JAXBContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GetFieldParserTest {
    private static GetFieldParser parser;
    private static JaxbUnmarshaller jaxbUnmarshaller;
    private static final String filePath = "src/test/resources/payload.xml";

    @BeforeClass
    public static void init() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Payload.class);
        parser = new GetFieldParser(context);
        jaxbUnmarshaller = new JaxbUnmarshaller(context);
    }


    @Test
    public void checkXmlDifference() throws Exception {
        String givenXml = new String(Files.readAllBytes(Paths.get(filePath).toAbsolutePath()));
        Payload document = (Payload)jaxbUnmarshaller.unmarshal(givenXml);
        List<String> project = parser.getFieldByTag("Users/User", document);
        project.forEach(System.out::println);
    }
}