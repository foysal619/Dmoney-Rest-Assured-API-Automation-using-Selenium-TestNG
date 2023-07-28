package utils;

import com.github.javafaker.Faker;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static javax.swing.UIManager.get;

public class Utils {
    //This method will save all the information like token ,user info in config.properties file
    public static void setEnviromentVariable(String key, String value) throws ConfigurationException {

        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    //for customer 1
    public static void addJsonList(String createdUserName, String createdUserPassword, String createdUserPhone, String createdUserEmail, String createdUserNid, String createdUserRole) throws IOException, ParseException {
        String fileName = "./src/test/resources/extraInfo.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userInfo = new JSONObject();
        userInfo.put("createdUserName", createdUserName);
        userInfo.put("createdUserPassword", createdUserPassword);
        userInfo.put("createdUserPhone", createdUserPhone);
        userInfo.put("createdUserEmail", createdUserEmail);
        userInfo.put("createdUserNid", createdUserNid);
        userInfo.put("createdUserRole", createdUserRole);
        jsonArray.add(userInfo);
        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

    //for customer 2
    public static void addJsonList2(String createdUser2Name, String createdUser2Password, String createdUser2Phone, String createdUser2Email, String createdUser2Nid, String createdUser2Role) throws IOException, ParseException {
        String fileName = "./src/test/resources/extraInfo.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userInfo = new JSONObject();
        userInfo.put("createdUser2Name", createdUser2Name);
        userInfo.put("createdUser2Password", createdUser2Password);
        userInfo.put("createdUser2Phone", createdUser2Phone);
        userInfo.put("createdUser2Email", createdUser2Email);
        userInfo.put("createdUser2Nid", createdUser2Nid);
        userInfo.put("createdUser2Role", createdUser2Role);
        jsonArray.add(userInfo);
        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }


    //for agent
    public static void addAgent(String createdAgentName, String createdAgentPassword, String createdAgentPhone, String createdUAgentEmail, String createdAgentNid, String createdAgentRole) throws IOException, ParseException {
        String fileName = "./src/test/resources/extraInfo.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userInfo = new JSONObject();
        userInfo.put("createdAgentName", createdAgentName);
        userInfo.put("createdAgentPassword", createdAgentPassword);
        userInfo.put("createdAgentPhone", createdAgentPhone);
        userInfo.put("createdAgentEmail", createdUAgentEmail);
        userInfo.put("createdAgentNid", createdAgentNid);
        userInfo.put("createdAgentRole", createdAgentRole);
        jsonArray.add(userInfo);
        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

    //for read file from json file
    public static JSONObject readJSONFile(String filename, int index) throws IOException, ParseException {


        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(filename));
        JSONArray jsonArray = (JSONArray) obj;
        int arraySize = jsonArray.size();
        int arrayIn = arraySize - index;

        JSONObject arrayObject = (JSONObject) jsonArray.get(arrayIn);


        return arrayObject;


    }

    //for read file from config.properties
    public static Properties readConfigFile() throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        prop.load(file);
        return prop;


    }

    public String name;
    public String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void genrateRandomUser() {
        Faker faker = new Faker();
        setName(faker.name().fullName());
        setEmail(faker.internet().emailAddress());


    }

    public static String randomNumber() {
        String prefix = "01700";
        int min = 100000;
        int max = 999999;
        int phoneNumber = (int) Math.round(Math.random() * (max - min) + min);
        return prefix + phoneNumber;
    }

    public static void main(String[] args) throws IOException, ParseException {
        //System.out.println(readJSONFile("./src/test/resources/extrainfo.json", 2,get("createdUser2Role")));
//     String token= (String) ;
        Utils utils = new Utils();
        UserModel testUser = new UserModel("jjjj", "badhon", "1234", "01771", "1997", "Customer");
        System.out.println(testUser);
    }

    public static int generateRandomId(int min, int max) {
        double number = Math.random() * (max - min) + min;
        return (int) number;
    }





}
