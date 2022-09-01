package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.pages.AllProjectsPage;
import by.teachmeskills.ui.pages.NewProjectPage;
import by.teachmeskills.ui.pages.RepositoryPage;
import by.teachmeskills.ui.pages.SettingsPage;
import by.teachmeskills.ui.providers.ProjectProvider;

public class ProjectSteps {

    NewProjectPage newProjectPage;
    RepositoryPage repositoryPage;
    AllProjectsPage allProjectsPage;
    SettingsPage settingsPage;

    public ProjectSteps() {
        this.newProjectPage = new NewProjectPage();
        this.repositoryPage = new RepositoryPage();
        this.allProjectsPage = new AllProjectsPage();
        this.settingsPage = new SettingsPage();
    }

    public RepositoryPage createNewProject() {
        allProjectsPage.clickCreateNewProjectButton();
        newProjectPage.createNewProject(new ProjectProvider().getProject()).submitForm();
        return repositoryPage;
    }

    public AllProjectsPage deleteProject() {
        settingsPage.deleteProject();
        return allProjectsPage;
    }
}
