package ru.javaops.masterjava.xml.util;

import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MainXml {
    private final JaxbUnmarshaller jaxbUnmarshaller;
    private static final String filePath = "src/test/resources/payload.xml";

    public MainXml() throws JAXBException{
        this.jaxbUnmarshaller = new JaxbUnmarshaller(JAXBContext.newInstance(Payload.class));
    }

    public static void main(String[] args) throws JAXBException, IOException {
        String groupName = "masterjava-02";
        List<User> users = new MainXml().getUsersByGroup(groupName);
        users.forEach(System.out::println);
    }

    public List<User> getUsersByGroup(String name) throws JAXBException, IOException {
        String xml = new String(Files.readAllBytes(Paths.get(filePath).toAbsolutePath()));
        Payload document = (Payload)jaxbUnmarshaller.unmarshal(xml);
        return document.getUsers().stream()
                .filter(it -> it.getGroups().stream().anyMatch(group -> name.equals(group.getGroupName())))
                .collect(Collectors.toList());
    }
}
