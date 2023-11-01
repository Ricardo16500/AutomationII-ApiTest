import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {


    @When("send POST request {string} with body")
    public void sendPOSTRequestWithBody(String arg0) {
    }

    @Then("response code should be {int}")
    public void responseCodeShouldBe(int arg0) {
    }

    @And("the attribute {string} should be {string}")
    public void theAttributeShouldBe(String arg0, String arg1) {
    }

    @Given("using token in todo.ly")
    public void usingTokenInTodoLy() {
    }

    @When("send PUT request {string} with body")
    public void sendPUTRequestWithBody(String arg0) {
    }

    @When("send DELETE request {string} with body")
    public void sendDELETERequestWithBody(String arg0) {
    }
}
