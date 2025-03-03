package framework.abstracta.steps;

import framework.abstracta.manager.DriverManager;
import framework.abstracta.pages.HomePage;
import framework.abstracta.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class StoreStepDefinition {
    private WebDriver driver;
    private Scenario scenario;
    private HomePage homePage;

    @Before
    public void setUp(Scenario scenario) {
        driver = DriverManager.getDriver();
        this.scenario = scenario;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtil.captureScreenshot(driver, scenario, "Failure Screenshot");
        }
        DriverManager.quitDriver();
    }

    @Given("El usuario se encuentra en la página principal")
    public void el_usuario_se_encuentra_en_la_página_principal() {
        DriverManager.getDriver().get("https://opencart.abstracta.us/");
        ScreenshotUtil.captureScreenshot(driver, scenario, "Home Page Loaded");
    }

    @When("Ingresa {string} en la barra de búsqueda y presiona buscar")
    public void ingresaEnLaBarraDeBúsquedaYPresionaBuscar(String value) {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.setInputSearch(value);
        homePage.clickOnSearchButton();
        ScreenshotUtil.captureScreenshot(driver, scenario, "Search Executed for: " + value);
    }

    @And("Agrega al carrito la primera opcion de la busqueda")
    public void seleccionaLaPrimeraOpciónQueApareceEnLosResultados() {
        homePage.clickOnAddToCartButton();
        ScreenshotUtil.captureScreenshot(driver, scenario, "Added First Search Result to Cart");

    }

    @And("Hace clic en el botón del carrito de compras")
    public void haceClicEnElBotónDelCarritoDeCompras() {
        homePage.clickOnItemsCartButton();
        ScreenshotUtil.captureScreenshot(driver, scenario, "Clicked on Cart Button");
    }

    @And("Presiona View Cart")
    public void presiona() {
        homePage.clickOnViewCartLabel();
        ScreenshotUtil.captureScreenshot(driver, scenario, "Viewed Cart");
    }

    @Then("Se valida que el iPhone se encuentre en el carrito de compras")
    public void seValidaQueElIPhoneSeEncuentreEnElCarritoDeCompras() {
        Assert.assertEquals(homePage.getValueTable().toLowerCase(), "iphone".toLowerCase());
        ScreenshotUtil.captureScreenshot(driver, scenario, "Validated iPhone in Cart");
    }

    @When("Remueve el iPhone del carrito de compras")
    public void remueveElIPhoneDelCarritoDeCompras() {
        homePage.clickOnRemoveItem();
        ScreenshotUtil.captureScreenshot(driver, scenario, "Removed iPhone from Cart");
    }

    @Then("Se valida que el iPhone ya no se encuentre en el carrito de compras")
    public void seValidaQueElIPhoneYaNoSeEncuentreEnElCarritoDeCompras() {
        homePage.isDisplayedLabelCartEmpty();
        ScreenshotUtil.captureScreenshot(driver, scenario, "Validated Cart is Empty");
    }
}
