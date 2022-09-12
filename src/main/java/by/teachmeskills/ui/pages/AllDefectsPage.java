package by.teachmeskills.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AllDefectsPage extends BasePage {

    @Override
    public boolean isPageOpened() {
        try {
            $x("//a[text()='Create new defect']").shouldBe(enabled);
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "All Defects Page", exception.getCause());
            return false;
        }
    }

    public NewDefectPage clickCreateNewDefectButton() {
        $x("//a[text()='Create new defect']").shouldBe(enabled).click();
        return new NewDefectPage();
    }

    public SelenideElement getAlert() {
        return $x("//div[@role='alert']").shouldBe(visible);
    }

    public String alertText() {
        return $x("//div[@role='alert']//span//span").getText();
    }

    public AllDefectsPage clickDropDownButton(String defectTitle, String nameButton) {
        $x(String.format("//a[text()='%s']/ancestor::tr[@class='project-row']//a[contains(@class, 'btn-dropdown')]", defectTitle)).click();
        $x(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']//a[text()='%s']", defectTitle, nameButton)).click();
        return new AllDefectsPage();
    }

    public AllDefectsPage clickDeleteInNotification() {
        $x("//span[text()='Delete']").shouldBe(enabled).click();
        return new AllDefectsPage();
    }

    public boolean isDefectDelete(String defectTitle) {

        try {
            $x(String.format("//a[text()='%s']", defectTitle));
            return false;
        } catch (NoSuchElementException exception) {
            log.error("The defect {} was not found, because of error {}", defectTitle, exception.getCause());
            return true;
        }
    }

    public AllDefectsPage clickConfirmInNotification() {
        $x("//button[text()='Confirm']").shouldBe(enabled).click();
        return this;
    }

    public DefectPage clickDefectTitle(String defectTitle) {
        $x(String.format("//a[text()='%s']", defectTitle)).shouldBe(enabled).click();
        return new DefectPage();
    }

    public boolean isVisibleNewDefectTitle(String defectTitle) {
        try {
            $x(String.format("//a[text()='%s']", defectTitle)).shouldBe(visible);
            return true;
        } catch (NoSuchElementException exception) {
            log.error("The defect {} was not found, because of error {}", defectTitle, exception.getCause());
            return false;
        }
    }

    public AllDefectsPage clickStatusButton(String status) {
        $x("//div[@class='filter-item']").shouldBe(enabled).click();
        String filterLocator = "//span[text()='%s']/ancestor::div[@class='checkbox']//span[@class='custom-control-indicator']";
        $x(String.format(filterLocator, "Open")).click();
        $x(String.format(filterLocator, status)).click();
        return this;
    }
}
