package com.example.xml_to_json.controller;

import com.example.xml_to_json.service.XmlToJsonCustomConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/convert")
@Data
public class XmlToJsonController {
    private final XmlToJsonCustomConverter converter;
    private final ObjectMapper mapper = new ObjectMapper();

    public XmlToJsonController(XmlToJsonCustomConverter converter) {
        this.converter = converter;
    }

    @PostMapping("/xml-to-json")
    public Map<String,Object> convertXmlToJson(@RequestParam("file") MultipartFile file) throws Exception{
        String xmlContent = new String(file.getBytes(), StandardCharsets.UTF_8);

        Object jsonStructure = converter.convertXmlToJson(xmlContent);

        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonStructure);

        String savedPath = converter.saveJsonToFile(jsonString);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Conversion successful");
        response.put("output_file_path", savedPath);
        response.put("converted_json", jsonStructure);

        return response;
    }
}
