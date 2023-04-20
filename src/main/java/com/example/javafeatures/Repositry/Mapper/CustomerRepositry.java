package com.example.javafeatures.Repositry.Mapper;

import com.example.javafeatures.Entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerRepositry {

    @Select("SELECT #{LoadFields} FROM Customer #{Filters}")
    List<Customer> FindSetWithFilters(@Param("LoadFields") String LoadFields, @Param("Filters") String Filters);

    @Select("SELECT EXISTS(SELECT #{LoadFields} FROM Customer #{Filters})")
    Integer IsEmptyWithFilters(@Param("LoadFields") String LoadFields, @Param("Filters") String Filters);


}
