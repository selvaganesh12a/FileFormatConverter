package com.securin.converters;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Convertor implements XMLJSONConverterI{
    @Override
    public void convertJSONtoXML(File json, File xml) throws IOException {
        String root = "root";
        String jsonstring = readFiletoString(json);
        // System.out.println(jsonstring);
        JSONObject obj = new JSONObject(jsonstring);
        String customXml = convertToCustomXml(obj,"root");
        FileWriter writer = new FileWriter(xml);
        writer.write(customXml);
        writer.close();
    }

    public static String convertToCustomXml(JSONObject obj, String root) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<").append(root).append(">\n");

        for(String key: obj.keySet()){
            Object value = obj.get(key);
            appendValueAsXml(xmlBuilder,key, value,1);
        }

        xmlBuilder.append("</").append(root).append(">");
        return xmlBuilder.toString();
    }

    public static void appendValueAsXml(StringBuilder xmlBuilder, String key, Object value, int indentLevel) {
        String indent = "    ".repeat(indentLevel);
        String nameAttr = (key != null) ? " name=\"" + key + "\"" : "";

        if(value instanceof JSONObject){
            xmlBuilder.append(indent).append("<object").append(nameAttr).append(">\n");
            JSONObject obj = (JSONObject) value;
            for (String i : obj.keySet()) {
                appendValueAsXml(xmlBuilder, i, obj.get(i), indentLevel + 1);
            }
            xmlBuilder.append(indent).append("</object>\n");
        } else if (value instanceof JSONArray){
            xmlBuilder.append(indent).append("<array").append(nameAttr).append(">\n");
            JSONArray arr = (JSONArray) value;
            for (Object item : arr) {
                appendValueAsXml(xmlBuilder, null, item, indentLevel + 1);
            }
            xmlBuilder.append(indent).append("</array>\n");
        } else if (value instanceof String) {
            xmlBuilder.append(indent).append("<string").append(nameAttr).append(">");
            xmlBuilder.append(escapeXml((String) value));
            xmlBuilder.append("</string>\n");
        } else if(value instanceof Number){
            xmlBuilder.append(indent).append("<number").append(nameAttr).append(">");
            xmlBuilder.append(value.toString());
            xmlBuilder.append("</number>\n");
        } else if (value instanceof Boolean) {
            xmlBuilder.append(indent).append("<boolean").append(nameAttr).append(">");
            xmlBuilder.append(value.toString());
            xmlBuilder.append("</boolean>\n");
        } else if (JSONObject.NULL.equals(value)) {
            xmlBuilder.append(indent).append("<null").append(nameAttr).append("/>\n");
        }
    }

    public static String escapeXml(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    public static String readFiletoString(File json) throws IOException{
        StringBuilder string = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(json))){
            String line;
            while((line = br.readLine()) != null){
                string.append(line);
            }
        }
        return string.toString();
    }
}
