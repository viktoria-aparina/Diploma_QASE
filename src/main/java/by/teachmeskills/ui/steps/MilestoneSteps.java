package by.teachmeskills.ui.steps;

import by.teachmeskills.ui.dto.milestone.Milestone;
import by.teachmeskills.ui.pages.AllMilestonesPage;
import by.teachmeskills.ui.pages.HeaderPage;
import by.teachmeskills.ui.pages.NewMilestonePage;

public class MilestoneSteps {

    HeaderPage headerPage;
    NewMilestonePage newMilestonePage;
    AllMilestonesPage allMilestonesPage;

    public MilestoneSteps() {
        this.headerPage = new HeaderPage();
        this.newMilestonePage = new NewMilestonePage();
        this.allMilestonesPage = new AllMilestonesPage();
    }

    public AllMilestonesPage createNewMilestone(Milestone milestone) {
        headerPage.clickMilestonesButton();
        allMilestonesPage.clickCreateMilestoneButton();
        newMilestonePage.createNewMilestone(milestone).submitForm();
        return allMilestonesPage;
    }
}
