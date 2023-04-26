package com.example.javafeatures.Repositry.Mapper;

import com.example.javafeatures.Entity.Customer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.StringTypeHandler;

import java.beans.BeanInfo;
import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerRepositry {

    final String Begin = "<script>";
    final String End = "</script>";
    final String WHERE = "WHERE";
    final String SELECT = "SELECT ";
    final String FROM = "FROM Customer";

    final String FindSet =
                    Begin +
                        SELECT +
                            "<if test='LoadFields.isEmpty()'> * </if>" +
                            "<if test='!(LoadFields.isEmpty())'>" +
                                "${LoadFields} "+
                            "</if>" +
                        FROM +
                            "<if test='Filters.isEmpty()'></if>" +
                            "<if test='!(Filters.isEmpty())'>" +
                        WHERE +
                            "<foreach collection='Filters' item='Filter'>" +
                                "${Filter}" +
                            "</foreach>" +
                            "</if>" +
                    End;

    final String IsEmpty =
            Begin +
                    "SELECT EXISTS("+
                        SELECT +
                            "<if test='LoadFields.isEmpty()'> * </if>" +
                            "<if test='!(LoadFields.isEmpty())'>" +
                                "${LoadFields} "+
                            "</if>" +
                        FROM +
                        "<if test='Filters.isEmpty()'></if>" +
                        "<if test='!(Filters.isEmpty())'>" +
                            WHERE +
                                "<foreach collection='Filters' item='Filter'>" +
                                    "${Filter}" +
                                "</foreach>" +
                        "</if>" +
                        "LIMIT 1" +
                    ")"+
                    End;

    final String FindFirst =
            Begin +
                    SELECT +
                    "<if test='LoadFields.isEmpty()'> * </if>" +
                    "<if test='!(LoadFields.isEmpty())'>" +
                    "${LoadFields} "+
                    "</if>" +
                    FROM +
                    "<if test='Filters.isEmpty()'></if>" +
                    "<if test='!(Filters.isEmpty())'>" +
                    WHERE +
                    "<foreach collection='Filters' item='Filter'>" +
                    "${Filter}" +
                    "</foreach>" +
                    "</if>" +
                    "LIMIT 1"+
                    End;

    final String FindLast =
            Begin +
                    SELECT +
                    "<if test='LoadFields.isEmpty()'> * </if>" +
                    "<if test='!(LoadFields.isEmpty())'>" +
                    "${LoadFields} "+
                    "</if>" +
                    FROM +
                    "<if test='Filters.isEmpty()'></if>" +
                    "<if test='!(Filters.isEmpty())'>" +
                    WHERE +
                    "<foreach collection='Filters' item='Filter'>" +
                    "${Filter}" +
                    "</foreach>" +
                    "</if>" +
                    "ORDER BY User_ID DESC "+
                    "LIMIT 1"+
                    End;

    final String Find =
            Begin +
                    SELECT +
                    "<if test='LoadFields.isEmpty()'> * </if>" +
                    "<if test='!(LoadFields.isEmpty())'>" +
                    "${LoadFields} "+
                    "</if>" +
                    FROM +
                    "<if test='Filters.isEmpty()'></if>" +
                    "<if test='!(Filters.isEmpty())'>" +
                    WHERE +
                    "<foreach collection='Filters' item='Filter'>" +
                    "${Filter}" +
                    "</foreach>" +
                    "</if>" +
                    "ORDER BY User_ID DESC "+
                    "LIMIT ${Count}"+
                    End;

    final String Get =
            Begin +
                    SELECT +
                    "<if test='LoadFields.isEmpty()'> * </if>" +
                    "<if test='!(LoadFields.isEmpty())'>" +
                    "${LoadFields} "+
                    "</if>" +
                    FROM +
                    "<if test='Filters.isEmpty()'></if>" +
                    "<if test='!(Filters.isEmpty())'>" +
                    WHERE +
                    "<foreach collection='Filters' item='Filter'>" +
                    "${Filter}" +
                    "</foreach>" +
                    "</if>" +
                    "LIMIT 1"+
                    End;

    final String Count =
            Begin +
                    SELECT +
                    "<if test='LoadFields.isEmpty()'> Count(*) </if>" +
                    "<if test='!(LoadFields.isEmpty())'>" +
                    " Count(${LoadFields}) "+
                    "</if>" +
                    FROM +
                    "<if test='Filters.isEmpty()'></if>" +
                    "<if test='!(Filters.isEmpty())'>" +
                    WHERE +
                    "<foreach collection='Filters' item='Filter'>" +
                    "${Filter}" +
                    "</foreach>" +
                    "</if>" +
                    End;

    final String Insert =
            Begin +
            "INSERT INTO Customer" +
                    "<foreach collection='Fields' item='Field' open='(' separator=',' close=')' >"+
                    "${Field}"+
                    "</foreach>"+
                    "VALUES"+
                    "<foreach collection='Values' item='Value' open='(' separator=',' close=')' >"+
                    "#{Value}"+
                    "</foreach>" +
            End;

    final String InsertWithFullField =
            Begin +
                    "INSERT INTO Customer " +
                    "<foreach collection='Fields' item='Field' open='(' separator=',' close=')' >"+
                    "${Field}"+
                    "</foreach>" +
                    "VALUES"+
                    "<foreach collection='FullValues' item='FullValue' open='(' separator=',' close=')' >"+
                    "<if test='FullValue == null'> null </if>"+
                    "<if test='FullValue != null'> '${FullValue}' </if>"+
                    "</foreach>" +
                    End;

    final String Delete =
            "DELETE FROM Customer WHERE User_ID = #{Customer.userId}";

    final String Modify =
            Begin+
            "UPDATE Customer SET" +
                    "<foreach collection='DiffMap' index='Field' item='Value' separator=','>" +
                    "${Field} = #{Value}" +
                    "</foreach>" +
                    WHERE +
                    " User_ID = #{Customer.userId}"+
                    End;


    @Select(FindSet)
    List<Customer> FindSet(@Param("LoadFields") String LoadFields, @Param("Filters") List<String> Filters);

    @Select(IsEmpty)
    Integer IsEmpty(@Param("LoadFields") String LoadFields, @Param("Filters") List<String> Filters);

    @Select(FindFirst)
    Customer FindFirst(@Param("LoadFields") String LoadFields,@Param("Filters") List<String> Filters);

    @Select(FindLast)
    Customer FindLast(@Param("LoadFields") String LoadFields,@Param("Filters") List<String> Filters);

    @Select(Find)
    List<Customer> Find(@Param("LoadFields") String LoadFields,@Param("Filters") List<String> Filters,@Param("Count") Integer Count);

    @Select(Get)
    Customer Get(@Param("LoadFields") String LoadFields,@Param("Filters") List<String> Filters);

    @Select(Count)
    Integer Count(@Param("LoadFields") String LoadFields,@Param("Filters") List<String> Filters);

    @Insert(Insert)
    Integer Insert(@Param("Fields") List<String> Fields,@Param("Values") List<Object> Values);

    @Insert(InsertWithFullField)
    Integer InsertWithFullField(@Param("Fields") List<String> Fields,@Param("FullValues") List<Object> FullValues);

    @Delete(Delete)
    Integer Delete(@Param("Customer") Customer customer);

    @Update(Modify)
    Integer Modify(@Param("DiffMap") Map<String, Object> DiffMap,@Param("Customer") Customer customer);

    @Select("SELECT Points FROM Customer WHERE Points > 100")
    List<Customer> Test();

}
