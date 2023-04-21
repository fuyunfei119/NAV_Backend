package com.example.javafeatures.Service;

import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Enum.CustomerFields;
import com.example.javafeatures.Repositry.CustomerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRecord record1;
    @Autowired
    private CustomerRecord record2;
    public List<Customer> FindSet() throws NoSuchFieldException {
//        Boolean HasRecord = record1
//                .SetRange(CustomerFields.customerType, "Retail")
//                .SetRange(CustomerFields.accountStatus, "Active")
//                .SetRange(CustomerFields.paymentInformation, "PayPal")
//                .IsEmpty();
//
//        if (HasRecord) {
//                record2.GetFilters();
//            return null;
//        }

        return record2
                .SetRange(CustomerFields.paymentInformation,"PayPal")
                .SetRange(CustomerFields.firstName,"Martin")
                .FindSet();
    }
}
