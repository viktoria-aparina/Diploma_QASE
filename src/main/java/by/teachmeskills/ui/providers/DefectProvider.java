package by.teachmeskills.ui.providers;

import by.teachmeskills.ui.dto.defect.Assigned;
import by.teachmeskills.ui.dto.defect.Defect;
import by.teachmeskills.ui.dto.defect.Severity;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;

public class DefectProvider {

    Faker faker = new Faker();

    public Defect getDefectWithRequiredFields() {
        return Defect.builder()
                     .title(faker.company().catchPhrase())
                     .actualResult(faker.app().author())
                     .severity(Severity.valueOf(Severity.CRITICAL.toString().toUpperCase()))
                     .build();
    }

    public Defect getDefectWithAllFields(String milestonename) {
        return Defect.builder()
                     .title(faker.company().catchPhrase())
                     .actualResult(faker.app().author())
                     .severity(Severity.valueOf(Severity.NORMAL.toString().toUpperCase()))
                     .milestone(milestonename)
                     .assigned(Assigned.valueOf(Assigned.UNASSIGNED.toString().toUpperCase()))
                     .tag(RandomStringUtils.randomAlphabetic(3))
                     .attachment(new File(System.getProperty("user.dir") + "\\TestAttachment.png"))
                     .build();
    }
}
