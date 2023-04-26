package com.example.javafeatures.Service;

import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Enum.CustomerFields;
import com.example.javafeatures.Repositry.CustomerRecord;
import com.example.javafeatures.Utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRecord record1;
    @Autowired
    private CustomerRecord record2;
    @Autowired
    private CustomerRecord record3;
    @Autowired
    private CustomerRecord record4;
    @Autowired
    private CustomerRecord record5;
    @Autowired
    private CustomerRecord record6;
    @Autowired
    private CustomerRecord record7;
    public List<Customer> FindSet() throws NoSuchFieldException {

        Boolean HasRecord = record1
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

    public Customer GetFirstCustomerLocatedInUSA() throws Exception {

        Customer customer = record3
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

        return customer;
    }

    public Customer GetLastRetailCustomer() throws NoSuchFieldException {

        Customer customer = record4
                .Reset()
                .SetLoadFields(
                        CustomerFields.userId,
                        CustomerFields.firstName,
                        CustomerFields.lastName,
                        CustomerFields.customerType)
                .SetRange(CustomerFields.customerType, "Retail")
                .FindLast();

        return customer;
    }

    public List<Customer> GetFirst_10_ActiveCustomer() throws Exception {

        List<Customer> customers = record4
                .Reset()
                .SetFilter(CustomerFields.lastLoginDate, ">%1", String.valueOf(LocalDate.of(2022,3,1)))
                .Find(10);

        return customers;
    }

    public Customer GetCustomer() {

        Customer customer = record5
                .Reset()
                .Get("f9f27b13-e7ef-4057-b2ce-dd9a181612b5");

        return customer;

    }

    public Integer CountRetailCustomerType() throws Exception {

        Integer count = record6
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
                .Reset()
                .Init()
                .Validate(CustomerFields.firstName, "YUNFEI", true)
                .Validate(CustomerFields.lastName, "FU", true)
                .Insert(true, true);

        Customer customer = record7
                .Reset()
                .SetLoadFields(
                        CustomerFields.firstName,
                        CustomerFields.lastName,
                        CustomerFields.accountStatus)
                .SetRange(CustomerFields.firstName, "YUNFEI")
                .SetRange(CustomerFields.lastName, "FU")
                .FindFirst();

        if (!ObjectUtils.isEmpty(customer)) {
            SuccessModified = record7
                    .Validate(CustomerFields.accountStatus, "Inactive", true)
                    .Modify(true);
        }

        if (!SuccessModified) {
            throw new Exception("Modify Failed.");
        }

        return record7.GetRecord();
    }

}
