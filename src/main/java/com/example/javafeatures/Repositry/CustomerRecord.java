package com.example.javafeatures.Repositry;

import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Enum.CustomerFields;
import com.example.javafeatures.Repositry.Mapper.CustomerRepositry;
import com.example.javafeatures.Repositry.SQL.SQLStatements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
@Scope("prototype")
public class CustomerRecord {

    @Autowired
    private CustomerRepositry customerRepositry;
    private Map<String,Object> FILTERS = new HashMap<>();
    private StringBuilder FINALFILTER = new StringBuilder();
    private StringBuilder LOADFIELDS = new StringBuilder();

    public Map<String,Object> GetFilters() {
        System.out.println(FILTERS);
        return FILTERS;
    }
    public String GetLoadFilters() { return LOADFIELDS.toString(); }

    public CustomerRecord SetRange(Enum<CustomerFields> Fields, Object newValue) throws NoSuchFieldException {
        Field field = Customer.class.getDeclaredField(Fields.name());
        FILTERS.put(field.getName(),newValue);
        return this;
    }

    private void FiltersParser(Map<String,Object> FILTERS) {
        Iterator<Map.Entry<String, Object>> iterator = FILTERS.entrySet().iterator();

        this.FINALFILTER.append(" WHERE");
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            this.FINALFILTER
                    .append(" ")
                    .append(entry.getKey())
                    .append(" = ")
                    .append(entry.getValue())
                    .append(" "+ SQLStatements.AND);
        }
    }

    public List<Customer> FindSet() {
        if (LOADFIELDS.isEmpty()) {
            LOADFIELDS.append("*");
        }

        if (FILTERS.isEmpty()) {
            return customerRepositry.FindSetWithFilters(LOADFIELDS.toString(),"");
        }

        this.FiltersParser(FILTERS);
        return customerRepositry.FindSetWithFilters(LOADFIELDS.toString(),FINALFILTER.toString());
    }

    public Boolean IsEmpty() {
        if (LOADFIELDS.isEmpty()) {
            LOADFIELDS.append("*");
        }

        if (FILTERS.isEmpty()) {
            return customerRepositry.IsEmptyWithFilters(LOADFIELDS.toString(),"") != 0;
        };

        this.FiltersParser(FILTERS);
        System.out.println("==============================");
        System.out.println("Filters : "+this.FINALFILTER.toString());
        System.out.println("==============================");
        return customerRepositry.IsEmptyWithFilters(this.LOADFIELDS.toString(),this.FINALFILTER.toString()) != 0;
    }
}
