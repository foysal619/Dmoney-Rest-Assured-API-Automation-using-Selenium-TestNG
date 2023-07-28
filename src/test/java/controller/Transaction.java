package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.TransactionModel;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.internal.Parser;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static utils.Utils.readJSONFile;

public class Transaction extends Setup {
static {
    try {
        intitconfig();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
    public Transaction() throws IOException, ParseException {

    }

    public Response doTransactionFromSystemToAgent(boolean methodFlag) throws ConfigurationException, IOException, ParseException, InterruptedException {
    RestAssured.baseURI = prop.getProperty("baseUrl");
      String methodName=methodFlag?"POST":"GET";
        TransactionModel systemToAgentTransaction = new TransactionModel("SYSTEM",prop.getProperty("createdAgentPhone"), 2000);
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(systemToAgentTransaction)
                        .when().request(methodName,"transaction/deposit");
        return res;

    }


    public Response agentToCustomerTransaction(String agentPhoneNumber,String customerPhoneNumber,int amount) throws ConfigurationException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        TransactionModel agentTransactionModel = new TransactionModel(agentPhoneNumber, customerPhoneNumber, amount);
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(agentTransactionModel)
                        .when()
                        .post("transaction/deposit");

        return res;

    }

    public Response checkCustomerBalance(boolean methodFlag) throws ConfigurationException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String methodName=methodFlag?"POST":"GET";
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .when().request(methodName,"transaction/balance/" + prop.getProperty("createdUser2Phone"));

        return res;

    }

    public Response moneyWithdrawalByCustomer(String customerPhoneNumber,String agentPhoneNumber,int amount) throws InterruptedException {
        RestAssured.baseURI = prop.getProperty("baseUrl");

        TransactionModel withdrawal = new TransactionModel(customerPhoneNumber,agentPhoneNumber, amount);

        Thread.sleep(2000);
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(withdrawal)
                        .when()
                        .post("transaction/withdraw/")
                        .then()
                        .extract().response();
        return res;



    }


    public Response sendMoneyToOtherCustomer(String fromNumber,String toNumber,int amount) {
        RestAssured.baseURI = prop.getProperty("baseUrl");

        TransactionModel sendMoney = new TransactionModel(fromNumber,toNumber,amount);


        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(sendMoney)
                        .when()
                        .post("transaction/sendmoney/")
                        .then()
                        .extract().response();
        return res;
    }



    public Response paymentToMerchantNumber(String fromNumber,String toNumber,int amount) {
        RestAssured.baseURI = prop.getProperty("baseUrl");

        TransactionModel sendMoney = new TransactionModel(fromNumber,toNumber,amount);


        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(sendMoney)
                        .when()
                        .post("transaction/payment/")
                        .then()
                        .extract().response();
        return res;
    }
}


