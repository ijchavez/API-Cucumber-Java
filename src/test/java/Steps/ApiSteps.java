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
        response = RestAssured.get("https://chercher.tech/sample/api/product/read/?id=4675");

    }
    @When("ejecuto el get")
    public void ejecuto_el_get() {
        responseBody = response.getBody().asString();
        System.out.println("============ Response Body ======================");
        System.out.println(responseBody);

    }
    @Then("verifico que el responseCode sea {int} y la consulta tarde menos de {int} ms")
    public void verifico_que_el_response_code_sea_y_la_consulta_tarde_menos_de_ms(Integer respCode, Integer maximoTiempoRespuesta) {
        Integer responseStatusCode = response.getStatusCode();
        System.out.println("===============================================");
        System.out.println("Código de respuesta: "+ responseStatusCode);

        Integer tiempoRta = Math.toIntExact(response.getTimeIn(TimeUnit.MILLISECONDS));
        System.out.println("Tiempo de respuesta: " + tiempoRta + "ms");
        System.out.println("===============================================");

        boolean tiempoAceptable = tiempoRespuestaMaximo(tiempoRta,maximoTiempoRespuesta);

        Assert.assertTrue(tiempoAceptable);
        Assert.assertEquals(responseStatusCode,respCode);

    }
    public boolean tiempoRespuestaMaximo(Integer tiempoRtaTotal, Integer tiempoMaximoRta){
        boolean tardaAceptable = true;
        if (tiempoRtaTotal > tiempoMaximoRta){
            tardaAceptable = false;
        }
        return tardaAceptable;


    }

}
