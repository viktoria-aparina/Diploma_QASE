package by.teachmeskills.api.providers;

import by.teachmeskills.api.dto.milestones.Milestone;
import com.github.javafaker.Faker;

public class MilestoneProvider {

    Faker faker = new Faker();

    public Milestone getMilestone() {
        return Milestone.builder()
                        .title(faker.backToTheFuture().date())
                        .description(faker.howIMetYourMother().catchPhrase())
                        .build();
    }
}
