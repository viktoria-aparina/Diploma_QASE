package by.teachmeskills.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RepositoryPage {

    public SelenideElement getTitle() {
        return $(By.tagName("h1")).shouldBe(Condition.visible);
    }
}
