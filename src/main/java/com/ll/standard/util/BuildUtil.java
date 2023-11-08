package com.ll.standard.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BuildUtil {
    public static String toJsonAsString(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = "";

        try {
            jsonData = objectMapper.writeValueAsString(obj);
        }
        catch (IOException e) {
            System.out.println("JSON 변환 오류");
        }

        return jsonData;
    }
}
