package by.teachmeskills.ui.pages;

import by.teachmeskills.ui.dto.project.Project;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

@Log4j2
public class NewProjectPage extends BasePage {

    @Override
    public boolean isPageOpened() {
        try {
            $x("//button[text()='Create project']").shouldBe(enabled);
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "New Project Page", exception.getCause());
            return false;
        }
    }

    public NewProjectPage createNewProject(Project project) {
        $(By.id("inputTitle")).sendKeys(project.getName());
        $(By.id("inputCode")).clear();
        $(By.id("inputCode")).sendKeys(project.getCode());
        $(By.id("inputDescription")).sendKeys(project.getDescription());
        $(By.id("public-access-type")).click();
        log.info("The form was filled in successfully");
        return this;
    }

    public RepositoryPage submitForm() {
        $(By.xpath("//button[text()='Create project']")).click();
        log.info("The project was created successfully");
        return new RepositoryPage();
    }
}
