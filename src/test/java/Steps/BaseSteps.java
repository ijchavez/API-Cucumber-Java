package Steps;

import PageObject.BasePage;
import io.restassured.response.Response;

public class BaseSteps extends BasePage {
    /** Aca van las pages decladadras:
     * public XxxPage xxxPage;
     * **/
    public Response response;
    public String responseBody = "";

    public BaseSteps(String aResponseBody, Response aResponse){
        this.responseBody = aResponseBody;
        this.response = aResponse;

    }
    public BaseSteps(){

    }
    public void setResponseBody(String aResponseBody){
        this.responseBody = aResponseBody;

    }
    public void setResponse(Response aResponse){
        this.response = aResponse;

    }
    public String getResponseBody(){
        return this.responseBody;

    }
    public Response getResponse(){
        return this.response;

    }
    public String toString(){
        return this.responseBody;

    }

}
