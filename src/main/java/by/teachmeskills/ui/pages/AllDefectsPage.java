package by.teachmeskills.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AllDefectsPage {

    public SelenideElement getTitle() {
        return $(By.tagName("h1")).shouldBe(Condition.visible);
    }

    public NewDefectPage clickCreateNewDefectButton() {
        $x("//a[text()='Create new defect']").click();
        return new NewDefectPage();
    }

    public SelenideElement getAlert() {
        return $x("//div[@role='alert']").shouldBe(Condition.visible);
    }

    public DefectPage clickDropDownButtonEdit(String defectTitle) {
        $x(String.format("//a[text()='%s']/ancestor::tr[@class='project-row']//a[contains(@class, 'btn-dropdown')]", defectTitle)).click();
        $x(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']//a[text()='Edit']", defectTitle)).click();
        return new DefectPage();
    }
}
