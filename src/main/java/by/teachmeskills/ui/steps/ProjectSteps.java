package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.pages.AllProjectsPage;
import by.teachmeskills.ui.pages.HeaderPage;
import by.teachmeskills.ui.pages.RepositoryPage;
import by.teachmeskills.ui.providers.ProjectProvider;

public class ProjectSteps {

    AllProjectsPage allProjectsPage;
    HeaderPage headerPage;

    public ProjectSteps() {
        this.allProjectsPage = new AllProjectsPage();
        this.headerPage = new HeaderPage();
    }

    public RepositoryPage createNewProject() {
        return allProjectsPage.clickCreateNewProjectButton().createNewProject(new ProjectProvider().getProject()).submitForm();
    }

    public AllProjectsPage deleteProject() {
        return headerPage.clickSettingsButton().deleteProject();
    }
}
