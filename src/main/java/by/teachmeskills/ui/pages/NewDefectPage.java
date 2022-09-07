package by.teachmeskills.ui.pages;

import by.teachmeskills.ui.dto.defect.Assigned;
import by.teachmeskills.ui.dto.defect.Defect;
import by.teachmeskills.ui.dto.defect.Severity;
import com.codeborne.selenide.ClickOptions;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.io.File;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.id;

@Log4j2
public class NewDefectPage extends BasePage {

    private final String locator = "//label[text()='%s']/ancestor::div[contains(@class, 'col-sm-12')]" +
            "//div[contains(@class, 'notranslate')]";

    @Override
    public boolean isPageOpened() {
        try {
            $x("//button[text()='Cancel']").shouldBe(enabled);
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "New Defect Page", exception.getCause());
            return false;
        }
    }

    public NewDefectPage createNewDefectWithRequiredFields(Defect defect) {
        $(id("title")).sendKeys(defect.getTitle());
        $x("//p[@class='empty-node']").sendKeys(defect.getActualResult());
        $x(String.format(locator, "Severity")).click();
        $x(defect.getSeverity().getLocator()).click();
        log.info("The form was filled in successfully");
        return this;
    }

    public NewDefectPage createNewDefectWithAllFields(Defect defect) {
        $(id("title")).sendKeys(defect.getTitle());
        $x("//p[@class='empty-node']").sendKeys(defect.getActualResult());
        $x(String.format(locator, "Severity")).click();
        $x(defect.getSeverity().getLocator()).click();
        $x(String.format(locator, "Milestone")).click();
        $x(String.format("//div[text()='%s' and contains(@id, 'react-select')]", defect.getMilestone())).click();
        $x(String.format(locator, "Assignee")).click();
        $x(defect.getAssigned().getLocator()).click();
        $x("//div[text()='Select...']/ancestor::div[contains(@class, 'flex-grow-1')]//input").sendKeys(defect.getTag());
        $x("//div[contains(text(), 'Create')]").click();
        $x("//button[text()=' Add attachment']").click(ClickOptions.usingJavaScript());
        $x("//input[@class='dz-hidden-input']").uploadFile(defect.getAttachment());
        log.info("The form was filled in successfully");
        return this;
    }

    public AllDefectsPage submitForm() {
        $x("//button[text()='Create defect']").shouldBe(enabled).click();
        return new AllDefectsPage();
    }

    public Defect getActualDefectInfoRequiredFields() {
        Defect actualDefect = Defect.builder()
                                    .title($(By.id("title")).getValue())
                                    .actualResult($x("//div[contains(@class, 'ProseMirror')]/p").getText())
                                    .severity(Severity.valueOf($x("//label[text()='Severity']/" +
                                                                          "ancestor::div[@id='severityGroup']//div[@class=' css-1uv1cud-singleValue']").getText().toLowerCase()))
                                    .build();
        log.info("The defect was created successfully");
        return actualDefect;
    }

    public Defect getActualDefectInfoAllFields() {
        Defect actualDefect = Defect.builder()
                                    .title($(id("title")).getValue())
                                    .actualResult($x("//div[contains(@class, 'ProseMirror')]/p").getText())
                                    .severity(Severity.valueOf($x("//label[text()='Severity']/" +
                                                                          "ancestor::div[@id='severityGroup']//div[@class=' css-1uv1cud-singleValue']").getText().toLowerCase()))
                                    .milestone($x("//label[text()='Milestone']/ancestor::div[@id='milestoneGroup']//div[@class=' css-1uv1cud-singleValue']").getText())
                                    .assigned(Assigned.valueOf($x("//label[text()='Assignee']/ancestor::div[contains(@class, 'col-lg-12 col-sm-12 col-xs-12')]//div[@class=' css-1uv1cud-singleValue']").getText().toUpperCase()))
                                    .tag($x("//div[@class='css-1rhbuit-multiValue']//span").getText())
                                    .attachment(new File($x("//div[@class='suitecase-attachment-overlay']/p").scrollTo().getOwnText()))
                                    .build();
        log.info("The defect was created successfully");
        return actualDefect;
    }

    public DefectPage clickCancelButtonAndCloseNotificationForm() {
        $x("//button[text()='Cancel']").shouldBe(enabled).click();
        $x("//button[text()='Close form']").shouldBe(enabled).click();
        log.info("The notification eas closed successfully");
        return new DefectPage();
    }

    public NewDefectPage updateDefectTitle(String newDefectTitle) {
        $(id("title")).clear();
        $(id("title")).sendKeys(newDefectTitle);
        log.info("The form was updated successfully");
        return this;
    }

    public AllDefectsPage submitUpdateForm() {
        $x("//button[text()='Update defect']").shouldBe(enabled).click();
        return new AllDefectsPage();
    }
}
