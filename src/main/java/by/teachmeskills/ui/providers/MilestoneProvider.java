package by.teachmeskills.ui.providers;

import by.teachmeskills.ui.dto.milestone.Milestone;
import by.teachmeskills.ui.dto.milestone.Status;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class MilestoneProvider {

    Faker faker = new Faker();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public Milestone getMilestone() {
        return Milestone.builder()
                        .name(faker.name().title())
                        .description(faker.internet().emailAddress())
                        .status(Status.ACTIVE)
                        .date(formatter.format(faker.date().future(10, TimeUnit.DAYS)))
                        .build();
    }
}
