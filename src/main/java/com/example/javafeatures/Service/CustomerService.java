package com.example.javafeatures.Service;

import com.example.javafeatures.Dao.BusinessCentral;
import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Enum.CustomerFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {

    private final BusinessCentral<Customer,CustomerFields> businessCentral;

    @Autowired
    public CustomerService(BusinessCentral<Customer, CustomerFields> businessCentral) {
        this.businessCentral = businessCentral;
    }

    public List<Customer> test() throws NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<Customer> customers = businessCentral
                .SetSource(Customer.class)
                .Reset()
                .SetLoadFields(CustomerFields.userId, CustomerFields.firstName, CustomerFields.lastName, CustomerFields.accountStatus, CustomerFields.customerType)
                .SetRange(CustomerFields.accountStatus, "Active")
                .FindSet();

        return customers;
    }


    @Autowired
    private BusinessCentral<Customer,CustomerFields> record1;
    @Autowired
    private BusinessCentral<Customer,CustomerFields> record2;
    @Autowired
    private BusinessCentral<Customer,CustomerFields> record3;
    @Autowired
    private BusinessCentral<Customer,CustomerFields> record4;
    @Autowired
    private BusinessCentral<Customer,CustomerFields> record5;
    @Autowired
    private BusinessCentral<Customer,CustomerFields> record6;
    @Autowired
    private BusinessCentral<Customer,CustomerFields> record7;
    public List<Customer> FindSet() throws NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        Boolean HasRecord = record1
                .SetSource(Customer.class)
                .Reset()
                .SetLoadFields(CustomerFields.Points)
                .SetRange(CustomerFields.customerType, "Retail")
                .SetRange(CustomerFields.accountStatus, "Active")
                .SetRange(CustomerFields.paymentInformation, "PayPal")
                .IsEmpty();

        if (!HasRecord) {
            return null;
        }

        List<Customer> customers = record2
                .SetSource(Customer.class)
                .SetLoadFields(CustomerFields.userId)
                .SetLoadFields(CustomerFields.firstName)
                .SetLoadFields(CustomerFields.lastName)
                .SetLoadFields(CustomerFields.Points)
                .SetLoadFields(CustomerFields.paymentInformation)
                .SetRange(CustomerFields.paymentInformation, "PayPal")
                .FindSet();

        return customers;

    }

    public List<Customer> CheckIfCustomerHasOver_100_Balance() throws Exception {

        List<Customer> customers = record2
                .SetSource(Customer.class)
                .Reset()
                .SetLoadFields(
                        CustomerFields.userId,
                        CustomerFields.firstName,
                        CustomerFields.lastName,
                        CustomerFields.Points)
                .SetFilter(CustomerFields.Points, "%1..%2|%3", "100","150","75")
                .FindSet();

        return customers;
    }

    public List<Customer> GetFirstCustomerLocatedInUSA() throws Exception {

        List<Customer> customers = record3
                .SetSource(Customer.class)
                .Reset()
                .SetLoadFields(
                        CustomerFields.userId,
                        CustomerFields.firstName,
                        CustomerFields.lastName,
                        CustomerFields.shippingAddress,
                        CustomerFields.phoneNumber,
                        CustomerFields.emailAddress)
                .SetFilter(CustomerFields.emailAddress, "%1*%2", "j", ".com")
                .FindFirst();

        return customers;
    }

    public List<Customer> GetLastRetailCustomer() throws NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        List<Customer> customers = record4
                .SetSource(Customer.class)
                .Reset()
                .SetLoadFields(
                        CustomerFields.userId,
                        CustomerFields.firstName,
                        CustomerFields.lastName,
                        CustomerFields.customerType)
                .SetRange(CustomerFields.customerType, "Retail")
                .FindLast();

        return customers;
    }

    public List<Customer> GetFirst_10_ActiveCustomer() throws Exception {

        List<Customer> customers = record4
                .SetSource(Customer.class)
                .Reset()
                .SetFilter(CustomerFields.lastLoginDate, ">%1", String.valueOf(LocalDate.of(2022,3,1)))
                .Find(10);

        return customers;
    }

    public List<Customer> GetCustomer() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        List<Customer> customers = record5
                .SetSource(Customer.class)
                .Reset()
                .Get("f9f27b13-e7ef-4057-b2ce-dd9a181612b5");

        return customers;

    }

    public Integer CountRetailCustomerType() throws Exception {

        Integer count = record6
                .SetSource(Customer.class)
                .Reset()
                .SetLoadFields(
                        CustomerFields.userId
                )
                .SetRange(CustomerFields.customerType, "Retail")
                .Count();

        return count;

    }

    public List<Customer> FindTop_10_CUstomer() throws Exception {
        List<Customer> customers = record7
                .SetSource(Customer.class)
                .Reset()
                .SetLoadFields(
                        CustomerFields.userId,
                        CustomerFields.firstName,
                        CustomerFields.lastName,
                        CustomerFields.emailAddress)
                .SetFilter(CustomerFields.emailAddress, "*%1*", "doe")
                .Find(1);

        return customers;
    }

    @Transactional
    public Customer InsertAndModify() throws Exception {
        Boolean SuccessModified = false;

         record7
                 .SetSource(Customer.class)
                .Reset()
                .Init()
                .Validate(CustomerFields.firstName, "YUNFEI", true)
                .Validate(CustomerFields.lastName, "FU", true)
                .Insert(true, true);

        List<Customer> customers = record7
                .Reset()
                .SetLoadFields(
                        CustomerFields.firstName,
                        CustomerFields.lastName,
                        CustomerFields.accountStatus)
                .SetRange(CustomerFields.firstName, "YUNFEI")
                .SetRange(CustomerFields.lastName, "FU")
                .FindFirst();

        if (!ObjectUtils.isEmpty(customers)) {
            SuccessModified = record7
                    .Validate(CustomerFields.accountStatus, "Inactive", true)
                    .Modify(false);
        }

        if (!SuccessModified) {
            throw new Exception("Modify Failed.");
        }

        return record7.GetRecord();
    }

}
