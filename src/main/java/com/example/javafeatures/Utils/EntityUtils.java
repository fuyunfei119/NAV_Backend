package com.example.javafeatures.Utils;

import io.micrometer.common.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

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

    public static List<String> getFieldNameList(Object object) {
        List<String> fieldNameList = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(object);
                if (fieldValue != null && !"".equals(fieldValue)) {
                    fieldNameList.add(EntityUtils.convertToSnakeCase(field.getName()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldNameList;
    }

    public static List<Object> getFieldValueList(Object object) {
        List<Object> fieldValueList = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(object);
                if (fieldValue != null && !"".equals(fieldValue)) {
                    fieldValueList.add(fieldValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldValueList;
    }

    public static List<String> getFieldNameList(Object object,Boolean UseFullFields) {
        if (!UseFullFields) {
            return getFieldNameList(object);
        }

        List<String> fieldNameList = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldNameList.add(EntityUtils.convertToSnakeCase(field.getName()));
        }
        return fieldNameList;
    }

    public static List<Object> getFieldValueList(Object object,Boolean UseFullFields) {
        if (!UseFullFields) {
            return getFieldValueList(object);
        }

        List<Object> fieldValueList = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(object);
                fieldValueList.add(fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldValueList;
    }

    public static Map<String, Object> compareObjects(Object obj1, Object obj2) throws IllegalAccessException {
        Class<?> objClass = obj1.getClass();
        Field[] fields = objClass.getDeclaredFields();

        Map<String, Object> diffMap = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj1Value = field.get(obj1);
            Object obj2Value = field.get(obj2);
            if (!Objects.equals(obj1Value, obj2Value)) {
                diffMap.put(EntityUtils.convertToSnakeCase(field.getName()), obj2Value);
            }
        }

        return diffMap;
    }
}
