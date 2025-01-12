
package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "email",
        "fullName",
        "groups"
})
@XmlRootElement(namespace = "http://javaops.ru", name = "User")
public class User {

    @XmlElement(namespace = "http://javaops.ru", required = true)
    protected String email;

    @XmlElement(namespace = "http://javaops.ru", required = true)
    protected String fullName;

    @XmlAttribute(required = true)
    protected FlagType flag;

    @XmlAttribute(name = "city", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object city;

    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    @XmlElementWrapper(name="groups", namespace = "http://javaops.ru")
    @XmlElement(name="group", namespace = "http://javaops.ru")
    protected ArrayList<Group> groups;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public FlagType getFlag() {
        return flag;
    }

    public void setFlag(FlagType flag) {
        this.flag = flag;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", flag=" + flag +
                ", city=" + ((CityType)city).getValue() +
                ", groups=" + groups +
                '}';
    }
}
