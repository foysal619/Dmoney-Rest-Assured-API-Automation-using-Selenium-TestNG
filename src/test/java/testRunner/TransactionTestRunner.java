package testRunner;

import controller.Transaction;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setup.Setup;
import utils.Utils;

import java.io.IOException;


public class TransactionTestRunner extends Setup {

    static  Transaction transaction;

    static {
        try {
            transaction = new Transaction();
            intitconfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //Unsuccessful System To Agent Account Transaction With Invalid get method
    @Test(priority = 1, description = "Unsuccessful System To Agent Account Transaction With Invalid get method",enabled = true)
    public void systemToUserTransactionWithInvalidMethod () throws ConfigurationException, InterruptedException, IOException, ParseException {
        Thread.sleep(5000);
        boolean methodFlag = false;

        Response res = transaction.doTransactionFromSystemToAgent(methodFlag);
        JsonPath jsonPath = res.jsonPath();
        String erroMessage = jsonPath.get("error.message");
        System.out.println(res.asString());

        Assert.assertTrue(erroMessage.contains("Not Found"));

    }

    //Successful Transaction from System Account To Agent Account: Deposit 2000tk from system to agent
    @Test(priority = 2, description = "Successful Transaction from System Account To Agent Account: Deposit 2000tk from system to agent",enabled = true)
    public void systemToAgentTransaction () throws ConfigurationException, IOException, ParseException, InterruptedException {
        boolean methodFlag = true;

        Response res = transaction.doTransactionFromSystemToAgent(methodFlag);
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(res.asString());
        Assert.assertTrue(message.contains("Deposit successful"));


    }

    //Unsuccessful Agent To Customer 1 deposit with Invalid Customer Phone Number
    @Test(priority = 3, description = "Unsuccessful Agent To Customer deposit with Invalid Customer Number",enabled = true)
    public void agentToCustomer1TransactionWithInvalidPhoneNumber () throws ConfigurationException, IOException, InterruptedException {
        intitconfig();
        Thread.sleep(3000);
        String agentPhoneNumber = prop.getProperty("createdAgentPhone");
        String customerPhoneNumber = "01819677097";


        Response res = transaction.agentToCustomerTransaction(agentPhoneNumber, customerPhoneNumber, 2000);
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(res.asString());
        Assert.assertTrue(message.contains("Account does not exist"));

    }

    //Successful Transaction from Agent to Customer 1: Deposit 1500tk to customer 1 from agent
    @Test(priority = 4, description = "Successful Transaction from Agent to Customer: Deposit 1500tk to customer 1 from agent",enabled = true)
    public void agentToCustomerTransaction () throws ConfigurationException, InterruptedException, IOException {
        intitconfig();
        Thread.sleep(3000);
        String agentPhoneNumber = prop.getProperty("createdAgentPhone");
        String customerPhoneNumber = prop.getProperty("createdUserPhone");

        Response res = transaction.agentToCustomerTransaction(agentPhoneNumber, customerPhoneNumber, 1500);
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertTrue(message.contains("Deposit successful"));
        System.out.println(res.asString());
        System.out.println(message);
    }

    //Unsuccessful Money Withdrawal By Customer to An invalid Agent number
    @Test(priority = 5, description = "Unsuccessful Money Withdrawal By Customer to An invalid Agent number",enabled = true)
    public void moneyWithdrawalByCustomerToAnInvalidAgentNumber () throws InterruptedException {
        String customerPhoneNumber = prop.getProperty("createdUserPhone");
        String agentPhoneNumber = "01819677097";
        int amount = 500;

        Response res = transaction.moneyWithdrawalByCustomer(customerPhoneNumber, agentPhoneNumber, amount);
        Thread.sleep(3000);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");

        Assert.assertTrue(message.contains("Account does not exist"));
    }

    //Successful Money Withdrawal By Customer 1: Withdraw 500tk by the customer 1 to the agent
    @Test(priority = 6, description = "Successful Money Withdrawal By Customer 1: Withdraw 500tk by the customer 1 to the agent",enabled = true)
    public void moneyWithdrawalByCustomer () throws InterruptedException, IOException {
        intitconfig();
        Thread.sleep(3000);
        String customerPhoneNumber = prop.getProperty("createdUserPhone");
        String agentPhoneNumber = prop.getProperty("createdAgentPhone");
        int amount = 500;

        Response res = transaction.moneyWithdrawalByCustomer(customerPhoneNumber, agentPhoneNumber, amount);
        intitconfig();
        Thread.sleep(3000);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertTrue(message.contains("Withdraw successful"));

    }


    //Unsuccessful Send money To an invalid  Customer 2 Number
    @Test(priority = 7, description = "Unsuccessful Send money To an invalid  Customer 2 Number",enabled = true)
    public void sendMoneyToAnInvalidCustomerNumber () throws InterruptedException {
        String fromNumber = prop.getProperty("createdUserPhone");
        String toNumber = "01819677097";
        int amount = 500;

        Thread.sleep(3000);
        Response res = transaction.sendMoneyToOtherCustomer(fromNumber, toNumber, amount);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertTrue(message.contains("From/To Account does not exist"));
    }

    //Successful Send money from Customer 1 to Valid Customer 2 Number
    @Test(priority = 8, description = "Successful Send money from Customer 1 to Valid Customer 2 Number",enabled = true)
    public void sendMoney () throws InterruptedException, IOException {
        intitconfig();
        Thread.sleep(3000);
        String fromNumber = prop.getProperty("createdUserPhone");
        String toNumber = prop.getProperty("createdUser2Phone");
        int amount = 500;

        Thread.sleep(3000);
        Response res = transaction.sendMoneyToOtherCustomer(fromNumber, toNumber, amount);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("Send money successful"));
    }

    //Unsuccessful Payment To an invalid  Merchant Number from customer 2
    @Test(priority = 9, description = "Unsuccessful Payment To an invalid  Merchant Number from customer 2",enabled = true)
    public void paymentToInvalidMerchant () throws InterruptedException {
        String fromNumber = prop.getProperty("createdUser2Phone");
        String toNumber = "01819677097";
        int amount = 100;

        Thread.sleep(3000);
        Response res = transaction.paymentToMerchantNumber(fromNumber, toNumber, amount);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertTrue(message.contains("Account does not exist"));
    }

    //Successful Payment To a Valid Merchant Number from customer 2
    @Test(priority = 10, description = "Successful Payment To a Valid Merchant Number from customer 2: Payment 100tk to (01686606905) Merchant Number from the Customer 2",enabled = true)
    public void paymentToValidMerchant () throws InterruptedException {
        String fromNumber = prop.getProperty("createdUser2Phone");
        String toNumber = "01686606905";
        int amount = 100;

        Thread.sleep(3000);
        Response res = transaction.paymentToMerchantNumber(fromNumber, toNumber, amount);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("Payment successful"));
    }


    //Unsuccessful checking of customer 2 balance with wrong method
    @Test(priority = 11, description = "Unsuccessful checking of customer 2 balance with wrong method",enabled = true)
    public void checkCustomerBalanceWithInvalidMethod () throws ConfigurationException {
        boolean httpMethod = true;
        Response res = transaction.checkCustomerBalance(httpMethod);
        JsonPath jsonPath = res.jsonPath();
        String errorMessage = jsonPath.get("error.message");
        System.out.println(res.asString());
        Assert.assertTrue(errorMessage.contains("Not Found"));

    }

    //Successful checking of customer 2 balance with correct method
    @Test(priority = 12, description = "Successful checking of customer 2 balance with correct method",enabled = true)
    public void checkCustomerBalance () throws ConfigurationException {
        boolean httpMethod = false;
        Response res = transaction.checkCustomerBalance(httpMethod);
        JsonPath jsonPath = res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(res.asString());
        Assert.assertEquals(message, "User balance");

    }
}


