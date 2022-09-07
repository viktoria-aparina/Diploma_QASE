package by.teachmeskills.ui.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class SettingsPage extends BasePage {

    @Override
    public boolean isPageOpened() {
        try {
            $x("//a[text()=' Delete project']").shouldBe(enabled);
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "Settings Page", exception.getCause());
            return false;
        }
    }

    public AllProjectsPage deleteProject() {
        $x("//a[text()=' Delete project']").shouldBe(enabled).click();
        $x("//button[text()=' Delete project']").shouldBe(enabled).click();
        return new AllProjectsPage();
    }
}
