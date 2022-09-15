package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.pages.AllDefectsPage;
import by.teachmeskills.ui.pages.HeaderPage;
import by.teachmeskills.ui.pages.NewDefectPage;
import io.qameta.allure.Step;

public class DefectStep {

    NewDefectPage newDefectPage;
    HeaderPage headerPage;
    AllDefectsPage allDefectsPage;

    public DefectStep() {
        this.newDefectPage = new NewDefectPage();
        this.headerPage = new HeaderPage();
        this.allDefectsPage = new AllDefectsPage();
    }

    @Step("Removing of defect in dropdown")
    public AllDefectsPage deleteDefect(String defectTitle) {
        newDefectPage.clickCancelButtonAndCloseNotificationForm();
        return allDefectsPage.clickDropDownButton(defectTitle, "Delete")
                             .clickDeleteInNotification();
    }

    @Step("Open the page with defect's creation")
    public NewDefectPage clickCreateDefectButton() {
        return headerPage.clickDefectsButton().clickCreateNewDefectButton();
    }
}
