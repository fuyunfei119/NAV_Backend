package com.example.javafeatures.Repositry;

import com.example.javafeatures.Entity.User;

import com.example.javafeatures.Enum.UserFields;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepositry {

    private final String TABLENAME = User.class.getName();

    private Map<String,Object> FILTERS = new HashMap<>();
    private StringBuilder FINALFILTER = new StringBuilder();

    public UserRepositry SetRange(Enum<UserFields> Fields,Object newValue) throws NoSuchFieldException {
        Field field = User.class.getDeclaredField(Fields.name());
        FILTERS.put(field.getName(),newValue);
        return this;
    }

    public Map<String,Object> GetFilters() {
        System.out.println(FILTERS);
        return FILTERS;
    }

    public List<User> FindSet() {
        if (FILTERS.isEmpty()) return null;

        Iterator<Map.Entry<String, Object>> iterator = FILTERS.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            FINALFILTER
                    .append(" "+SQLStatements.AND+" ")
                    .append(entry.getKey())
                    .append(" = ")
                    .append(entry.getValue());
        }

        return null;
    }
}
