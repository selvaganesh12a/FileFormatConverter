package com.example.protobuf_to_json.controller;

import com.example.protobuf.SampleOuterClass;
import com.google.protobuf.util.JsonFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/convert")
public class ProtobufToJsonController {

    @PostMapping(value = "/pb-to-json", consumes = "multipart/form-data")
    public ResponseEntity<String> convertProtobufToJson(@RequestParam("file") MultipartFile file){
        try{
            SampleOuterClass.Sample sample = SampleOuterClass.Sample.parseFrom(file.getInputStream());
            String json = JsonFormat.printer().includingDefaultValueFields().print(sample);

            String outputDir = "src/main/resources/output";
            Files.createDirectories(Paths.get(outputDir));

            String baseName = file.getOriginalFilename();
            if(baseName != null && baseName.contains("."))
                baseName = baseName.substring(0,baseName.lastIndexOf("."));
            else
                baseName = "converted";

            File jsonfile = new File(outputDir + "/" + baseName + ".json");
            try(FileWriter writer = new FileWriter(jsonfile)) {
                writer.write(json);
            }
            String message = String.format(
                    "✅ Protobuf successfully converted to JSON and saved at: %s%n%nJSON Content:%n%s",
                    jsonfile.getAbsolutePath(),
                    json
            );
            return ResponseEntity.ok(message);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Error while converting Protobuf to JSON: " + e.getMessage());
        }
    }
}
