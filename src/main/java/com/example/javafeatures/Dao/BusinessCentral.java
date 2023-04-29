package com.example.javafeatures.Dao;

import com.example.javafeatures.Annotation.PK;
import com.example.javafeatures.Dao.MySQLImplementation;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository
public interface NAVRepo<T,E extends Enum<E>> {

    MySQLImplementation<T,E> SetSource(Class<T> tClass) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    MySQLImplementation<T,E> SetRange(E entityFields, String newValue) throws Exception;

    MySQLImplementation<T,E> SetFilter(E entityFields, String sqlExpression, String... newValue) throws Exception;

    MySQLImplementation<T,E> SetLoadFields(E entityFields) throws Exception;

    MySQLImplementation<T,E> SetLoadFields(E... entityFields) throws Exception;

    Boolean IsEmpty();

    List<T> FindSet();

    List<T> FindFirst() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException;

    List<T> FindLast();

    List<T> Find(Integer Count);

    List<T> Get(Object ID);

    Integer Count() throws Exception;

    MySQLImplementation<T,E> Reset();

    MySQLImplementation<T,E> Init();

    T GetRecord();

    T GetX_Record();

    MySQLImplementation<T,E> SetCurrentKey();

    MySQLImplementation<T,E> Validate(E entityFields, Object newValue, Boolean TriggerEvent) throws Exception;

    Boolean Delete();

    Boolean Modify(Boolean UseEvent) throws Exception;

    Boolean Insert(Boolean UseEvent,Boolean FullFields);
}
