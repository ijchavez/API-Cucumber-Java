package Steps;

import PageObject.LandingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SpotifyEjercicioUnoSteps extends BaseSteps{
    @Given("estoy en la pagina de spotify")
    public void estoy_en_la_pagina_de_spotify() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.spotify.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @When("Busco el plan {string}")
    public void busco_el_plan(String string) {
        landingPage = new LandingPage(driver);
        premiumPage = landingPage.clickOnPremiumBtn();

    }

    @Then("valido que el {string} exista")
    public void valido_que_el_exista(String string) {
        WebElement elementoPlanes = premiumPage.getPlanes();
        List<WebElement> listaDePlanes = premiumPage.getElementosPlanes(elementoPlanes);
        List<String> listaDePlanesText = premiumPage.listaDePlanesText(listaDePlanes);
        /**
         System.out.println(listaDePlanesText);
         int i = 0;
         for (String texto : listaDePlanesText){
         System.out.println( i + " >>> " + texto);
         i++;
         }
         */
        Assert.assertTrue(listaDePlanesText.get(12).contains(string));
        premiumPage.closeDriver();
    }

}