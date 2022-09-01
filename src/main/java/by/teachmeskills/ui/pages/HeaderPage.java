package by.teachmeskills.ui.pages;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class HeaderPage {

    public AllDefectsPage clickDefectsButton() {
        $x("//span[text()='Defects']").click();
        log.info("The user is on All Defects page");
        return new AllDefectsPage();
    }

    public AllMilestonesPage clickMilestonesButton() {
        $x("//span[text()='Milestones']").click();
        log.info("The user is on All Milestones page");
        return new AllMilestonesPage();
    }

    public SettingsPage clickSettingsButton() {
        $x("//span[text()='Settings']").click();
        log.info("The user is on Settings page");
        return new SettingsPage();
    }
}
