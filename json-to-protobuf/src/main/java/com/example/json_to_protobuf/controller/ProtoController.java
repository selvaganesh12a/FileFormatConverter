package com.example.json_to_protobuf.controller;

import com.example.json_to_protobuf.service.JsonToProtobufConverter;
import com.example.json_to_protobuf.service.ProtobufWriter;
import com.example.protobuf.SampleOuterClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProtoController {

    @GetMapping("/convert")
    public String convertJsonToProto() throws IOException{
        SampleOuterClass.Sample sample = JsonToProtobufConverter.convertJsonToProtobuf("src/main/resources/sample.json");
        ProtobufWriter.writeProtoToFile(sample, "src/main/resources/sample.pb");
        return "Protobuf file is generated Successfully";
    }
}
