package by.teachmeskills.api.providers;

import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.defect.Severity;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class DefectProvider {

    Faker faker = new Faker();

    public Defect getDefectWithRequiredFields() {
        return Defect.builder()
                     .title(faker.company().name())
                     .actualResult(RandomStringUtils.randomAlphabetic(10))
                     .severity(faker.number().numberBetween(1, Severity.values().length))
                     .build();
    }

    public Defect getDefectWithAllField(List<String> hashCodes, String milestoneId) {
        List<String> tags = new ArrayList<>();
        tags.add(RandomStringUtils.randomAlphabetic(3));
        return Defect.builder()
                     .title(faker.company().name())
                     .actualResult(RandomStringUtils.randomAlphabetic(10))
                     .severity(faker.number().numberBetween(1, Severity.values().length))
                     .milestoneId(milestoneId)
                     .attachments(hashCodes)
                     .tags(tags)
                     .build();
    }
}
