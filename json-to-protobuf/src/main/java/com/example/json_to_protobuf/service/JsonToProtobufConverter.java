package com.example.json_to_protobuf.service;

import com.example.protobuf.SampleOuterClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class JsonToProtobufConverter {

    public static SampleOuterClass.Sample convertJsonToProtobuf(String jsonFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TempSample temp = mapper.readValue(new File(jsonFilePath), TempSample.class);

        return SampleOuterClass.Sample.newBuilder()
                .setName(temp.getName())
                .setAge(temp.getAge())
                .setEmail(temp.getEmail())
                .build();
    }

    public static class TempSample {
        private String name;
        private int age;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
