package com.ll;

import com.ll.standard.util.Ut;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Rq {

    @Getter
    private String action;

    private Map<String, String> paramMap;

    public Rq(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);

        action = cmdBits[0];

        // 쿼리가 존재할 경우
        if (cmdBits.length > 1) {
            String query = cmdBits[1];

            String[] queryBits = query.split("&");

            for (String queryParam : queryBits) {
                String[] queryParamBits = queryParam.split("=", 2);

                if (queryParamBits.length < 2) continue;

                String paramName = queryParamBits[0];
                String paramValue = queryParamBits[1];

                paramMap = new HashMap<>();
                paramMap.put(paramName, paramValue);
            }
        }
    }

    public String getParam(String key) {
        return paramMap != null ? paramMap.get(key) : "";
    }

    public int getParamAsInt(String paramName, int defaultValue) {

        if (paramMap == null) return defaultValue;

        return Ut.Str.parseInt(paramMap.get(paramName), defaultValue);
    }
}