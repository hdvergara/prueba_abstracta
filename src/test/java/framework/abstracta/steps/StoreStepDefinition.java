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
            byte[] screenshot = ScreenshotUtil.takeScreenshot(driver);
            scenario.attach(screenshot, "image/png", "Screenshot de fallo");
        }
        DriverManager.quitDriver();
    }

    @Given("El usuario se encuentra en la página principal")
    public void el_usuario_se_encuentra_en_la_página_principal() {
        DriverManager.getDriver().get("https://opencart.abstracta.us/");
    }

    @When("Ingresa {string} en la barra de búsqueda y presiona buscar")
    public void ingresaEnLaBarraDeBúsquedaYPresionaBuscar(String value) {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.setInputSearch(value);
        homePage.clickOnSearchButton();
        byte[] screenshot = ScreenshotUtil.takeScreenshot(driver);
        scenario.attach(screenshot, "image/png", "Screenshot actual");
    }

    @And("Agrega al carrito la primera opcion de la busqueda")
    public void seleccionaLaPrimeraOpciónQueApareceEnLosResultados() {
        homePage.clickOnAddToCartButton();

    }

    @And("Hace clic en el botón del carrito de compras")
    public void haceClicEnElBotónDelCarritoDeCompras() {
        homePage.clickOnItemsCartButton();
    }

    @And("Presiona View Cart")
    public void presiona() {
        homePage.clickOnViewCartLabel();
        byte[] screenshot = ScreenshotUtil.takeScreenshot(driver);
        scenario.attach(screenshot, "image/png", "Screenshot actual");
    }

    @Then("Se valida que el iPhone se encuentre en el carrito de compras")
    public void seValidaQueElIPhoneSeEncuentreEnElCarritoDeCompras() {
        Assert.assertEquals(homePage.getValueTable().toLowerCase(), "iphone".toLowerCase());
        byte[] screenshot = ScreenshotUtil.takeScreenshot(driver);
        scenario.attach(screenshot, "image/png", "Screenshot actual");
    }

    @When("Remueve el iPhone del carrito de compras")
    public void remueveElIPhoneDelCarritoDeCompras() {
        homePage.clickOnRemoveItem();
    }

    @Then("Se valida que el iPhone ya no se encuentre en el carrito de compras")
    public void seValidaQueElIPhoneYaNoSeEncuentreEnElCarritoDeCompras() {
        homePage.validateItemRemoved();
        homePage.isDisplayedLabelCartEmpty();
        byte[] screenshot = ScreenshotUtil.takeScreenshot(driver);
        scenario.attach(screenshot, "image/png", "Screenshot actual");
    }
}
