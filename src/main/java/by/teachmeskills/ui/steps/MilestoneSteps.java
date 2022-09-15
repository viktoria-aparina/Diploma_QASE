package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.dto.milestone.Milestone;
import by.teachmeskills.ui.pages.AllMilestonesPage;
import by.teachmeskills.ui.pages.HeaderPage;
import io.qameta.allure.Step;

public class MilestoneSteps {

    HeaderPage headerPage;

    public MilestoneSteps() {
        this.headerPage = new HeaderPage();
    }

    @Step("Create new milestone in project")
    public AllMilestonesPage createNewMilestone(Milestone milestone) {
        return headerPage.clickMilestonesButton()
                         .clickCreateMilestoneButton()
                         .createNewMilestone(milestone)
                         .submitForm();
    }
}
