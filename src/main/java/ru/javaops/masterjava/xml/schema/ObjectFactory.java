package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import java.util.ArrayList;

@XmlRegistry
public class ObjectFactory {

    private final static QName _City_QNAME = new QName("http://javaops.ru", "City");

    public ObjectFactory() {
    }

    public Payload createPayload() {
        return new Payload();
    }

    public User createUser() {
        return new User();
    }

    public ArrayList<CityType> createPayloadCities() {
        return new ArrayList<>();
    }

    public ArrayList<User> createPayloadUsers() {
        return new ArrayList<>();
    }

    public CityType createCityType() {
        return new CityType();
    }

    @XmlElementDecl(namespace = "http://javaops.ru", name = "City")
    public JAXBElement<CityType> createCity(CityType value) {
        return new JAXBElement<CityType>(_City_QNAME, CityType.class, null, value);
    }

}
