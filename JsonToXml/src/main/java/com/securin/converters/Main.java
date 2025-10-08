package com.securin.converters;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.err.println("Error: Incorrect number of arguments.");
            System.err.println("Usage: java -jar myprogram.jar <input_json_file> <output_xml_file>");
            System.exit(1);
        }

        try{
            String jsonInputPath = args[0];
            String xmlOutputPath = args[1];
            File inputFile = new File(jsonInputPath);
            File outputFile = new File(xmlOutputPath);
            XMLJSONConverterI converter = ConverterFactory.createXMLJSONConverter();
            converter.convertJSONtoXML(inputFile,outputFile);
            System.out.println("Conversion is done Successfully");
        } catch (Exception e){
            System.err.println("An error occurred during conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
