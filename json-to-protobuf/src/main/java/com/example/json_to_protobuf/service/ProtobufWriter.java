package com.example.json_to_protobuf.service;

import com.example.protobuf.SampleOuterClass;

import java.io.FileOutputStream;
import java.io.IOException;

public class ProtobufWriter {
    public static void writeProtoToFile(SampleOuterClass.Sample sample, String outputPath) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(outputPath)){
            sample.writeTo(fos);
        }
    }
}
