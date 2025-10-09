package com.example.xml_to_json.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmlToJsonCustomConverter {
    public Object convertXmlToJson(String xmlContent) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));
        Element root = doc.getDocumentElement();
        return parseElement(root);
    }

    private Object parseElement(Element element) {
        String tag = element.getTagName();

        switch (tag) {
            case "object":
                return parseObject(element);
            case "array":
                return parseArray(element);
            case "string":
                return element.getTextContent();
            case "number":
                return parseNumber(element.getTextContent());
            case "boolean":
                return Boolean.parseBoolean(element.getTextContent());
            default:
                return null;
        }
    }

    private Map<String,Object> parseObject(Element element) {
        Map<String,Object> map =  new LinkedHashMap<>();
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if(node instanceof Element childElement){
                String key = childElement.getAttribute("name");
                Object value = parseElement(childElement);
                if(key != null && !key.isEmpty()){
                    map.put(key,value);
                }else{
                    map.putAll((Map<String,Object>) value);
                }
            }
        }
        return map;
    }

    private List<Object> parseArray(Element element) {
        List<Object> list = new ArrayList<>();
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if(node instanceof Element childElement){
                list.add(parseElement(childElement));
            }
        }
        return list;
    }

    private Object parseNumber(String text) {
        try{
            if (text.contains(".")) return Double.parseDouble(text);
            else return Integer.parseInt(text);
        } catch (NumberFormatException e){
            return text;
        }
    }

    public String saveJsonToFile(String jsonContent) throws IOException {
        Path outputDir = Paths.get("output");
        if(!Files.exists(outputDir)){
            Files.createDirectory(outputDir);
        }

        String fileName = "output_" + System.currentTimeMillis() + ".json";
        Path filePath = outputDir.resolve(fileName);

        Files.write(filePath, jsonContent.getBytes(StandardCharsets.UTF_8));
        return filePath.toAbsolutePath().toString();
    }
}
