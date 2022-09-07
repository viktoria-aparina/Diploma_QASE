package by.teachmeskills.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class DefectPage extends BasePage {

    @Override
    public boolean isPageOpened() {
        try {
            $x("//button[text()='Send']").shouldBe(enabled);
            return true;
        } catch (TimeoutException exception) {
            log.error("The page {} was not opened, because of error {}", "Defect Page", exception.getCause());
            return false;
        }
    }

    public SelenideElement getStatus(String status) {
        return $x(String.format("//span[contains(text(), '%s')]", status)).shouldBe(visible);
    }
}
