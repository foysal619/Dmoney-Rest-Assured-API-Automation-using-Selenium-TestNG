package testRunner;

import com.github.javafaker.Faker;
import controller.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
    User user = new User();


    public UserTestRunner () throws IOException {
        intitconfig();
    }

    //do login with invalid credentials
    @Test(priority = 1, description = "Login with Invalid Credentials")
    public void doLoginWithInvalidCredentials() throws IOException, ConfigurationException {
        User user = new User();
        user.doLoginWithInvalid("invalidemail@123.net", "invalidPassword");
    }

    //do login with invalid credentials
    @Test(priority = 2, description = "Login with Valid Credentials")
    public void doLoginWithValidCredentials() throws IOException, ConfigurationException {
        User user = new User();
        user.doLogin("salman@roadtocareer.net", "1234");
    }

    //create new user with invalid phone number
    @Test(priority = 3, description = "Create new user with invalid phone number")
    public void createNewUserWithInvalidPhoneNumber () throws ConfigurationException, InterruptedException, IOException {

        Utils utils = new Utils();
        utils.genrateRandomUser();
        intitconfig();
        Thread.sleep(3000);
        String token = prop.getProperty("token");
        Response response = user.userCreate(utils.getName(), utils.getEmail(), "1234", "01673534", "19" + Utils.randomNumber(), "Customer", token);
        JsonPath jsonpath = response.jsonPath();
        System.out.println(response.asString());
        String message = jsonpath.get("message");

        Assert.assertTrue(message.contains("length must be at least 11 characters long"));

    }

    //create new user with valid credentials
    @Test(priority = 4, description = "Create new user with valid details", enabled = true)
    public void createNewUserWithValidCredentials () throws ConfigurationException, InterruptedException, IOException, ParseException {
        Utils utils = new Utils();
        utils.genrateRandomUser();
        String email=utils.email;
        String token = prop.getProperty("token");
        Response response = user.userCreate(utils.getName(), utils.getEmail(), "1234", Utils.randomNumber(), "19" + Utils.randomNumber(), "Customer", token);
        JsonPath jsonpath = response.jsonPath();


        String message = jsonpath.get("message");

        System.out.println(response.asString());
        Assert.assertTrue(message.contains("User created"));
        String createdUserName = jsonpath.get("user.name");
        String createdUserPassword = jsonpath.get("user.password");
        String createdUserPhone = jsonpath.get("user.phone_number");
        String createdUserEmail = jsonpath.get("user.email");
        String createdUserNid = jsonpath.get("user.nid");
        String createdUserRole = jsonpath.get("user.role");
        Utils.addJsonList(createdUserName,createdUserPassword,createdUserPhone,createdUserEmail,createdUserNid,createdUserRole);
        intitconfig();
        Utils.setEnviromentVariable("createdUserPhone", jsonpath.get("user.phone_number"));
    }

    //create second user with invalid token
    @Test(priority = 5, description = "Invalid Second User Creation with Invalid Token", enabled = true)
    public void createSecondUserCreationWithInvalidToken () throws ConfigurationException, InterruptedException, IOException, ParseException {
        Utils utils = new Utils();
        utils.genrateRandomUser();
        String email=utils.email;
        String token = prop.getProperty("invalidToken");
        Response response = user.userCreate(utils.getName(), utils.getEmail(), "1234", Utils.randomNumber(), "19" + Utils.randomNumber(), "Customer", token);
        JsonPath jsonpath = response.jsonPath();


        String message = jsonpath.get("error.message");

        System.out.println(response.asString());
        System.out.println(message);
        Assert.assertTrue(message.contains("Token expired!"));

        intitconfig();
    }

    //create second user with valid credentials
    @Test(priority = 6, description = "Create new second user with valid details", enabled = true)
    public void createNewUser2WithValidCredentials () throws ConfigurationException, InterruptedException, IOException, ParseException {
        Utils utils = new Utils();
        utils.genrateRandomUser();
        String email=utils.email;
        String token = prop.getProperty("token");
        Response response = user.userCreate(utils.getName(), utils.getEmail(), "1234", Utils.randomNumber(), "19" + Utils.randomNumber(), "Customer", token);
        JsonPath jsonpath = response.jsonPath();


        String message = jsonpath.get("message");

        System.out.println(response.asString());
        Assert.assertTrue(message.contains("User created"));
        String createdUser2Name = jsonpath.get("user.name");
        String createdUser2Password = jsonpath.get("user.password");
        String createdUser2Phone = jsonpath.get("user.phone_number");
        String createdUser2Email = jsonpath.get("user.email");
        String createdUser2Nid = jsonpath.get("user.nid");
        String createdUser2Role = jsonpath.get("user.role");
        Utils.addJsonList2(createdUser2Name,createdUser2Password,createdUser2Phone,createdUser2Email,createdUser2Nid,createdUser2Role);
        intitconfig();
        Utils.setEnviromentVariable("createdUser2Phone", jsonpath.get("user.phone_number"));
    }

    //create agent with valid credentials
    @Test(priority = 7, description = "Create Agent with Valid Credentials", enabled = true)
    public void createAgentWithValidCredentials () throws ConfigurationException, InterruptedException, IOException, ParseException {
        Utils utils = new Utils();
        utils.genrateRandomUser();
        String email=utils.email;
        String token = prop.getProperty("token");
        Response response = user.userCreate(utils.getName(), utils.getEmail(), "1234", Utils.randomNumber(), "19" + Utils.randomNumber(), "Agent", token);
        JsonPath jsonpath = response.jsonPath();


        String message = jsonpath.get("message");

        System.out.println(response.asString());
        Assert.assertTrue(message.contains("User created"));
        String createdAgentName = jsonpath.get("user.name");
        String createdAgentPassword = jsonpath.get("user.password");
        String createdAgentPhone = jsonpath.get("user.phone_number");
        String createdAgentEmail = jsonpath.get("user.email");
        String createdAgentNid = jsonpath.get("user.nid");
        String createdAgentRole = jsonpath.get("user.role");
        Utils.addAgent(createdAgentName,createdAgentPassword,createdAgentPhone,createdAgentEmail,createdAgentNid,createdAgentRole);
        intitconfig();
        Utils.setEnviromentVariable("createdAgentPhone", jsonpath.get("user.phone_number"));
    }
//    @Test(priority = 5, description = "Create New Second User", enabled = true)
//    public void createNewSecondUser () throws ConfigurationException, InterruptedException, IOException {
//        Utils utils = new Utils();
//        utils.genrateRandomUser();
//        String token = prop.getProperty("token");
//        Response response = user.userCreate(utils.getName(), utils.getEmail(), "1234", Utils.randomNumber(), "19" + Utils.randomNumber(), "Customer", token);
//        JsonPath jsonpath = response.jsonPath();
//
//
//        String message = jsonpath.get("message");
//
//        System.out.println(response.asString());
//        Assert.assertTrue(message.contains("User created"));
//        Utils.setEnviromentVariable("created2ndUserPhone", jsonpath.get("user.phone_number"));
//        Utils.setEnviromentVariable("created2ndUserPassword", jsonpath.get("user.password"));
//        intitconfig();
//    }
//
//    @Test(priority = 6, description = "New user Creation with Invalid token")
//    public void createNewAgentWithoutProperToken () throws ConfigurationException, InterruptedException {
//        Utils utils = new Utils();
//        utils.genrateRandomUser();
//        String token = "yfuwebfuyewbfuybwefuyewufuyvbew";
//
//        Response res = user.agentCreate(utils.getName(), utils.getEmail(), "4X@" + Utils.randomNumber(), Utils.randomNumber(), "19" + Utils.randomNumber(), "Agent", token);
//
//        JsonPath jsonpath = res.jsonPath();
//
//
//        String message = jsonpath.get("error.message");
//        System.out.println(res.asString());
//        Assert.assertTrue(message.contains("Token expired"));
//
//
//    }
//
//    @Test(priority = 7, description = "Create New Agent", enabled = true)
//    public void createNewAgent () throws ConfigurationException, InterruptedException, IOException {
//        Utils utils = new Utils();
//        utils.genrateRandomUser();
//        String token = prop.getProperty("token");
//        Thread.sleep(3000);
//        Response res = user.agentCreate(utils.getName(), utils.getEmail(), "4X@" + Utils.randomNumber(), Utils.randomNumber(), "19" + Utils.randomNumber(), "Agent", token);
//
//        JsonPath jsonpath = res.jsonPath();
//
//
//        String message = jsonpath.get("message");
//        System.out.println(res.asString());
//        Assert.assertTrue(message.contains("User created"));
//        Utils.setEnviromentVariable("createdAgentPhone", jsonpath.get("user.phone_number"));
//        Utils.setEnviromentVariable("createdAgentPassword", jsonpath.get("user.password"));
//
//        intitconfig();
//    }
//
//    //@Test(priority = 8, description = "Search User With Invalid Phone Number")
//    public void searchUserWithInvalidPhoneNumber () throws InterruptedException, IOException {
//        String phoneNumber = "01819677097";
//        intitconfig();
//        String token = prop.getProperty("token");
//        Response res = user.searchByCustomerPhoneNumber(phoneNumber);
//        JsonPath jsonPath = res.jsonPath();
//        String message = jsonPath.get("message");
//        System.out.println(res.asString());
//        Assert.assertTrue(message.contains("User not found"));
//        intitconfig();
//    }
//
//    //@Test(priority = 9, description = "Search User by Phone Number", enabled = true)
//    public void searchByCustomerPhoneNumber () throws InterruptedException, IOException {
//        String phoneNumber = prop.getProperty("createdUserPhone");
//        Response res = user.searchByCustomerPhoneNumber(phoneNumber);
//        JsonPath jsonPath = res.jsonPath();
//        String message = jsonPath.get("message");
//        System.out.println(res.asString());
//        Assert.assertTrue(message.contains("User found"));
//        intitconfig();
//    }
}
