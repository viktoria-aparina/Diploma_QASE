package by.teachmeskills.ui;

import by.teachmeskills.ui.dto.defect.Defect;
import by.teachmeskills.ui.pages.AllDefectsPage;
import by.teachmeskills.ui.pages.NewDefectPage;
import by.teachmeskills.ui.providers.DefectProvider;
import by.teachmeskills.ui.steps.DefectStep;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DeleteDefectTest extends BaseTest {

    @BeforeMethod
    public void createDefect() {
        new DefectStep().clickCreateDefectButton();
    }

    @Test(groups = "smoke UI tests", description = "UI: Delete defect with required fields using dropdown menu")
    public void deleteDefectTest() {
        Defect defect = new DefectProvider().getDefectWithRequiredFields();
        new NewDefectPage().createNewDefectWithRequiredFields(defect)
                           .submitForm();
        assertTrue(new AllDefectsPage().getAlert().isDisplayed(), "The defect wasn't created");

        new AllDefectsPage().clickDropDownButton(defect.getTitle(), "Delete")
                            .clickDeleteInNotification();
        assertTrue(new AllDefectsPage().getAlert().isDisplayed(), "The notification message didn't appear");
        assertFalse(new AllDefectsPage().isDefectDelete(defect.getTitle()), "The defect wasn't deleted");
    }
}
