package com.example.myimdb.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CustomFieldSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 在这里进行字符串拼接，例如拼接上一个固定的字符串
        String modifiedValue = "https://image.tmdb.org/t/p/w500" + value;
        gen.writeString(modifiedValue);
    }
}
