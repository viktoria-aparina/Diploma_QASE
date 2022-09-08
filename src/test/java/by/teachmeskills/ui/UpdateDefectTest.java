package by.teachmeskills.ui;

import by.teachmeskills.ui.dto.defect.Defect;
import by.teachmeskills.ui.pages.AllDefectsPage;
import by.teachmeskills.ui.pages.DefectPage;
import by.teachmeskills.ui.pages.NewDefectPage;
import by.teachmeskills.ui.providers.DefectProvider;
import by.teachmeskills.ui.steps.DefectStep;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class UpdateDefectTest extends BaseTest {

    Defect defect;

    @BeforeMethod
    public void createDefect() {
        new DefectStep().clickCreateDefectButton();
        defect = new DefectProvider().getDefectWithRequiredFields();
        new NewDefectPage().createNewDefectWithRequiredFields(defect)
                           .submitForm();
    }

    @Test(groups = "regression UI tests", description = "UI: Update defect title")
    public void updateDefectTitle() {
        new AllDefectsPage().clickDropDownButton(defect.getTitle(), "Edit");
        assertTrue(new NewDefectPage().isPageOpened(), "The New Defect Page wasn't opened");

        new NewDefectPage().updateDefectTitle("Updated defect title")
                           .submitUpdateForm();
        assertTrue(new AllDefectsPage().getAlert().isDisplayed(), "The defect title wasn't updated");
        assertTrue(new AllDefectsPage().isVisibleNewDefectTitle("Updated defect title"), "The defect title wasn't updated");
    }

    @Test(groups = "regression UI tests", description = "UI: Update defect status using dropdown menu")
    public void updateDefectStatus() {
        new AllDefectsPage().clickDropDownButton(defect.getTitle(), "Resolve")
                            .clickConfirmInNotification()
                            .clickStatusButton("Resolved")
                            .clickDefectTitle(defect.getTitle());
        assertTrue(new DefectPage().isPageOpened(), "The Defect Page wasn't opened");
        assertTrue(new DefectPage().getStatus("Resolved").isDisplayed(), "The status wasn't changed");
    }
}
