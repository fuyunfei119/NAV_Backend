package com.example.javafeatures;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEventTest {
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private CustomerRecord customerRecord;
//
//    @Autowired
//    private CustomerRepositry customerRepositry;
//
//    @Test
//    void TypeTest() {
//        System.out.println(customerRepositry.Test());
//    }
//
//    @Test
//    @Transactional
//    void EventTest() {
//        customerRecord
//                .Init()
//                .Insert(true,true);
//    }
//
//    @Test
//    void FindSetTest() throws NoSuchFieldException {
//        List<Customer> customers = customerRecord
//                .Reset()
//                .SetLoadFields(
//                        CustomerFields.firstName,
//                        CustomerFields.lastName
//                )
//                .FindSet();
//
//        System.out.println(customers);
//    }
//
//    @Test
//    @Transactional
//    void InitTest() throws NoSuchFieldException {
//        Boolean insert = customerRecord
//                .Reset()
//                .Init()
//                .Validate(CustomerFields.firstName, "YUNFEI", true)
//                .Validate(CustomerFields.lastName, "FU", true)
//                .Insert(true, true);
//
//        System.out.println(insert);
//    }
//
//    @Test
//    void FindFistTest() throws NoSuchFieldException {
//        Customer customer = customerRecord
//                .Reset()
//                .SetRange(CustomerFields.firstName, "YUNFEI")
//                .FindFirst();
//
//        System.out.println(customer);
//    }
//
//    @Test
//    void Matteotest() throws Exception {
//        List<Customer> customers = customerRecord
//                .Reset()
//                .SetLoadFields(
//                        CustomerFields.userId,
//                        CustomerFields.firstName,
//                        CustomerFields.lastName,
//                        CustomerFields.emailAddress,
//                        CustomerFields.accountStatus)
//                .SetRange(CustomerFields.accountStatus, "Active")
//                .SetFilter(CustomerFields.emailAddress, "*%1*", "doe")
//                .FindSet();
//
//        System.out.println(customers);
//    }
//
//    @Test
//    void Matteotest3() throws Exception {
//        Boolean HasRecord = customerRecord
//                .Reset()
//                .SetLoadFields(
//                        CustomerFields.userId,
//                        CustomerFields.firstName,
//                        CustomerFields.lastName)
//                .SetRange(CustomerFields.accountStatus, "Active")
//                .SetFilter(CustomerFields.firstName, "*%1*", "FEI")
//                .IsEmpty();
//
//        if (HasRecord) {
//            Customer customer = customerRecord.GetRecord();
//            System.out.println(customer);
//        }else {
//            System.out.println("es gibt nicht");
//        }
//    }
//
//    @Test
//    void ModifyTest() {
////        customerRecord
////                .Reset()
////                .Get()
//    }
}
