package by.teachmeskills.ui.pages;

import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class AllProjectsPage {

    public AllProjectsPage open() {
        Selenide.open("/projects");
        return new AllProjectsPage();
    }

    public NewProjectPage clickCreateNewProjectButton() {
        $(By.id("createButton")).click();
        return new NewProjectPage();
    }
}
