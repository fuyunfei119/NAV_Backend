package com.example.javafeatures.Dao;

import com.example.javafeatures.Dao.Impl.BusinessCentralMYSQLImpl;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository
public interface BusinessCentral<T,E extends Enum<E>> {

    BusinessCentralMYSQLImpl<T,E> SetSource(Class<T> tClass) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    BusinessCentralMYSQLImpl<T,E> SetRange(E entityFields, String newValue) throws Exception;

    BusinessCentralMYSQLImpl<T,E> SetFilter(E entityFields, String sqlExpression, String... newValue) throws Exception;

    BusinessCentralMYSQLImpl<T,E> SetLoadFields(E entityFields) throws Exception;

    BusinessCentralMYSQLImpl<T,E> SetLoadFields(E... entityFields) throws Exception;

    Boolean IsEmpty();

    List<T> FindSet();

    List<T> FindFirst() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException;

    List<T> FindLast();

    List<T> Find(Integer Count);

    List<T> Get(Object ID);

    Integer Count() throws Exception;

    BusinessCentralMYSQLImpl<T,E> Reset();

    BusinessCentralMYSQLImpl<T,E> Init();

    T GetRecord();

    T GetX_Record();

    BusinessCentralMYSQLImpl<T,E> SetCurrentKey();

    BusinessCentralMYSQLImpl<T,E> Validate(E entityFields, Object newValue, Boolean TriggerEvent) throws Exception;

    Boolean Delete();

    Boolean Modify(Boolean UseEvent) throws Exception;

    Boolean Insert(Boolean UseEvent,Boolean FullFields);
}
