package by.teachmeskills.ui.pages;

import static com.codeborne.selenide.Selenide.$x;

public class SettingsPage {

    public AllProjectsPage deleteProject() {
        $x("//a[text()=' Delete project']").click();
        $x("//button[text()=' Delete project']").click();
        return new AllProjectsPage();
    }
}
