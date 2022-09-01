package by.teachmeskills.ui.pages;

import static com.codeborne.selenide.Selenide.$x;

public class DefectPage {

    public NewDefectPage clickEditButton() {
        $x("//a[text()=' Edit']").click();
        return new NewDefectPage();
    }
}
