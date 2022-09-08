package by.teachmeskills.ui;

import by.teachmeskills.ui.dto.defect.Defect;
import by.teachmeskills.ui.dto.milestone.Milestone;
import by.teachmeskills.ui.pages.AllDefectsPage;
import by.teachmeskills.ui.pages.AllMilestonesPage;
import by.teachmeskills.ui.pages.HeaderPage;
import by.teachmeskills.ui.pages.NewDefectPage;
import by.teachmeskills.ui.providers.DefectProvider;
import by.teachmeskills.ui.providers.MilestoneProvider;
import by.teachmeskills.ui.steps.MilestoneSteps;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateDefectTest extends BaseTest {

    @Test(groups = "smoke UI tests", description = "UI: Create defect with required fields")
    public void createNewDefectWithRequiredFields() {
        new HeaderPage().clickDefectsButton().clickCreateNewDefectButton();
        assertTrue(new NewDefectPage().isPageOpened(), "The New Defect Page wasn't opened");

        Defect defect = new DefectProvider().getDefectWithRequiredFields();
        new NewDefectPage().createNewDefectWithRequiredFields(defect)
                           .submitForm();
        assertTrue(new AllDefectsPage().getAlert().isDisplayed(), "The defect wasn't created");
        assertEquals(new AllDefectsPage().alertText(), "Defect was created successfully!",
                     "The text in alert is differ from expected");

        new AllDefectsPage().clickDropDownButton(defect.getTitle(), "Edit");
        assertTrue(new NewDefectPage().isPageOpened(), "The New Defect Page wasn't opened");

        Defect actualDefect = new NewDefectPage().getActualDefectInfoRequiredFields();
        assertThat(actualDefect).as("Defects are different").isEqualTo(defect);
    }

    @Test(groups = "smoke UI tests", description = "UI: Create defect with all fields")
    public void createNewDefectWithAllFields() {
        Milestone milestone = new MilestoneProvider().getMilestone();
        new MilestoneSteps().createNewMilestone(milestone);
        assertTrue(new AllMilestonesPage().getAlert().isDisplayed(), "The milestone wasn't created");
        assertEquals(new AllDefectsPage().alertText(), "Milestone was created successfully!",
                     "The text in alert is differ from expected");
        new AllMilestonesPage().getAlert().shouldBe(disappear, Duration.ofSeconds(10));
        Defect defect = new DefectProvider().getDefectWithAllFields(milestone.getName());

        new HeaderPage().clickDefectsButton().clickCreateNewDefectButton();
        assertTrue(new NewDefectPage().isPageOpened(), "The New Defect Page wasn't opened");

        new NewDefectPage().createNewDefectWithAllFields(defect).submitForm();
        assertTrue(new AllDefectsPage().getAlert().isDisplayed(), "The defect wasn't created");
        assertEquals(new AllDefectsPage().alertText(), "Defect was created successfully!",
                     "The text in alert is differ from expected");

        new AllDefectsPage().clickDropDownButton(defect.getTitle(), "Edit");
        assertTrue(new NewDefectPage().isPageOpened(), "The New Defect Page wasn't opened");

        Defect actualDefect = new NewDefectPage().getActualDefectInfoAllFields();
        assertThat(actualDefect).as("Defects are different").usingRecursiveComparison()
                                .ignoringFields("attachment")
                                .isEqualTo(defect);
        assertThat(actualDefect.getAttachment().getName()).as("The attachments are different").isEqualTo(defect.getAttachment().getName());
    }
}
