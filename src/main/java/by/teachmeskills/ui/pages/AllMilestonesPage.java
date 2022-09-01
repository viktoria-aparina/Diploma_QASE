package by.teachmeskills.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AllMilestonesPage {

    public SelenideElement getTitle() {
        return $(By.tagName("h1")).shouldBe(Condition.visible);
    }

    public NewMilestonePage clickCreateMilestoneButton() {
        $x("//a[text()='Create milestone']").shouldBe(Condition.visible).click();
        return new NewMilestonePage();
    }

    public SelenideElement getAlert() {
        return $x("//div[@role='alert']").shouldBe(Condition.visible);
    }
}
