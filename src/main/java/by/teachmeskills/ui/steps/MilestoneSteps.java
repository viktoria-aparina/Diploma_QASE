package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.dto.milestone.Milestone;
import by.teachmeskills.ui.pages.AllMilestonesPage;
import by.teachmeskills.ui.pages.HeaderPage;

public class MilestoneSteps {

    HeaderPage headerPage;

    public MilestoneSteps() {
        this.headerPage = new HeaderPage();
    }

    public AllMilestonesPage createNewMilestone(Milestone milestone) {
        return headerPage.clickMilestonesButton()
                         .clickCreateMilestoneButton()
                         .createNewMilestone(milestone)
                         .submitForm();
    }
}
