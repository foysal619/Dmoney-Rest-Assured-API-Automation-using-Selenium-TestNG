package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
//import static utils.Utils.saveEnvVar;
import static utils.Utils.setEnviromentVariable;


public class User extends Setup {
    public User () throws IOException {
        intitconfig();
    }
    public void doLoginWithInvalid (String email, String password) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);
        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(404).extract().response();


        JsonPath jsonObj = res.jsonPath();
        String token = jsonObj.get("token");
        String message = jsonObj.get("message");
        System.out.println(token);
        System.out.println(message);
        //to assert the successful login
        Assert.assertTrue(message.contains("User not found"));

    }

    public void doLogin(String email, String password) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);
        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(200).extract().response();


        JsonPath jsonObj = res.jsonPath();
        String token = jsonObj.get("token");
        String message = jsonObj.get("message");
        System.out.println(token);
        System.out.println(message);
        //to save the token
        setEnviromentVariable ("token", token);
        //to assert the successful login
        Assert.assertTrue(message.contains("Login successfully"));

    }

    public Response userCreate (String name, String email, String password, String phone_number, String nid, String role,String token) throws ConfigurationException, InterruptedException {
        Thread.sleep(5000);

        Thread.sleep(2000);
        UserModel registerModel = new UserModel(name, email, password, phone_number, nid, role);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", token)
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                        .body(registerModel)
                        .when()
                        .post("user/create");
        return res;

    }

}
