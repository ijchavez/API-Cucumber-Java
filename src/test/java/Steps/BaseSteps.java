package Steps;

import PageObject.LandingPage;
import PageObject.PremiumPage;
import PageObject.RegistrationPage;

import PageObject.BasePage;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

public class BaseSteps extends BasePage {
    /** Aca van las pages decladadras:
     * public XxxPage xxxPage;
     * **/
    public WebDriver driver;
    public LandingPage landingPage;
    public PremiumPage premiumPage;
    public RegistrationPage registrationPage;
    public Response response;
    public String responseBody = "";
}
