package com.example.javafeatures.Repositry;

import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Enum.CustomerFields;
import com.example.javafeatures.Repositry.Mapper.CustomerRepositry;
import com.example.javafeatures.Repositry.SQL.SQLStatements;
import com.example.javafeatures.Utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;

@Repository
@Scope("prototype")
public class CustomerRecord {

    @Autowired
    private CustomerRepositry customerRepositry;

    private Map<String,String> FILTERS = new HashMap<>();
    private List<String> LOADFIELDS = new ArrayList<>();

    public CustomerRecord SetRange(Enum<CustomerFields> Fields, String newValue) throws NoSuchFieldException {
        Field field = Customer.class.getDeclaredField(Fields.name());
        FILTERS.put(EntityUtils.convertToSnakeCase(field.getName()),newValue);
        return this;
    }

//    private void FiltersParser(Map<String,Object> FILTERS) {
//        Iterator<Map.Entry<String, Object>> iterator = FILTERS.entrySet().iterator();
//
//        this.FINALFILTER.append(" WHERE");
//        while (iterator.hasNext()) {
//            Map.Entry<String, Object> entry = iterator.next();
//            this.FINALFILTER
//                    .append(" ")
//                    .append(entry.getKey())
//                    .append(" = ")
//                    .append(entry.getValue())
//                    .append(" "+ SQLStatements.AND);
//        }
//    }

//    public Boolean IsEmpty() {
//        if (LOADFIELDS.isEmpty()) {
//            LOADFIELDS.add("*");
//        }
//
//        if (FILTERS.isEmpty()) {
//            return customerRepositry.IsEmptyWithFilters(LOADFIELDS,"") != 0;
//        };
//
//        return customerRepositry.IsEmptyWithFilters(this.LOADFIELDS,this.FILTERS) != 0;
//    }

    public List<Customer> FindSet() {
        return customerRepositry.FindSetWithFilters(LOADFIELDS,FILTERS);
    }

    public List<Customer> test() {
        LOADFIELDS.add(CustomerFields.userId.name());
        LOADFIELDS.add(CustomerFields.firstName.name());
        System.out.println(LOADFIELDS);
        return customerRepositry.FindSet(LOADFIELDS);
    }
}
