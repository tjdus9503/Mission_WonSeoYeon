package com.ll;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Rq {

    @Getter
    private String action;

    @Getter
    private Map<String, String> paramMap;

    public Rq(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);

        action = cmdBits[0];

        if (cmdBits.length > 1) {
            String query = cmdBits[1];

            String[] queryBits = query.split("&");

            for (String queryParam : queryBits) {
                String[] queryParamBits = queryParam.split("=", 2);

                String paramName = queryParamBits[0];
                String paramValue = queryParamBits[1];

                paramMap = new HashMap<>();
                paramMap.put(paramName, paramValue);
            }
        }

    }
}
