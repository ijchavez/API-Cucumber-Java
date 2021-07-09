package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class ApiSteps extends BaseSteps {

    @Given("Estoy en la API de chercher.tech")
    public void estoy_en_la_api_de_chercher_tech() {
        response = RestAssured.get("https://chercher.tech/sample/api/product/read");
    }
    @When("ejecuto el get")
    public void ejecuto_el_get() {
        responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

    }
    @Then("verifico que el responseCode sea {int} y la consulta tarde menos de {int} ms")
    public void verifico_que_el_response_code_sea_y_la_consulta_tarde_menos_de_ms(Integer respCode, Integer int2) {

        Integer responseStatusCode = response.getStatusCode();
        System.out.println("************************************************");
        Assert.assertEquals(responseStatusCode,respCode);
        System.out.println("Status Code => "+ responseStatusCode);
        System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));

    }

}
