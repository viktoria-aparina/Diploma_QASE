package by.teachmeskills.api.providers;

import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.defect.DefectSeverity;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class DefectProvider {

    Faker faker = new Faker();

    public Defect getDefectWithRequiredFields() {
        return Defect.builder()
                     .title(faker.company().name())
                     .actualResult(RandomStringUtils.randomAlphabetic(10))
                     .severity(faker.number().numberBetween(1, DefectSeverity.values().length))
                     .build();
    }

    public Defect getDefectWithAllField(String hashcode, int milestoneId) {
        return Defect.builder()
                     .title(faker.company().name())
                     .actualResult(RandomStringUtils.randomAlphabetic(10))
                     .severity(faker.number().numberBetween(1, DefectSeverity.values().length))
                     .milestoneId(milestoneId)
                     .attachment(hashcode)
                     .tag(RandomStringUtils.randomAlphabetic(3))
                     .build();
    }
}
