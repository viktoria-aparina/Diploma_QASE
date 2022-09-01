package by.teachmeskills.ui;

import by.teachmeskills.ui.steps.LoginSteps;
import by.teachmeskills.ui.steps.ProjectSteps;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void loginAndCreateProject() {
        new LoginSteps().openAndLogin();
        new ProjectSteps().createNewProject();
    }

    /*@AfterMethod
    public void deleteProject() {
        new ProjectSteps().deleteProject();
    }*/
}
