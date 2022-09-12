package by.teachmeskills.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AllMilestonesPage extends BasePage {

    @Override
    public boolean isPageOpened() {
        try {
            $x("//a[text()='Create milestone']").shouldBe(enabled);
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "All Milestones Page", exception.getCause());
            return false;
        }
    }

    public NewMilestonePage clickCreateMilestoneButton() {
        $x("//a[text()='Create milestone']").shouldBe(enabled).click();
        return new NewMilestonePage();
    }

    public SelenideElement getAlert() {
        return $x("//div[@role='alert']").shouldBe(visible);
    }
}
