# Dmoney-API-Automation-With-Rest-Assured


This is Simple API automation of Dmoney API.
Here I have Done Automation Of User creation Like Customer and Agent Creation and Transaction Automation like
Send Money,Agent To Customer Deposit,Money Withdrwal etc.
I have done Assertion on Every Assertion Point and also Cover Negative test Cases.


## Author

- [@Jamil-kawsher1](https://www.github.com/Jamil-kawsher1)




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
- Java
- IntelliJ IDEA
- Rest Assured with TestNG
- Allure
- Gradle


## Test Case:
https://docs.google.com/spreadsheets/d/15qVGhWmMT0aO3B5rr43JsnpjsQQSqazb/edit?usp=sharing&ouid=105413695096846736430&rtpof=true&sd=true


# Allure report Screenshots

![restAssured1](https://user-images.githubusercontent.com/42008531/225357377-2d73b610-1712-49dd-b0a4-05992c510ed2.jpg)

![restAssured2](https://user-images.githubusercontent.com/42008531/225357418-e2d5710f-7509-4429-86a5-4bda90f15203.jpg)





## Scenerio

1. Call login API
2. Create  a new customer and an agent
3. Search by the customer phone number
4. Deposit 5000 tk to the Agent from system
5. Deposit 2000 tk by agent to customer
6. Check balance of customer
7. Check statement by trnxId
8. Withdraw 1000 tk by customer and assert expected balance
9. Send 500 tk to another customer and assert expected balance
10. Check customer statement


