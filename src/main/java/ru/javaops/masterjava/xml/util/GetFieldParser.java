package ru.javaops.masterjava.xml.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ru.javaops.masterjava.xml.schema.Payload;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.dom.DOMResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GetFieldParser {

    private static final String REGEX_SLASH = "/";
    private final JAXBContext jaxbContext;

    public GetFieldParser(JAXBContext jaxbContext) {
        this.jaxbContext = jaxbContext;
    }

    public List<String> getFieldByTag(String field, Payload document) throws RuntimeException {
        try {
            Document domTree = toXmlDom(document);
            return findField(domTree.getFirstChild(), field);
        } catch (Exception e) {
            throw new RuntimeException("Failed collect buffer", e);
        }
    }

    public static List<String> findField(Node root, String field) {

        List<String> container = new ArrayList<>();
        field = field.trim();
        List<Node> nodes = getElements(root, Arrays.asList(field.split(REGEX_SLASH)));
        if (!nodes.isEmpty()) {
            nodes.forEach(node -> container.add(node.getTextContent().trim()));
        }
        return container;
    }

    private static List<Node> getElements(Node root, List<String> paths) {
        if (checkIfNotNull(paths == null || paths.isEmpty(), root == null)) {
            return Collections.emptyList();
        }

        List<Node> nodes = getDirectChildren(root, paths.get(0));

        if (paths.size() == 1) {
            return nodes;
        } else {
            List<Node> result = new LinkedList<>();
            paths = paths.subList(1, paths.size());

            for (Node node : nodes) {
                result.addAll(getElements(node, paths));
            }

            return result;
        }
    }

    private static boolean checkIfNotNull(boolean b, boolean b2) {
        return b || b2;
    }

    public Document toXmlDom(Object xmlObj) {
        try {
            DOMResult domResult = new DOMResult();
            Marshaller m = jaxbContext.createMarshaller();
            m.marshal(xmlObj, domResult);
            return (Document) domResult.getNode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает коллекцию дочерних тегов первого уровня название которых соответствует <code>nodeName</code>/
     *
     * @param root     нод относительно которого делать отбор дочерних тегов
     * @param nodeName название дочерних тегов подлежащих выборке
     * @return набор тегов
     */
    private static List<Node> getDirectChildren(Node root, String nodeName) {
        List<Node> nodes = new LinkedList<>();
        for (Node child = root.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element && nodeName.equals(child.getNodeName())) {
                nodes.add(child);
            }
        }
        return nodes;
    }
}
