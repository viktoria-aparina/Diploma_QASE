package by.teachmeskills.ui.pages;

import by.teachmeskills.ui.utils.PropertiesLoader;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.id;

@Log4j2
public class LoginPage {

    public LoginPage open() {
        Selenide.open("/login");
        getWebDriver().manage().window().maximize();
        return this;
    }

    public AllProjectsPage loginWithValidCredential() {
        $(id("inputEmail")).sendKeys(PropertiesLoader.loadProperties().getProperty("login"));
        $(id("inputPassword")).sendKeys(PropertiesLoader.loadProperties().getProperty("password"));
        $(id("btnLogin")).click();
        log.info("User was logged in successfully");
        return new AllProjectsPage();
    }

    public SelenideElement getLoginButton() {
        return $(id("btnLogin")).shouldBe(enabled);
    }
}
