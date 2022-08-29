package by.teachmeskills.API;

import by.teachmeskills.API.clients.DefectApiClient;
import by.teachmeskills.API.clients.ProjectApiClients;
import by.teachmeskills.API.dto.defect.Defect;
import by.teachmeskills.API.dto.defect.response.DefectResponse;
import by.teachmeskills.API.dto.project.Project;
import by.teachmeskills.API.dto.project.response.ProjectResponse;
import by.teachmeskills.API.providers.DefectProvider;
import by.teachmeskills.API.providers.ProjectProvider;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefectCrudTest {

    ProjectApiClients projectApiClients = new ProjectApiClients();
    DefectApiClient defectApiClients = new DefectApiClient();
    Project project;
    Faker faker = new Faker();
    private final int limit = faker.number().numberBetween(1, 10);
    private final int offset = faker.number().numberBetween(1, 10);

    @BeforeMethod
    public void createProject() {

        project = new ProjectProvider().getProject();
        ProjectResponse postProject = projectApiClients.postProject(project, HttpStatus.SC_OK);
    }

    @Test
    public void createDefectTest() {

        Defect expectedDefect = new DefectProvider().getDefect();
        DefectResponse postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);

        assertThat(postActualDefect.isStatus()).as("The defect wasn't created").isEqualTo(true);
        assertThat(postActualDefect.getResult().getId()).as("The id is null in the response")
                                                        .isNotNull();

        DefectResponse getActualDefect = defectApiClients.getDefect(project.getCode(), postActualDefect.getResult()
                                                                                                       .getId(), HttpStatus.SC_OK);

        assertThat(expectedDefect).as("Defects are different").usingRecursiveComparison()
                                  .comparingOnlyFields("title", "actual_result")
                                  .isEqualTo(getActualDefect);
    }

    @Test
    public void deleteDefectTest() {

        Defect expectedDefect = new DefectProvider().getDefect();
        DefectResponse postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);

        DefectResponse deletedDefect = defectApiClients.deleteDefect(project.getCode(), postActualDefect.getResult()
                                                                                                        .getId(), HttpStatus.SC_OK);

        assertThat(deletedDefect.isStatus()).as("The defect wasn't deleted").isEqualTo(true);

        Response deletedDefectResponse = defectApiClients.getDefectResponse(project.getCode(), postActualDefect.getResult()
                                                                                                               .getId());

        assertThat(deletedDefectResponse.statusCode()).as("Status code is invalid in case for request")
                                                      .isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

   /* @Test
    public void getAllDefectsTest() {

        AllDefects actualAllDefects = defectApiClients.getAllDefects("DEMO", 3, 0);
        assertThat(actualAllDefects.isStatus()).as("All defects weren't loaded").isEqualTo(true);

        Response actualAllDefectsResponse = defectApiClients.getAllDefectsResponse("DEMO", 3, 0);
        assertThat(actualAllDefectsResponse.jsonPath().get("result.count").toString()).as("Count of project is incorrect")
                                                                                      .isEqualTo(valueOf(limit));
        JsonPath path = actualAllDefectsResponse.body().jsonPath();
        List<Object> entities = path.getList("result.entities");
        entities.size();

        assertThat(entities.size()).as("").isEqualTo(limit);
    }*/

    @Test
    public void updateDefect() {

        Defect expectedDefect = new DefectProvider().getDefect();
        DefectResponse postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);

        Defect updatedDefect = new DefectProvider().getUpdatedDefect();
        DefectResponse patchUpdatedDefect = defectApiClients.updateDefect(updatedDefect, project.getCode(), postActualDefect.getResult()
                                                                                                                            .getId(), HttpStatus.SC_OK);

        assertThat(patchUpdatedDefect.isStatus()).as("The defect wasn't updated").isEqualTo(true);
        assertThat(patchUpdatedDefect.getResult().getId()).as("The id is null in the response")
                                                          .isNotNull();

        DefectResponse getActualDefect = defectApiClients.getDefect(project.getCode(), patchUpdatedDefect.getResult()
                                                                                                         .getId(), HttpStatus.SC_OK);

        assertThat(patchUpdatedDefect).as("Defects are different").usingRecursiveComparison()
                                      .comparingOnlyFields("title")
                                      .isEqualTo(getActualDefect);
    }

    @Test
    public void resolveDefect() {

        Defect expectedDefect = new DefectProvider().getDefect();
        DefectResponse postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);

        DefectResponse patchActualDefect = defectApiClients.resolveDefect(project.getCode(), postActualDefect.getResult()
                                                                                                             .getId(), HttpStatus.SC_OK);

        assertThat(patchActualDefect.isStatus()).as("The defect wasn't updated").isEqualTo(true);
        assertThat(patchActualDefect.getResult().getId()).as("The id is null in the response")
                                                         .isNotNull();

        DefectResponse getActualDefect = defectApiClients.getDefect(project.getCode(), patchActualDefect.getResult()
                                                                                                        .getId(), HttpStatus.SC_OK);

        assertThat(patchActualDefect).as("Defects are different").usingRecursiveComparison()
                                     .comparingOnlyFields("status", "result.id")
                                     .isEqualTo(getActualDefect);
    }
}
