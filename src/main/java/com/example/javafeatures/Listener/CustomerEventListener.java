package com.example.javafeatures.Listener;

import com.example.javafeatures.Enum.CustomerFields;
import com.example.javafeatures.Event.Delete.OnBeforeDeleteCustomer;
import com.example.javafeatures.Event.Init.OnAfterInitCustomer;
import com.example.javafeatures.Event.Init.OnBeforeInitCustomer;
import com.example.javafeatures.Event.Insert.OnBeforeInsertCustomer;
import com.example.javafeatures.Repositry.CustomerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class CustomerEventListener {

    @Autowired
    private CustomerRecord customerRecord;

    @EventListener
    public void OnBeforeInitCustomerEvent(OnBeforeInitCustomer event) throws NoSuchFieldException {
        if (event.getHandled()) return;

        customerRecord = (CustomerRecord) event.getSource();

        customerRecord
                .Validate(CustomerFields.userId,UUID.randomUUID().toString())
                .Validate(CustomerFields.firstName,"Yunfei")
                .Validate(CustomerFields.lastName,"Fu");
    }

    @EventListener
    public void OnAfterInitCustomerEvent(OnAfterInitCustomer event) {
        if (event.getHandled()) return;

        customerRecord = (CustomerRecord) event.getSource();

    }

    @EventListener
    public void OnBeforeInsertCustomer(OnBeforeInsertCustomer event) throws NoSuchFieldException {
        customerRecord = (CustomerRecord) event.getSource();

        customerRecord
                .Validate(CustomerFields.accountCreationDate, Date.valueOf(LocalDate.now()));
    }

    @EventListener
    public void OnBeforeDeleteCustomer(OnBeforeDeleteCustomer event) throws Exception {
        customerRecord = (CustomerRecord) event.getSource();

        if (CheckIfCustomerHasOverDueBalance()) throw new Exception("This Customer still have over due balance.");
    }

    private Boolean CheckIfCustomerHasOverDueBalance() {
        return true;
    }
}
