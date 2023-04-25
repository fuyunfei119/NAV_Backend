package com.example.javafeatures.Repositry;

import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Enum.CustomerFields;
import com.example.javafeatures.Event.Init.OnAfterInitCustomer;
import com.example.javafeatures.Event.Init.OnBeforeInitCustomer;
import com.example.javafeatures.Event.Insert.OnBeforeInsertCustomer;
import com.example.javafeatures.Repositry.Mapper.CustomerRepositry;
import com.example.javafeatures.Utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@Scope("prototype")
public class CustomerRecord {

    @Autowired
    private CustomerRepositry customerRepositry;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    private final List<String> FILTERS = new ArrayList<>();
    private final List<String> LOADFIELDS = new ArrayList<>();
    private final String PrimaryKey = String.valueOf(CustomerFields.userId);
    private Customer customer;

    public CustomerRecord SetRange(Enum<CustomerFields> Fields, String newValue) throws NoSuchFieldException {
        Field field = Customer.class.getDeclaredField(Fields.name());

        if (!FILTERS.isEmpty()) {
            FILTERS.add(" AND ");
        }

        FILTERS.add(
                 EntityUtils.convertToSnakeCase(field.getName()) + " = " + "'" +newValue + "'"
        );

        return this;
    }

    public CustomerRecord SetFilter(Enum<CustomerFields> Fields,String sqlExpression, String... newValue) throws Exception {

        Field field = Customer.class.getDeclaredField(Fields.name());

        if (CountPlaceHolders(sqlExpression) != newValue.length) throw new Exception("the count of parameters does not match the count of placeholders");

        ParserSQLExpression(sqlExpression,field.getName(),newValue);

        return this;
    }

    public CustomerRecord SetLoadFields(Enum<CustomerFields> Fields) throws NoSuchFieldException {
        Field field = Customer.class.getDeclaredField(Fields.name());

        this.LOADFIELDS.add(EntityUtils.convertToSnakeCase(field.getName()));

        return this;
    }

    public CustomerRecord SetLoadFields(Enum<CustomerFields>... Fields) throws NoSuchFieldException {
        for (Enum<CustomerFields> field : Fields) {

            Field finalField = Customer.class.getDeclaredField(field.name());

            this.LOADFIELDS.add(EntityUtils.convertToSnakeCase(finalField.getName()));
        }

        return this;
    }

    public Boolean IsEmpty() {
        return customerRepositry.IsEmpty(
                String.join(", ", LOADFIELDS),FILTERS) != 0;
    }

    public List<Customer> FindSet() {
        return customerRepositry.FindSet(
                String.join(", ", LOADFIELDS),FILTERS);
    }

    public Customer FindFirst() {
        return customerRepositry.FindFirst(
                String.join(", ", LOADFIELDS),FILTERS);
    }

    public Customer FindLast() {
        return customerRepositry.FindLast(
                String.join(", ", LOADFIELDS),FILTERS);
    }

    public List<Customer> Find(Integer Count) {
         return customerRepositry.Find(
                 String.join(", ", LOADFIELDS),
                 FILTERS,
                 Count
         );
    }

    public Customer Get(String ID) {

        this.FILTERS.clear();
        FILTERS.add(
                EntityUtils.convertToSnakeCase(PrimaryKey) + " = " + "'" + ID + "'"
        );

        return customerRepositry.Get(
                String.join(", ", LOADFIELDS),FILTERS);
    }

    public Integer Count() throws Exception {

        if (LOADFIELDS.size() > 1) throw new Exception("There are more than one fields within Count expression!");

        return customerRepositry.Count(
                String.join(", ", LOADFIELDS),FILTERS);
    }

    public CustomerRecord Reset() {
        this.FILTERS.clear();
        this.LOADFIELDS.clear();
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
        String Convert_Field_Name = EntityUtils.convertToSnakeCase(field);
        int lengthOfNewValue = 0;

        String finalFilter = FILTERS.contains(field) ? " AND ": "" ;

        for (String placeHolder : placeHolders) {
            String currentValue = newValue[lengthOfNewValue];

            System.out.println(placeHolder);

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

    public CustomerRecord Init() {
        this.customer = new Customer();

        eventPublisher.publishEvent(new OnBeforeInitCustomer(this,false));

        this.customer.setAccountStatus("Active");
        this.customer.setLastLoginDate(Date.valueOf(LocalDate.now()));

        eventPublisher.publishEvent(new OnAfterInitCustomer(this,false));

        return this;
    }

    public Customer GetRecord() {
        return this.customer;
    }

    public CustomerRecord SetCurrentKey() {
        return this;
    }

    public CustomerRecord Validate(CustomerFields customerFields, Object newValue) throws NoSuchFieldException {

        Customer.class.getDeclaredField(customerFields.name());

        switch (customerFields) {
            case userId -> OnValidateUserID(newValue);
            case phoneNumber -> OnValidatePhoneNumber(newValue);
            case firstName -> OnValidateFirstName(newValue);
            case lastName -> OnValidateLastName(newValue);
            case accountCreationDate -> OnValidateAccountCreationDate(newValue);
        }

        return this;
    }
    public Boolean Delete() {
        eventPublisher.publishEvent(new OnBeforeInitCustomer(this,false));

        customerRepositry.Delete(this.customer);
        return true;

    }

    public Boolean Modify() { return true; }

    public Boolean Insert(Boolean UseEvent,Boolean FullFields) {

        if (UseEvent) {
            this.eventPublisher.publishEvent(new OnBeforeInsertCustomer(this));
        }

        List<String> fieldNameList = EntityUtils.getFieldNameList(this.customer,true);
        List<Object> valueList = EntityUtils.getFieldValueList(this.customer,true);

        Integer count;

        if (FullFields) {
            count = customerRepositry.InsertWithFullField(fieldNameList,valueList);
        }else {
            count = customerRepositry.Insert(fieldNameList,valueList);
        }

        return count != 0;
    }

    private void OnValidateUserID(Object newValue) {
        this.customer.setUserId(newValue.toString());
    }

    private void OnValidatePhoneNumber(Object newValue) {
        this.customer.setPhoneNumber(newValue.toString());
    }

    private void OnValidateFirstName(Object newValue) {
        this.customer.setFirstName(newValue.toString());
    }

    private void OnValidateLastName(Object newValue) {
        this.customer.setLastName(newValue.toString());
    }

    private void OnValidateAccountCreationDate(Object newValue) {
        this.customer.setAccountCreationDate((Date) newValue);
    }
}
