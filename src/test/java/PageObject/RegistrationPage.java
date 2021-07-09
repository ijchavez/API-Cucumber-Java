package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver remoteDriver){
        driver = remoteDriver;
        //En cada constructor debo ponerlo, mas si hago extends de clases y lo van a tomar varios
        PageFactory.initElements(driver, this);

    }
    @FindBy(id="email")
    WebElement registrationEmailField;
    public void fillRegistrationEmail(String aRegistrationEmail){
        registrationEmailField.sendKeys(aRegistrationEmail);

    }
    @FindBy(id="confirm")
    WebElement confirmRegistrationEmailField;
    public void fillConfirmRegistrationEmail(String aRegistrationEmail){
        confirmRegistrationEmailField.sendKeys(aRegistrationEmail);

    }
    @FindBy(xpath="//*[@aria-label='Indicador de error']")
    WebElement validMailErr;
    public String getValidMailErrMsgText(){
        String validMailErrText = validMailErr.getText();
        return validMailErrText;

    }

}

