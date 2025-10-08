package org.example;

import java.io.*;

public class TextToCsvConvertor {
    public static void main(String[] args) {
        String inputPath = "sampleText.txt";
        String outputPath = "outputCsv.csv";
        File inputfile = new File("C:/Users/DELL/Downloads/Securin/File Projects/text-to-csv/src/main/java/org/example/sampleText.txt");
        File outputfile = new File("C:/Users/DELL/Downloads/Securin/File Projects/text-to-csv/src/main/java/org/example/outputCsv.csv");

        try(BufferedReader br = new BufferedReader( new FileReader(inputfile));
            PrintWriter pw = new PrintWriter(new FileWriter(outputfile))){

            String line = br.readLine();
            if(line == null){
                System.out.println("Empty file.");
                return;
            }

            String delimeter;
            if(line.contains(",")) delimeter = ",";
            else if (line.contains("\t")) delimeter = "\t";
            else delimeter = " ";

            String[] data = line.split(delimeter.equals(" ") ? "\\s+" : delimeter);
            pw.println(String.join(",",data));

            while((line = br.readLine()) != null){
                data = line.split(delimeter.equals(" ") ? "\\s+" : delimeter);
                pw.println(String.join(",",data));
            }

            System.out.println("TXT file has converted to CSV Successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
