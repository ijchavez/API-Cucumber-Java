package PageObject;

import org.openqa.selenium.WebDriver;

public class BasePage {
    /**
     *   Las pages en la carpeta PageObject extienden de BasePage en los tests no se declaran:*
     *   XxxPage xxxPage = new XxxPage(driver);
     *   sino:
     *   xxxPage = new XxxPage(driver);
     *   En las pages no instancion el driver:
     *   public WebDriver driver; --> ESTO NO VA EN LAS PAGES, toma el que esta en esta clase,
     *   si lo instancio this.driver is null es el error que aparece
     *
     */
    public WebDriver driver;
    public void closeDriver(){
        driver.close();

    }

}