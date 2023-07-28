# Dmoney-Rest-Assured-API-Automation-using-Selenium-TestNG
This repository consists of API Automation using Rest Assured, Selenium, TestNG, and Java. I have automated the user creation for Customer and Agent, also some transaction-related operations have been done, ex- Send Money, Agent To Customer Deposit, Money Withdrawal, Customer Payment, etc. Also, assertion and negative test cases have been cover for every test cases

## How to run this project

- Clone This project
- open build.gradle file in IntelliJ IDEA
- Type gradle clean test in Terminal and Hit Enter
- Then Hit the follwing command in terminal

```bash
  allure generate allure-results --clean -o allure-report
  allure serve allure-results
```

## Tools and Tech
- Java Programming Language
- IntelliJ IDEA
- Selenium Tool
- Rest Assured Library
- TestNG Framework (Page Object Model Architecture)
- Allure for Report Generation
- Gradle Build System

## Assigned Work Scenario
Do the following steps using the API from this collection:
https://api.postman.com/collections/1844288-143eb923-423f-4c91-a198-fe6e56d20e35?access_key=PMAT-01GJ3CC22Q0066PJWP3T0XHQ8G

1. Do Login by admin
2. Create 2 new customers and an agent
3. Give 2000 tk from the System account to the newly created agent
4. Deposit 1500 tk to a customer from the agent account
5. Withdraw 500 tk by the customer to the agent
6. Send money 500 tk to another customer
7. Payment of 100 tk to a merchant (01686606905) by the recipient customer
8. Check the balance of the recipient customer

Hints:
1. Keep the baseUrl, partnerKey, and token in config.properties file
2. Keep the new customer's and agents' necessary  info in a json array file for chaining APIs

## Test Cases Covered
1. Check that the user can not log in with invalid credentials
2. Check that the user can successfully log in with valid credentials
3. Check that user1/customer1 creation is not possible with an invalid phone number
4. Check that user1/customer1 can be created with valid credentials
5. Check that user2/customer2 creation is not possible using an invalid token
6. Check that user2/customer2 can be created with valid credentials
7. Check that agent can be created with valid credentials
8. Check that it is not possible to deposit money from the system account to the agent account with the wrong method
9. Check that it is possible to do the successful deposit of money from the system account to the agent account
10. Check that it is not possible to deposit money from the agent account to the wrong customer 1 account
11. Check that it is possible to successfully deposit money from the agent account to the correct customer 1 account
12. Check that it is not possible to withdraw money from customer 1 account to a wrong agent account
13. Check that it is possible to successfully withdraw money from customer 1 account to a correct agent account
14. Check that it is not possible to send money from customer 1 account to the wrong customer 2 account
15. Check that it is possible to successfully send money from customer 1 account to a correct customer 2 account
16. Check that it is not possible to do a payment from the customer 2 account to an invalid agent account
17. Check that it is possible to do a successful payment from the customer 2 account to a valid agent account
18. Check that it is not possible to check customer 2 account balance with the wrong method
19. 18. Check that it is possible to successfully check customer 2 account balance with the correct method
 
# Allure report Screenshots
![rest-assured 1](https://github.com/foysal619/Dmoney-Rest-Assured-API-Automation-using-Selenium-TestNG/assets/61048879/48fde995-94e7-4e36-8e2f-70904ab83015)
![rest-assured 2](https://github.com/foysal619/Dmoney-Rest-Assured-API-Automation-using-Selenium-TestNG/assets/61048879/ec4ac2fa-d165-478e-aaef-077d2e6fcc25)
![rest-assured 3](https://github.com/foysal619/Dmoney-Rest-Assured-API-Automation-using-Selenium-TestNG/assets/61048879/71b6db0c-ab9e-410d-a37d-23f5a1a3025b)

# Project Demonstration
https://github.com/foysal619/Dmoney-Rest-Assured-API-Automation-using-Selenium-TestNG/assets/61048879/0e3e970f-1e23-4f5e-b215-b4c228bdf4c6










