package by.teachmeskills.ui;

import by.teachmeskills.ui.dto.defect.Defect;
import by.teachmeskills.ui.dto.milestone.Milestone;
import by.teachmeskills.ui.pages.AllDefectsPage;
import by.teachmeskills.ui.pages.AllMilestonesPage;
import by.teachmeskills.ui.pages.HeaderPage;
import by.teachmeskills.ui.pages.NewDefectPage;
import by.teachmeskills.ui.providers.DefectProvider;
import by.teachmeskills.ui.providers.MilestoneProvider;
import by.teachmeskills.ui.steps.DefectStep;
import by.teachmeskills.ui.steps.MilestoneSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class CreateDefectTest extends BaseTest {

    @Test
    public void createNewDefectWithRequiredFields() {
        new HeaderPage().clickDefectsButton().clickCreateNewDefectButton();
        assertTrue(new NewDefectPage().getTitleCreateDefect().isDisplayed(), "The user is on wrong page");

        Defect defect = new DefectProvider().getDefectWithRequiredFields();
        new NewDefectPage().createNewDefectWithRequiredFields(defect).submitForm();
        assertTrue(new AllDefectsPage().getAlert().isDisplayed(), "The defect wasn't created");

        new AllDefectsPage().clickDropDownButtonEdit(defect.getTitle());
        assertTrue(new NewDefectPage().getTitleEditDefect().isDisplayed());

        Defect actualDefect = new NewDefectPage().getActualDefectInfoRequiredFields();
        assertThat(actualDefect).as("Defects are different").isEqualTo(defect);
    }

    @Test
    public void createNewDefectWithAllFields() {
        Milestone milestone = new MilestoneProvider().getMilestone();
        new MilestoneSteps().createNewMilestone(milestone);
        Defect defect = new DefectProvider().getDefectWithAllFields(milestone.getName());

        assertTrue(new AllMilestonesPage().getAlert().isDisplayed(), "The milestone wasn't created");

        new HeaderPage().clickDefectsButton().clickCreateNewDefectButton();
        assertTrue(new NewDefectPage().getTitleCreateDefect().isDisplayed(), "The user is on wrong page");

        new NewDefectPage().createNewDefectWithAllFields(defect).submitForm();
        assertTrue(new AllDefectsPage().getAlert().isDisplayed(), "The defect wasn't created");

        new AllDefectsPage().clickDropDownButtonEdit(defect.getTitle());
        assertTrue(new NewDefectPage().getTitleEditDefect().isDisplayed());

        Defect actualDefect = new NewDefectPage().getActualDefectInfoAllFields();
        assertThat(actualDefect).as("Defects are different").isEqualTo(defect);
    }

    @AfterMethod
    public void deleteDefect() {
        new DefectStep().deleteDefect();
    }
}
