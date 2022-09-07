package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.pages.AllProjectsPage;
import by.teachmeskills.ui.pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    public AllProjectsPage openAndLogin() {
        return loginPage.open().loginWithValidCredential();
    }
}
