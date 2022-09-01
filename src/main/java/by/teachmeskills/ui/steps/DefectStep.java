package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.pages.*;
import by.teachmeskills.ui.providers.DefectProvider;

public class DefectStep {

    NewDefectPage newDefectPage;
    HeaderPage headerPage;
    SettingsPage settingsPage;
    AllProjectsPage allProjectsPage;
    AllDefectsPage allDefectsPage;

    public DefectStep() {
        this.newDefectPage = new NewDefectPage();
        this.headerPage = new HeaderPage();
        this.settingsPage = new SettingsPage();
        this.allProjectsPage = new AllProjectsPage();
        this.allDefectsPage = new AllDefectsPage();
    }

    public AllProjectsPage deleteDefect() {
        newDefectPage.clickCancelButtonAndCloseNotificationForm();
        headerPage.clickSettingsButton();
        settingsPage.deleteProject();
        return allProjectsPage;
    }

    public AllDefectsPage createDefectWithRequiredFields() {
        headerPage.clickDefectsButton();
        allDefectsPage.clickCreateNewDefectButton();
        newDefectPage.createNewDefectWithRequiredFields(new DefectProvider().getDefectWithRequiredFields()).submitForm();
        return allDefectsPage;
    }
}
