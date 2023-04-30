package com.example.javafeatures.Listener;

import com.example.javafeatures.Dao.BusinessCentral;
import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Enum.CustomerFields;
import com.example.javafeatures.Event.Init.OnBeforeInitCustomer;
import com.example.javafeatures.Event.Insert.OnBeforeInsertCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class CustomerEventListener {

    @Autowired
    private BusinessCentral<Customer,CustomerFields> businessCentral;

    @EventListener
    public void OnBeforeInitCustomerEvent(OnBeforeInitCustomer event) throws Exception {
        if (event.getHandled()) return;

        BusinessCentral<Customer,CustomerFields> source = (BusinessCentral<Customer,CustomerFields>) event.getSource();

//        customerRecord = (CustomerRecord) event.getSource();

        source
                .Validate(CustomerFields.userId,UUID.randomUUID().toString(),true)
                .Validate(CustomerFields.accountStatus,"Active",true);
    }

//    @EventListener
//    public void OnAfterInitCustomerEvent(OnAfterInitCustomer event) {
//        if (event.getHandled()) return;
//
//        customerRecord = (CustomerRecord) event.getSource();
//    }
//
    @EventListener
    public void OnBeforeInsertCustomer(OnBeforeInsertCustomer event) throws Exception {
        BusinessCentral<Customer,CustomerFields> source = (BusinessCentral<Customer,CustomerFields>) event.getSource();

        source
                .Validate(CustomerFields.accountCreationDate, Date.valueOf(LocalDate.now()),true);
    }
//
//    @EventListener
//    public void OnBeforeDeleteCustomer(OnBeforeDeleteCustomer event) throws Exception {
//        customerRecord = (CustomerRecord) event.getSource();
//
//        if (CheckIfCustomerHasOverDueBalance()) throw new Exception("This Customer still have over due balance.");
//    }
//
//    private Boolean CheckIfCustomerHasOverDueBalance() {
//        return true;
//    }
}
