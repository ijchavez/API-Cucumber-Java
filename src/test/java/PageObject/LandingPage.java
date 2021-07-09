package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage{
    public LandingPage(WebDriver remoteDriver){
        driver = remoteDriver;
        //En cada constructor debo ponerlo, mas si hago extends de clases y lo van a tomar varios
        PageFactory.initElements(driver, this);

    }
    @FindBy(xpath="//*[@href='https://www.spotify.com/ar/premium/']")
    WebElement premiumBtn;
    public PremiumPage clickOnPremiumBtn(){
        premiumBtn.click();
        PremiumPage pm = new PremiumPage(driver);
        return pm;

    }
    @FindBy(xpath="//*[@href='https://www.spotify.com/ar/signup/']")
    WebElement registrateBtn;
    public RegistrationPage clickOnRegistrateBtn(){
        registrateBtn.click();
        RegistrationPage rp = new RegistrationPage(driver);
        return rp;

    }

}
