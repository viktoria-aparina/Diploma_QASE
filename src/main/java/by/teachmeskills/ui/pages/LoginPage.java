package by.teachmeskills.ui.pages;

import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.id;

@Log4j2
public class LoginPage extends BasePage {

    @Override
    public boolean isPageOpened() {
        try {
            $(id("btnLogin")).shouldBe(enabled);
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "Login Page", exception.getCause());
            return false;
        }
    }

    public LoginPage open() {
        Selenide.open("/login");
        getWebDriver().manage().window().maximize();
        return this;
    }

    public AllProjectsPage loginWithValidCredential() {
        $(id("inputEmail")).sendKeys(System.getenv("username"));
        $(id("inputPassword")).sendKeys(System.getenv("password"));
        $(id("btnLogin")).click();
        log.info("User was logged in successfully");
        return new AllProjectsPage();
    }
}
