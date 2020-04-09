# rewardpoint-app

This reward point app is for 

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
 
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction 
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
 
Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.


# it is build upon using 
      *  Spring, Spring Boot, REST, H2 database
      *  Could verify through Postman request 
      *  It has built in data.sql  (for reward point calculation data )
      
  We can perform all give below through Postman request
      
       *  Create the customer 
       *  Get all customer details
       *  Get all trxn details
       *  Get last three month trxn details
       *  Get reward point details 
       
       
       
###To Run this project locally
```shell
$ git clone https://github.com/RaviKhare/rewardpoint-app.git
$ mvn spring-boot:run

       
      
