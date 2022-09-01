package by.teachmeskills.API.providers;

import by.teachmeskills.API.dto.defect.Defect;
import by.teachmeskills.API.dto.defect.DefectSeverity;
import by.teachmeskills.API.dto.defect.Status;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class DefectProvider {

    Faker faker = new Faker();

    public Defect getDefect() {
        return Defect.builder()
                     .title(faker.company().name())
                     .actualResult(RandomStringUtils.randomAlphabetic(10))
                     .severity(faker.number().numberBetween(1, 6))
                     .build();
    }

    public Defect getUpdatedDefect() {
        return Defect.builder()
                     .title(faker.company().name())
                     .actualResult(RandomStringUtils.randomAlphabetic(8))
                     .severity(faker.number().numberBetween(1, 6))
                     .build();
    }

    public Defect setStatus() {
        return Defect.builder()
                     .status(Status.INVALID)
                     .build();
    }
}
