DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer (
    User_ID VARCHAR(50) NOT NULL PRIMARY KEY,
    First_Name VARCHAR(50) NOT NULL,
    Last_Name VARCHAR(50) NOT NULL,
    Email_Address VARCHAR(255) NOT NULL,
    Phone_Number VARCHAR(20) NOT NULL,
    Billing_Address VARCHAR(255) NOT NULL,
    Shipping_Address VARCHAR(255) NOT NULL,
    Account_Creation_Date DATE NOT NULL,
    Last_Login_Date DATE,
    Account_Status ENUM('Active', 'Suspended', 'Closed') NOT NULL,
    Payment_Information VARCHAR(255) NOT NULL,
    Order_History TEXT,
    Points INT DEFAULT 0,
    Customer_Type ENUM('Retail', 'Wholesale') NOT NULL
);

INSERT INTO Customer (User_ID,First_Name, Last_Name, Email_Address, Phone_Number, Billing_Address, Shipping_Address, Account_Creation_Date, Last_Login_Date, Account_Status, Payment_Information, Order_History, Points, Customer_Type) VALUES
    ('f9f27b13-e7ef-4057-b2ce-dd9a181612b5','John', 'Doe', 'john.doe@example.com', '555-1234', '123 Main St, Anytown USA', '123 Main St, Anytown USA', '2022-01-01', '2022-03-15', 'Active', 'Credit Card', 'Order #12345, Order #12346', 100, 'Retail'),
    ('adf13a27-c4bd-415e-aa34-63173457a504','Jane', 'Doe', 'jane.doe@example.com', '555-5678', '456 Elm St, Anytown USA', '456 Elm St, Anytown USA', '2022-01-02', '2022-03-15', 'Active', 'PayPal', 'Order #12347, Order #12348', 150, 'Retail'),
    ('c84712d4-2e0f-4f7a-9f44-c41908c49185','Bob', 'Smith', 'bob.smith@example.com', '555-9012', '789 Oak St, Anytown USA', '789 Oak St, Anytown USA', '2022-01-03', '2022-03-14', 'Suspended', 'Credit Card', 'Order #12349, Order #12350', 75, 'Retail'),
    ('b51df661-f5d6-4c08-b400-8ab09de7b40b','Samantha', 'Jones', 'samantha.jones@example.com', '555-3456', '321 Pine St, Anytown USA', '321 Pine St, Anytown USA', '2022-01-04', '2022-03-14', 'Active', 'Credit Card', 'Order #12351, Order #12352', 200, 'Retail'),
    ('0d2e618e-9a5c-4943-af42-cd7aa07b793a','Michael', 'Williams', 'michael.williams@example.com', '555-7890', '654 Cedar St, Anytown USA', '654 Cedar St, Anytown USA', '2022-01-05', '2022-03-13', 'Active', 'PayPal', 'Order #12353, Order #12354', 50, 'Retail'),
    ('3165bc21-62b6-41d3-87ce-375b219958fc','Emily', 'Davis', 'emily.davis@example.com', '555-2345', '987 Maple St, Anytown USA', '987 Maple St, Anytown USA', '2022-01-06', '2022-03-13', 'Active', 'Credit Card', 'Order #12355, Order #12356', 125, 'Retail'),
    ('5979cbcf-c134-4590-8b69-9cf780f6c919','James', 'Brown', 'james.brown@example.com', '555-6789', '246 Oak St, Anytown USA', '246 Oak St, Anytown USA', '2022-01-07', '2022-03-12', 'Active', 'PayPal', 'Order #12357, Order #12358', 75, 'Retail'),
    ('1cce9e0a-6474-41c3-8e11-d40002c60283','Sarah', 'Taylor', 'sarah.taylor@example.com', '555-0123', '135 Elm St, Anytown USA', '135 Elm St, Anytown USA', '2022-01-08', '2022-03-12', 'Active', 'Credit Card', 'Order #12359, Order #12360', 150, 'Retail'),
    ('fce14438-b324-44a6-b5c8-1b12730aeeb1','David', 'Johnson', 'david.johnson@example.com', '555-4567', '864 Cedar St, Anytown USA', '864 Cedar St, Anytown USA', '2022-01-09', '2022-03-11', 'Closed', 'Credit Card','Order #12361, Order #12362', 100, 'Wholesale'),
    ('df08b4f7-fef8-47b1-9c5f-67f26f2b7dd5','Linda', 'Wilson', 'linda.wilson@example.com', '555-8901', '975 Pine St, Anytown USA', '975 Pine St, Anytown USA', '2022-01-11', '2022-03-10', 'Active', 'PayPal', 'Order #12363, Order #12364', 200, 'Retail'),
    ('f51de039-4a1f-4189-a68c-17cf738fe225','Brian', 'Davis', 'brian.davis@example.com', '555-2345', '753 Maple St, Anytown USA', '753 Maple St, Anytown USA', '2022-01-12', '2022-03-10', 'Active', 'Credit Card', 'Order #12365, Order #12366', 150, 'Retail'),
    ('d5b3df04-1d0e-4f2a-9a73-676a028d0732','Jessica', 'Johnson', 'jessica.johnson@example.com', '555-6789', '159 Oak St, Anytown USA', '159 Oak St, Anytown USA', '2022-01-13', '2022-03-09', 'Active', 'PayPal', 'Order #12367, Order #12368', 100, 'Retail'),
    ('75e88a8c-9bed-4667-a293-4c3406ea7afd','Karen', 'Miller', 'karen.miller@example.com', '555-0123', '753 Elm St, Anytown USA', '753 Elm St, Anytown USA', '2022-01-14', '2022-03-09', 'Active', 'Credit Card', 'Order #12369, Order #12370', 50, 'Retail'),
    ('c50b71e1-77b9-4d38-918c-c3023b199968','Matthew', 'Wilson', 'matthew.wilson@example.com', '555-4567', '357 Cedar St, Anytown USA', '357 Cedar St, Anytown USA', '2022-01-15', '2022-03-08', 'Active', 'Credit Card', 'Order #12371, Order #12372', 125, 'Retail'),
    ('ea4dd2a3-9f74-4c83-a9d8-3aa0b8898ddf','Ashley', 'Davis', 'ashley.davis@example.com', '555-8901', '753 Pine St, Anytown USA', '753 Pine St, Anytown USA', '2022-01-16', '2022-03-08', 'Active', 'PayPal', 'Order #12373, Order #12374', 75, 'Retail'),
    ('f4a7c668-9744-4da8-98ff-9bd457e333e4','William', 'Miller', 'william.miller@example.com', '555-2345', '951 Maple St, Anytown USA', '951 Maple St, Anytown USA', '2022-01-17', '2022-03-07', 'Active', 'Credit Card', 'Order #12375, Order #12376', 150, 'Retail'),
    ('ad1e54be-4b89-4dd2-82d3-56a217623f6b','Elizabeth', 'Wilson', 'elizabeth.wilson@example.com', '555-6789', '753 Oak St, Anytown USA', '753 Oak St, Anytown USA', '2022-01-18', '2022-03-07', 'Active', 'PayPal', 'Order #12377, Order #12378', 100, 'Retail'),
    ('ad87d780-b3c8-4c61-8081-81b3fba960be','Brandon', 'Davis', 'brandon.davis@example.com', '555-0123', '357 Elm St, Anytown USA', '357 Elm St, Anytown USA', '2022-01-19', '2022-03-06', 'Active', 'Credit Card', 'Order #12379, Order #12380', 200, 'Retail'),
    ('a50d20fc-4c78-48de-a1a5-928ce55949da','Megan', 'Johnson', 'megan.johnson@example.com', '555-4567', '357 Pine St, Anytown USA', '357 Pine St, Anytown USA','2022-01-20', '2022-03-06', 'Active', 'PayPal', 'Order #12381, Order #12382', 125, 'Retail'),
    ('47b8d196-eca1-4c53-a908-9b071c0c3179','James', 'Miller', 'james.miller@example.com', '555-8901', '753 Cedar St, Anytown USA', '753 Cedar St, Anytown USA', '2022-01-21', '2022-03-05', 'Active', 'Credit Card', 'Order #12383, Order #12384', 50, 'Retail'),
    ('66792bc5-e23e-4850-80f7-9a369ea4590d','Emma', 'Wilson', 'emma.wilson@example.com', '555-2345', '753 Oak St, Anytown USA', '753 Oak St, Anytown USA', '2022-01-22', '2022-03-05', 'Active', 'PayPal', 'Order #12385, Order #12386', 75, 'Retail'),
    ('74e2ccfe-76c0-4cf6-8842-3adddd66c76f','Ryan', 'Davis', 'ryan.davis@example.com', '555-6789', '357 Elm St, Anytown USA', '357 Elm St, Anytown USA', '2022-01-23', '2022-03-04', 'Active', 'Credit Card', 'Order #12387, Order #12388', 150, 'Retail'),
    ('1eeaf632-b66a-43c9-b51e-2cf9909a67a7','Grace', 'Miller', 'grace.miller@example.com', '555-0123', '951 Maple St, Anytown USA', '951 Maple St, Anytown USA', '2022-01-24', '2022-03-04', 'Active', 'PayPal', 'Order #12389, Order #12390', 100, 'Retail'),
    ('e724fd73-3c68-4158-808f-d5df4f075702','Michael', 'Wilson', 'michael.wilson@example.com', '555-4567', '357 Cedar St, Anytown USA', '357 Cedar St, Anytown USA', '2022-01-25', '2022-03-03', 'Active', 'Credit Card', 'Order #12391, Order #12392', 200, 'Retail'),
    ('0fc4b4ca-f680-4345-9248-ca9fc0f2d5eb','Olivia', 'Davis', 'olivia.davis@example.com', '555-8901', '753 Pine St, Anytown USA', '753 Pine St, Anytown USA', '2022-01-26', '2022-03-03', 'Active', 'PayPal', 'Order #12393, Order #12394', 125, 'Retail'),
    ('f2d77139-5f41-46a6-ac25-3e237bad0e97','Christopher', 'Miller', 'christopher.miller@example.com', '555-2345', '753 Maple St, Anytown USA', '753 Maple St, Anytown USA', '2022-01-27', '2022-03-02', 'Active', 'Credit Card', 'Order #12395, Order #12396', 50, 'Retail'),
    ('52270a21-c888-4255-b8c7-842e77652ff8','Sophia', 'Wilson', 'sophia.wilson@example.com', '555-6789', '159 Oak St, Anytown USA', '159 Oak St, Anytown USA', '2022-01-28', '2022-03-02', 'Active', 'PayPal', 'Order #12397, Order #12398', 75, 'Retail'),
    ('fcab65da-c0b2-4491-8f1c-76822788ce8c','Tyler', 'Davis', 'tyler.davis@example.com', '555-0123', '357 Elm St, Anytown USA', '357 Elm St, Anytown USA', '2022-01-29', '2022-03-01', 'Active', 'Credit Card', 'Order #12399, Order #12400', 150, 'Retail');