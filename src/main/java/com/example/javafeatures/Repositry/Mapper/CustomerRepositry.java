package com.example.javafeatures.Repositry.Mapper;

import com.example.javafeatures.Entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerRepositry {

    final String SQL =
            "<script>" +
                    "SELECT " +
                    "<foreach collection='LoadFields' item='LoadField' separator=','>" +
                    "#{LoadField}" +
                    "</foreach>" +
                    "FROM customer" +
            "</script>";

    final String FindSetWithFilters =
            "<script> " +
                    "SELECT " +
                    "<if test='LoadFields.isEmpty()'> * </if>" +
                    "<if test='!(LoadFields.isEmpty())'>" +
                        "<foreach collection='LoadFields' item='LoadField' separator=',' open='(' close=')'>" +
                            "#{LoadField}" +
                        "</foreach>" +
                    "</if>" +
                    "FROM customer" +
                    "<if test='Filters.isEmpty()'></if>" +
                    "<if test='!(Filters.isEmpty())'>" +
                    "WHERE " +
                    "<foreach collection='Filters' item='item' index='key' separator=' AND '>" +
                    "${key} = #{item}" +
                    "</foreach>" +
                    "</if>" +
            "</script>";

    final String IsEmptyWithoutFilters =
            "<script> " +
                    "SELECT " +
                    "<foreach collection='LoadFields' item='LoadField' separator=','>" +
                    "#{LoadField}" +
                    "</foreach>" +
                    "FROM customer" +
                    "WHERE" +
                    "<foreach collection='Filters.entrySet()' index='FilterName' item='FilterValue' separator=','>" +
                    "${FilterName} = #{FilterValue}" +
                    "</foreach> " +
                    "</script>";

    @Select(SQL)
    List<Customer> FindSet(@Param("LoadFields") List<String> LoadFields);

    @Select(FindSetWithFilters)
    List<Customer> FindSetWithFilters(@Param("LoadFields") List<String> LoadFields, @Param("Filters") Map<String,String> Filters);

    @Select(IsEmptyWithoutFilters)
    List<Customer> IsEmptyWithoutFilters(@Param("LoadFields") List<String> LoadFields);

    @Select("SELECT EXISTS(SELECT #{LoadFields} FROM Customer #{Filters})")
    Integer IsEmptyWithFilters(@Param("LoadFields") List<String> LoadFields, @Param("Filters") String Filters);

    @Select("SELECT * FROM customer WHERE payment_Information = 'PayPal'")
    List<Customer> test();


}
