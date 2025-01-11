
package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://javaops.ru", name = "Payload")
public class Payload {
    @XmlElementWrapper(name = "Cities", namespace = "http://javaops.ru")
    @XmlElement(name = "City", namespace = "http://javaops.ru", required = true)
    protected ArrayList<CityType> cities;

    @XmlElementWrapper(name="Users", namespace = "http://javaops.ru")
    @XmlElement(name = "User", namespace = "http://javaops.ru", required = true)
    protected ArrayList<User> users;

    @XmlElementWrapper(name = "Projects", namespace = "http://javaops.ru")
    @XmlElement(name = "Project", namespace = "http://javaops.ru", required = true)
    protected ArrayList<Project> projects;

    @XmlElementWrapper(name = "Groups", namespace = "http://javaops.ru")
    @XmlElement(name = "Group", namespace = "http://javaops.ru", required = false)
    protected ArrayList<Group> groups;

    public ArrayList<CityType> getCities() {
        return cities;
    }

    public void setCities(ArrayList<CityType> cities) {
        this.cities = cities;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
