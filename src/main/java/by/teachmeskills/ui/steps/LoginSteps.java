package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.pages.AllProjectsPage;
import by.teachmeskills.ui.pages.LoginPage;
import io.qameta.allure.Step;

public class LoginSteps {

    LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    @Step("Login with valid credentials")
    public AllProjectsPage openAndLogin() {
        return loginPage.open().loginWithValidCredential();
    }
}
