package runner;

import config.Configuration;
import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class MyStepdefs {
    Response response;
    RequestInfo requestInfo = new RequestInfo();
    Map<String, String> variables = new HashMap<>();

    @When("send {} request {string} with body")
    public void sendPOSTRequestWithBody(String method, String url, String body) {
        requestInfo.setUrl(Configuration.host+this.replaceValues(url)).setBody(body);
        response = FactoryRequest.make(method).send(requestInfo);
        
    }
    @Then("response code should be {int}")
    public void responseCodeShouldBe(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @And("the attribute {string} should be {string}")
    public void theAttributeShouldBe(String attribute, String nameVariable) {
        variables.put(nameVariable,response.then().extract().path(attribute)+"");
    }

    @Given("using token in todo.ly")
    public void usingTokenInTodoLy() {
        String credentials = Base64.getEncoder()
                .encodeToString((Configuration.user+":"+Configuration.password).getBytes());
        requestInfo.setUrl(Configuration.host+"/api/authentication/token.json")
                .setHeader("Authorization","Basic "+credentials);
        response = FactoryRequest.make("get").send(requestInfo);
        // get token
        String token = response.then().extract().path("TokenString");
        requestInfo = new RequestInfo();
        requestInfo.setHeader("Token",token);
    }
    private String replaceValues(String value){
        for (String key:variables.keySet() ) {
            value=value.replace(key,variables.get(key));
        }
        return value;
    }


}
