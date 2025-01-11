package ru.javaops.masterjava.xml.util;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.ElementSelectors;
import ru.javaops.masterjava.xml.schema.Payload;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;

public class JaxbMarshallerTest {
    private static JAXBContext context;
    private static JaxbMarshaller jaxbMarshaller;
    private static JaxbUnmarshaller jaxbUnmarshaller;
    private static final String filePath = "src/test/resources/payload.xml";

    @BeforeClass
    public static void init() throws Exception {
        context = JAXBContext.newInstance(Payload.class);
        jaxbMarshaller = new JaxbMarshaller(context);
        jaxbUnmarshaller = new JaxbUnmarshaller(context);
    }


    @Test
    public void testPayload() throws Exception {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        String xml = new String(Files.readAllBytes(Paths.get(filePath).toAbsolutePath()));
        Payload payloadNew = (Payload) unmarshaller.unmarshal(new StringReader(xml));

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(payloadNew, writer);

        String strPayload = writer.toString();
        System.out.println(strPayload);
    }

    @Test
    public void testJaxbMarshaller() throws Exception {
        String xml = new String(Files.readAllBytes(Paths.get(filePath).toAbsolutePath()));
        Payload payloadNew = (Payload)jaxbUnmarshaller.unmarshal(xml);

        String strPayload = jaxbMarshaller.marshal(payloadNew);
        System.out.println(strPayload);
    }

    @Test
    public void checkXmlDifference() throws Exception {
        String givenXml = new String(Files.readAllBytes(Paths.get(filePath).toAbsolutePath()));
        String expectedXml = new String(Files.readAllBytes(Paths.get(filePath).toAbsolutePath()));
        Payload given = (Payload)jaxbUnmarshaller.unmarshal(givenXml);

        String actual = jaxbMarshaller.marshal(given);

        Diff diff = DiffBuilder.compare(expectedXml)
                .withTest(actual)
                .ignoreComments()
                .ignoreWhitespace()
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))//ignore tags ordering
                .checkForSimilar()
                .build();

        diff.getDifferences().forEach(System.out::println);
        assertFalse(diff.hasDifferences());
    }
}