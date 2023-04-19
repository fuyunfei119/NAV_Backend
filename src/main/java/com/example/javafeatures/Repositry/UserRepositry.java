package com.example.javafeatures.Repositry;

import com.example.javafeatures.Entity.User;

import com.example.javafeatures.Enum.UserFields;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;


@Repository
@Scope("prototype")
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

    public List<User> IsEmpty() {
        if (FILTERS.isEmpty()) return null;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRepositry that = (UserRepositry) o;

        if (!TABLENAME.equals(that.TABLENAME)) return false;
        if (!Objects.equals(FILTERS, that.FILTERS)) return false;
        return Objects.equals(FINALFILTER, that.FINALFILTER);
    }

    @Override
    public int hashCode() {
        int result = TABLENAME.hashCode();
        result = 31 * result + (FILTERS != null ? FILTERS.hashCode() : 0);
        result = 31 * result + (FINALFILTER != null ? FINALFILTER.hashCode() : 0);
        return result;
    }
}
