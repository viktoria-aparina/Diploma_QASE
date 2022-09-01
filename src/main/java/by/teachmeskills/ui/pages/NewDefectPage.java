package by.teachmeskills.ui.pages;

import by.teachmeskills.ui.dto.defect.Assigned;
import by.teachmeskills.ui.dto.defect.Defect;
import by.teachmeskills.ui.dto.defect.Severity;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.id;

@Log4j2
public class NewDefectPage {

    private final String locator = "//label[text()='%s']/ancestor::div[contains(@class, 'col-sm-12')]" +
            "//div[contains(@class, 'notranslate')]";


    public SelenideElement getTitleCreateDefect() {
        return $(By.tagName("h1")).shouldBe(Condition.visible);
    }

    public SelenideElement getTitleEditDefect() {
        return $(By.tagName("h1")).shouldBe(Condition.visible);
    }

    public NewDefectPage createNewDefectWithRequiredFields(Defect defect) {
        $(id("title")).sendKeys(defect.getTitle());
        $x("//div[@class='ProseMirror toastui-editor-contents']").sendKeys(defect.getActualResult());
        $x(String.format(locator, "Severity")).click();
        $(id(defect.getSeverity().getLocator())).click();
        log.info("The form was filled in successfully");
        return this;
    }

    public NewDefectPage createNewDefectWithAllFields(Defect defect) {

        $(id("title")).sendKeys(defect.getTitle());
        $x("//div[@class='ProseMirror toastui-editor-contents']").sendKeys(defect.getActualResult());
        $x(String.format(locator, "Severity")).click();
        $x(defect.getSeverity().getLocator()).click();
        $x(String.format(locator, "Milestone")).click();
        $x(String.format("//div[text()='%s' and contains(@id, 'react-select')]", defect.getMilestone())).click();
        $x(String.format(locator, "Assignee")).click();
        $x(defect.getAssigned().getLocator()).click();

        $x("//div[text()='Select...']/ancestor::div[contains(@class, 'flex-grow-1')]//input").sendKeys(defect.getTag());
        $x("//div[contains(text(), 'Create')]").click();

       /* $x("//button[text()=' Add attachment']").click();
        String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";

        SelenideElement element = $x("//form[contains(@class,'dropzone')]");
        ((JavascriptExecutor) getWebDriver()).executeScript(js, element);

        element.uploadFile(defect.getAttachment());*/
        log.info("The form was filled in successfully");
        return this;
    }

    public AllDefectsPage submitForm() {
        $x("//button[text()='Create defect']").click();
        return new AllDefectsPage();
    }

    public Defect getActualDefectInfoRequiredFields() {
        Defect actualDefect = Defect.builder()
                                    .title($(By.id("title")).getValue())
                                    .actualResult($x("//div[contains(@class, 'ProseMirror')]/p").getText())
                                    .severity(Severity.valueOf($x("//label[text()='Severity'/" +
                                                                          "ancestor::div[@id='severityGroup']//div[@class=' css-1uv1cud-singleValue']").getText().toUpperCase()))
                                    .build();
        log.info("The defect was created successfully");
        return actualDefect;
    }

    public Defect getActualDefectInfoAllFields() {
        Defect actualDefect = Defect.builder()
                                    .title($(id("title")).getValue())
                                    .actualResult($x("//div[contains(@class, 'ProseMirror')]/p").getText())
                                    .severity(Severity.valueOf($x("//label[text()='Severity']/" +
                                                                          "ancestor::div[@id='severityGroup']//div[@class=' css-1uv1cud-singleValue']").getText().toUpperCase()))
                                    .milestone($x("//label[text()='Milestone']/ancestor::div[@id='milestoneGroup']//div[@class=' css-1uv1cud-singleValue']").getText())
                                    .assigned(Assigned.valueOf($x("//label[text()='Assignee']/ancestor::div[contains(@class, 'col-lg-12 col-sm-12 col-xs-12')]//div[@class=' css-1uv1cud-singleValue']").getText().toUpperCase()))
                                    .tag($x("//div[@class='css-1rhbuit-multiValue']//span").getText())
                                    .build();
        log.info("The defect was created successfully");
        return actualDefect;
    }

    public DefectPage clickCancelButtonAndCloseNotificationForm() {
        $x("//button[text()='Cancel']").click();
        $x("//button[text()='Close form']").click();
        log.info("The notification eas closed successfully");
        return new DefectPage();
    }
}
