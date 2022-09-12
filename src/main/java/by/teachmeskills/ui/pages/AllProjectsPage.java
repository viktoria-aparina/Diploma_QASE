package by.teachmeskills.ui.pages;

import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AllProjectsPage extends BasePage {

    @Override
    public boolean isPageOpened() {
        try {
            $x("//a[text()='Create new project']").shouldBe(enabled);
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "All Projects Page", exception.getCause());
            return false;
        }
    }

    public AllProjectsPage open() {
        Selenide.open("/projects");
        return new AllProjectsPage();
    }

    public NewProjectPage clickCreateNewProjectButton() {
        $x("//a[text()='Create new project']").shouldBe(enabled).click();
        return new NewProjectPage();
    }
}
