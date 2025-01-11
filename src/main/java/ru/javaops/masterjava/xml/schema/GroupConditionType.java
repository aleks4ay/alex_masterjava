
package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "groupConditionType", namespace = "http://javaops.ru")
@XmlEnum
public enum GroupConditionType {

    @XmlEnumValue("registering")
    REGISTERING("registering"),
    @XmlEnumValue("current")
    CURRENT("current"),
    @XmlEnumValue("finished")
    FINISHED("finished");
    private final String value;

    GroupConditionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GroupConditionType fromValue(String v) {
        for (GroupConditionType c: GroupConditionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
