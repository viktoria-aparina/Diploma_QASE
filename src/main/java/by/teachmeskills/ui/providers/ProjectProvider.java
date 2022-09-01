package by.teachmeskills.ui.providers;

import by.teachmeskills.ui.dto.project.Project;
import by.teachmeskills.ui.dto.project.ProjectAccess;
import com.github.javafaker.Faker;

public class ProjectProvider {

    Faker faker = new Faker();

    public Project getProject() {
        return Project.builder()
                      .name(faker.company().name())
                      .code(faker.code().ean8())
                      .description(faker.programmingLanguage().name())
                      .accessLevel(ProjectAccess.PUBLIC)
                      .build();
    }
}
