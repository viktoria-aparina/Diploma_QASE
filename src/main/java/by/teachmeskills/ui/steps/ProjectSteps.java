package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.pages.AllProjectsPage;
import by.teachmeskills.ui.pages.HeaderPage;
import by.teachmeskills.ui.pages.RepositoryPage;
import by.teachmeskills.ui.providers.ProjectProvider;
import io.qameta.allure.Step;

public class ProjectSteps {

    AllProjectsPage allProjectsPage;
    HeaderPage headerPage;

    public ProjectSteps() {
        this.allProjectsPage = new AllProjectsPage();
        this.headerPage = new HeaderPage();
    }

    @Step("Creation of new project")
    public RepositoryPage createNewProject() {
        return allProjectsPage.clickCreateNewProjectButton().createNewProject(new ProjectProvider().getProject()).submitForm();
    }

    @Step("Removing project")
    public AllProjectsPage deleteProject() {
        return headerPage.clickSettingsButton().deleteProject();
    }
}
