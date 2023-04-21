package com.example.javafeatures.Utils;

import io.micrometer.common.util.StringUtils;

public class EntityUtils {

    public static String convertToSnakeCase(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(input.charAt(0)));
        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                result.append("_");
                result.append(Character.toLowerCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }
}
