package framework.abstracta.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class WebActions {
    private static WebDriver driver = null;

    public WebActions(WebDriver driver) {
        WebActions.driver = driver;
    }

    private void waitForElement(WebElement element, int timeout) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.error("Error esperando el elemento con locator: {}", element, e);
            throw e;
        }
    }

    public void click(WebElement element, int timeout) {
        try {
            waitForElement(element, timeout);
            highlightElement(element);
            element.click();
        } catch (Exception e) {
            log.error("Error al hacer click en el elemento con locator: {}", element, e);
            throw e;
        }
    }

    public void sendText(WebElement element, String text, int timeout) {
        try {
            waitForElement(element, timeout);
            element.clear();
            element.sendKeys(text);
            highlightElement(element);
        } catch (Exception e) {
            log.error("Error al enviar texto '{}' al elemento con locator: {}", text, element, e);
            throw e;
        }
    }

    public String getText(WebElement element, int timeout) {
        try {
            waitForElement(element, timeout);
            highlightElement(element);
            return element.getText();
        } catch (Exception e) {
            log.error("Error al obtener el texto del elemento con locator: {}", element, e);
            throw e;
        }
    }

    public boolean isVisible(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOf(element));
            highlightElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void highlightElement(WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.border='3px solid red'", element);
        }
    }

}
