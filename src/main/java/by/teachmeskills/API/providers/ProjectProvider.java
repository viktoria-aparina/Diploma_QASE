package by.teachmeskills.API.providers;

import by.teachmeskills.API.dto.project.Project;
import by.teachmeskills.API.dto.project.ProjectAccess;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class ProjectProvider {

    Faker faker = new Faker();

    public Project getProject() {
        return Project.builder()
                      .title(faker.company().name())
                      .code(RandomStringUtils.randomAlphabetic(5).toUpperCase())
                      .description(faker.internet().url())
                      .accessLevel(ProjectAccess.NONE)
                      .build();
    }
}
