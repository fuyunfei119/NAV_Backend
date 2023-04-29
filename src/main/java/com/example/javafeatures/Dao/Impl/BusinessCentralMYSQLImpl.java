package com.example.javafeatures.Dao;

import com.example.javafeatures.Event.Init.OnAfterInitCustomer;
import com.example.javafeatures.Event.Init.OnBeforeInitCustomer;
import com.example.javafeatures.Event.Insert.OnBeforeInsertCustomer;
import com.example.javafeatures.Event.Modify.OnBeforeModifyCustomer;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.javafeatures.Enum.CustomerFields.*;

@Repository
public class MySQLImplementation<T,E extends Enum<E>> implements NAVRepo<T,E>{

    @Autowired
    private Repositry repositry;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    private final List<String> FILTERS = new ArrayList<>();
    private final List<String> LOADFIELDS = new ArrayList<>();
    private Field PrimaryKey;
    private Object PK_Value;
    private Class<T> tClass;
    private T Entity;
    private T X_Entity;
    private List<T> Entities;
    private List<T> X_Entities;

    @Override
    public MySQLImplementation<T, E> SetSource(Class<T> tClass) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        this.tClass = tClass;
        this.PrimaryKey = getPrimaryKeyField(this.tClass);

        PrimaryKey.setAccessible(true);

        this.Entity = this.tClass.getDeclaredConstructor().newInstance();
        this.X_Entity = this.tClass.getDeclaredConstructor().newInstance();
        return this;
    }

    @Override
    public MySQLImplementation<T,E> SetRange(E entityField, String newValue) throws NoSuchFieldException {

        Field field = tClass.getDeclaredField(entityField.name());

        if (!FILTERS.isEmpty()) {
            FILTERS.add(" AND ");
        }

        FILTERS.add(convertToSnakeCase(field.getName()) + " = " + "'" +newValue + "'");

        return this;
    }

    @Override
    public MySQLImplementation<T,E> SetFilter(E entityField, String sqlExpression, String... newValue) throws Exception {

        Field field = tClass.getDeclaredField(entityField.name());

        if (CountPlaceHolders(sqlExpression) != newValue.length) throw new Exception("the count of parameters does not match the count of placeholders");

        if (!FILTERS.isEmpty()) {
            FILTERS.add(" AND ");
        }

        ParserSQLExpression(sqlExpression,field.getName(),newValue);

        return this;
    }

    @Override
    public MySQLImplementation<T,E> SetLoadFields(E entityField) throws NoSuchFieldException {
        Field field = tClass.getDeclaredField(entityField.name());

        this.LOADFIELDS.add(convertToSnakeCase(field.getName()));

        return this;
    }

    @Override
    public MySQLImplementation<T,E> SetLoadFields(E... entityField) throws NoSuchFieldException {
        for (Enum<E> field : entityField) {
            Field finalField = this.tClass.getDeclaredField(field.name());

            this.LOADFIELDS.add(convertToSnakeCase(finalField.getName()));
        }
        return this;
    }

    @Override
    public Boolean IsEmpty() {
        return repositry.IsEmpty(
                String.join(", ", LOADFIELDS),FILTERS) != 0;
    }

    @Override
    public List<T> FindSet() {

        List list = repositry.FindSet(String.join(", ", LOADFIELDS), FILTERS);

        this.Entities = list;

        return list;
    }

    @Override
    public List<T> FindFirst() {
        List list = (List) repositry.FindFirst(String.join(", ", LOADFIELDS), FILTERS);
        return list;
    }

    @Override
    public List<T> FindLast() {
        return (List) repositry.FindLast(String.join(", ", LOADFIELDS),FILTERS);
    }

    @Override
    public List<T> Find(Integer Count) {
        return (List) repositry.Find(String.join(", ", LOADFIELDS), FILTERS, Count);
    }

    @Override
    public List<T> Get(Object ID) {
        this.FILTERS.clear();
        FILTERS.add(convertToSnakeCase(PrimaryKey.getName() + " = " + "'" + ID + "'"));

        return (List) repositry.Get(String.join(", ", LOADFIELDS),FILTERS);
    }

    @Override
    public Integer Count() throws Exception {

        if (LOADFIELDS.size() > 1) throw new Exception("There are more than one fields within Count expression!");

        return repositry.Count(String.join(", ", LOADFIELDS),FILTERS);
    }

    @Override
    public MySQLImplementation<T,E> Reset() {
        this.FILTERS.clear();
        this.LOADFIELDS.clear();
        BeanUtils.copyProperties(this.Entity,this.X_Entity);
        return this;
    }


    private Integer CountPlaceHolders(String sqlExpression) {
        int count = 0;

        Matcher matcher = Pattern.compile("%\\d+").matcher(sqlExpression);
        while (matcher.find()) {
            count++;
        }

        return count;
    }

    private void ParserSQLExpression(String sqlExpression, String field, String... newValue) throws Exception {
        List<String> placeHolders = new ArrayList<>(Arrays.asList(sqlExpression.split("(?=[|&])|(?<=[|&])")));
        String Convert_Field_Name = convertToSnakeCase(field);
        int lengthOfNewValue = 0;

        String finalFilter = FILTERS.contains(field) ? " AND ": "" ;

        for (String placeHolder : placeHolders) {
            String currentValue = newValue[lengthOfNewValue];

            switch (placeHolder) {
                case "|" -> finalFilter = " OR ";
                case "&" -> finalFilter = " AND ";
                default -> {
                    if (placeHolder.startsWith("%") && (!placeHolder.contains("..")) && (!placeHolder.contains("*"))) {
                        FILTERS.add(finalFilter + Convert_Field_Name + " = " + currentValue);
                    } else if (placeHolder.contains("..")) {
                        FILTERS.add(Convert_Field_Name + " BETWEEN " + currentValue + " AND " + newValue[lengthOfNewValue + 1]);
                        lengthOfNewValue++;
                    } else {
                        String operator;
                        if (placeHolder.contains(">=")) {
                            operator = ">=";
                        } else if (placeHolder.contains("<=")) {
                            operator = "<=";
                        } else if (placeHolder.contains("<>")) {
                            operator = "<>";
                        } else if (placeHolder.contains(">")) {
                            operator = ">";
                        } else if (placeHolder.contains("<")) {
                            operator = "<";
                        } else if (placeHolder.contains("*")) {

                            int firstStar = placeHolder.indexOf("*");

                            if (placeHolder.indexOf("*", firstStar + 1) == -1) {
                                if (placeHolder.contains("%2")) {
                                    FILTERS.add(Convert_Field_Name + " LIKE '" + placeHolder
                                            .replace("*", "%")
                                            .replace("%1", currentValue)
                                            .replace("%2", newValue[lengthOfNewValue + 1]) + "'"
                                    );
                                } else {
                                    FILTERS.add(Convert_Field_Name + " LIKE '" + placeHolder
                                            .replace("*", "%")
                                            .replace("%1", currentValue) + "'"
                                    );
                                }

                            } else {
                                FILTERS.add(Convert_Field_Name + " LIKE '" + placeHolder
                                        .replace("%1", currentValue)
                                        .replace("*", "%") + "'"
                                );
                            }

                            lengthOfNewValue++;
                            break;

                        } else {
                            throw new Exception("IIlegalSQLExpression");
                        }

                        FILTERS.add(finalFilter + Convert_Field_Name + " " + operator + " " + currentValue);
                    }
                    lengthOfNewValue++;
                }
            }
        }
    }

    @Override
    public MySQLImplementation<T,E> Init() {
        eventPublisher.publishEvent(new OnBeforeInitCustomer(this,false));

        eventPublisher.publishEvent(new OnAfterInitCustomer(this,false));
        return this;
    }

    @Override
    public T GetRecord() {
        return this.Entity;
    }

    @Override
    public T GetX_Record() { return this.X_Entity; }

    @Override
    public MySQLImplementation<T,E> SetCurrentKey() {
        return this;
    }

    @Override
    public MySQLImplementation<T,E> Validate(E entityField, Object newValue, Boolean TriggerEvent) throws NoSuchFieldException, IllegalAccessException {

        tClass.getDeclaredField(entityField.name());

        if (entityField.equals(userId)) {
            OnValidateUserID(newValue, TriggerEvent);
        } else if (entityField.equals(phoneNumber)) {
            OnValidatePhoneNumber(newValue, TriggerEvent);
        } else if (entityField.equals(firstName)) {
            OnValidateFirstName(newValue, TriggerEvent);
        } else if (entityField.equals(lastName)) {
            OnValidateLastName(newValue, TriggerEvent);
        } else if (entityField.equals(accountStatus)) {
            OnValidateAccountStatus(newValue, TriggerEvent);
        } else if (entityField.equals(accountCreationDate)) {
            OnValidateAccountCreationDate(newValue, TriggerEvent);
        }

        return this;
    }

    @Override
    public Boolean Modify(Boolean UseEvent) throws IllegalAccessException, NoSuchFieldException {
        if (UseEvent) {
            this.eventPublisher.publishEvent(new OnBeforeModifyCustomer(this));
        }

        Map<String, Object> diffMap = compareObjects(Entity, X_Entity);

        Field field = this.Entity.getClass().getDeclaredField(PrimaryKey.getName());
        field.setAccessible(true);
        this.PK_Value = field.get(this.Entity);

        return repositry.Modify(diffMap,convertToSnakeCase(PrimaryKey.getName()),PK_Value) != 0;
    }

    @Override
    public Boolean Delete() {
        eventPublisher.publishEvent(new OnBeforeInitCustomer(this,false));

        repositry.Delete(this.Entity);
        return true;

    }

    @Override
    public Boolean Insert(Boolean UseEvent,Boolean FullFields) {

        if (UseEvent) {
            this.eventPublisher.publishEvent(new OnBeforeInsertCustomer(this));
        }

        List<String> fieldNameList = getFieldNameList(this.Entity,true);
        List<Object> valueList = getFieldValueList(this.Entity,true);

        Integer count;

        if (FullFields) {
            count = repositry.InsertWithFullField(fieldNameList,valueList);
        }else {
            count = repositry.Insert(fieldNameList,valueList);
        }

        return count != 0;
    }

    private void OnValidateUserID(Object newValue,Boolean TriggerEvent) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.Entity.getClass().getDeclaredField("userId");

        field.setAccessible(true);

        field.set(this.Entity,newValue);
    }

    private void OnValidatePhoneNumber(Object newValue,Boolean TriggerEvent) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.Entity.getClass().getDeclaredField("phoneNumber");

        field.setAccessible(true);

        field.set(this.Entity,newValue);
    }

    private void OnValidateFirstName(Object newValue,Boolean TriggerEvent) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.Entity.getClass().getDeclaredField("firstName");

        field.setAccessible(true);

        field.set(this.Entity,newValue);
    }

    private void OnValidateLastName(Object newValue,Boolean TriggerEvent) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.Entity.getClass().getDeclaredField("lastName");

        field.setAccessible(true);

        field.set(this.Entity,newValue);
    }

    private void OnValidateAccountCreationDate(Object newValue,Boolean TriggerEvent) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.Entity.getClass().getDeclaredField("accountCreationDate");

        field.setAccessible(true);

        field.set(this.Entity,newValue);
    }

    private void OnValidateAccountStatus(Object newValue,Boolean TriggerEvent) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.Entity.getClass().getDeclaredField("accountStatus");

        field.setAccessible(true);

        field.set(this.Entity,newValue);
    }


    private static String convertToSnakeCase(String input) {
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

    private static List<String> getFieldNameList(Object object) {
        List<String> fieldNameList = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(object);
                if (fieldValue != null && !"".equals(fieldValue)) {
                    fieldNameList.add(convertToSnakeCase(field.getName()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldNameList;
    }

    private static List<Object> getFieldValueList(Object object) {
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

    private static List<String> getFieldNameList(Object object,Boolean UseFullFields) {
        if (!UseFullFields) {
            return getFieldNameList(object);
        }

        List<String> fieldNameList = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldNameList.add(convertToSnakeCase(field.getName()));
        }
        return fieldNameList;
    }

    private static List<Object> getFieldValueList(Object object,Boolean UseFullFields) {
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

    private static Map<String, Object> compareObjects(Object obj1, Object obj2) throws IllegalAccessException {
        Class<?> objClass = obj1.getClass();
        Field[] fields = objClass.getDeclaredFields();

        Map<String, Object> diffMap = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj1Value = field.get(obj1);
            Object obj2Value = field.get(obj2);
            if (!Objects.equals(obj1Value, obj2Value)) {
                diffMap.put(convertToSnakeCase(field.getName()), obj2Value);
            }
        }

        return diffMap;
    }


    private static <T> Field getPrimaryKeyField(Class<T> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(com.example.javafeatures.Annotation.PK.class)) {
                return field;
            }
        }

        throw new RuntimeException("Primary key field not found in class " + clazz.getName());
    }
}
