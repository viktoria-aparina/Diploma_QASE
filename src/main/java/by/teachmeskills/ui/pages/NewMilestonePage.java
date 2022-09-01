package by.teachmeskills.ui.pages;

import by.teachmeskills.ui.dto.milestone.Milestone;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.id;

@Log4j2
public class NewMilestonePage {

    public SelenideElement getTitle() {
        return $(By.tagName("h1")).shouldBe(visible);
    }

    public NewMilestonePage createNewMilestone(Milestone milestone) {
        $(id("title")).sendKeys(milestone.getName());
        $x("//p[@class='empty-node']").click();
        $x("//p[@class='empty-node']").sendKeys(milestone.getDescription());
        $x("//label[text()='Status']/ancestor::div[contains(@class, 'col-sm-12')]" +
                   "//div[contains(@class, 'notranslate')]").click();
        $x(milestone.getStatus().getLocator()).click();
        $x("//input[@name='dueDate']").sendKeys(milestone.getDate());
        log.info("The form was filled in successfully");
        return this;
    }

    public AllMilestonesPage submitForm() {
        $(By.xpath("//button[text()='Create milestone']")).click();
        log.info("The milestone was created successfully");
        return new AllMilestonesPage();
    }
}
