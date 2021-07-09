import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PruebaApi {
    @Test
    public void readAllDetails()
    {
        // request the server
        Response response = RestAssured.get("https://chercher.tech/sample/api/product/read");

        // store the response body in string
        String responseBody = response.getBody().asString();
        // print the response
        System.out.println("Response Body is =>  " + responseBody);
        // store the response code
        int responseStatusCode = response.getStatusCode();
        System.out.println("************************************************");
        System.out.println("Status Code => "+ responseStatusCode);
        System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));

    }
    @Test
    public void postDetails()
    {
        // request the server
        RequestSpecification reqSpec = RestAssured.given();

        JSONObject jo = new JSONObject();
        jo.put("id", "4676");
        jo.put("name", "esto es un nombre");
        jo.put("description", "esta es una descripcion");
        jo.put("price", "99");
        reqSpec.body(jo.toString());

        Response resp = reqSpec.post("https://chercher.tech/sample/api/product/update");
        System.out.println(jo);

        // request the server
        Response response = RestAssured.get("https://chercher.tech/sample/api/product/read?id=4676");

        // store the response body in string
        String responseBody = response.getBody().asString();
        // print the response
        System.out.println("Response Body is =>  " + responseBody);

        int responseStatusCode = response.getStatusCode();
        System.out.println("************************************************");
        System.out.println("Status Code => "+ responseStatusCode);
        System.out.println("Tiempo de respuesta: " +
                            response.getTimeIn(TimeUnit.MILLISECONDS)+
                            "ms");

    }
    @Test
    public void putDetails(){
        RequestSpecification reqSpec = RestAssured.given();

        JSONObject jo = new JSONObject();
        jo.put("name", "Prueba de API");
        jo.put("description", "APIPRUEBA");
        jo.put("price", "3.1416");
        reqSpec.body(jo.toString());

        Response resp = reqSpec.put("https://chercher.tech/sample/api/product/create");
        System.out.println("Response code => " + resp.statusCode());
    }

}
