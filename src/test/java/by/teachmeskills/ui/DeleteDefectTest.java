package by.teachmeskills.ui;

import by.teachmeskills.ui.steps.DefectStep;
import org.testng.annotations.BeforeMethod;

public class DeleteDefectTest {

    @BeforeMethod
    public void createDefect() {
        new DefectStep().createDefectWithRequiredFields();
    }
}