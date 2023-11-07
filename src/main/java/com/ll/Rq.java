package com.ll;

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

    public int getParamAsInt(String key) {
        try {
            return Integer.parseInt(paramMap.get(key));
        }
        catch (NullPointerException e) {
            return 0;
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }
}
