package by.teachmeskills.ui;

import by.teachmeskills.ui.steps.LoginSteps;
import by.teachmeskills.ui.steps.ProjectSteps;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod(groups = { "smoke UI tests", "regression UI tests" })
    public void loginAndCreateProject() {
        new LoginSteps().openAndLogin();
        new ProjectSteps().createNewProject();
    }

    @AfterMethod(alwaysRun = true)
    public void deleteProject() {
        new ProjectSteps().deleteProject();
        Selenide.closeWebDriver();
    }
}
