package by.teachmeskills.api;

import by.teachmeskills.api.clients.AttachmentApiClient;
import by.teachmeskills.api.clients.DefectApiClient;
import by.teachmeskills.api.clients.MilestonesApiClient;
import by.teachmeskills.api.clients.ProjectApiClient;
import by.teachmeskills.api.dto.defect.response.DefectResponse;
import by.teachmeskills.api.dto.project.Project;
import by.teachmeskills.api.providers.ProjectProvider;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    Project project;
    ProjectApiClient projectApiClient = new ProjectApiClient();
    DefectApiClient defectApiClients = new DefectApiClient();
    AttachmentApiClient attachmentApiClient = new AttachmentApiClient();
    MilestonesApiClient milestonesApiClient = new MilestonesApiClient();

    @BeforeMethod(groups = {"smoke API tests", "regression API tests"})
    public void createProject() {
        project = new ProjectProvider().getProject();
        projectApiClient.postProject(project, HttpStatus.SC_OK);
    }

    @AfterMethod(alwaysRun = true)
    public void deleteProject() {
        projectApiClient.deleteProject(project.getCode(), HttpStatus.SC_OK);
    }

    void assertDefectResponse(DefectResponse actualDefect) {
        assertThat(actualDefect.isStatus()).as("The status in the response doesn't match expected status")
                                           .isEqualTo(true);

        assertThat(actualDefect.getResult().getId()).as("The id is null in the response")
                                                    .isNotNull()
                                                    .isNotEqualTo(0);
    }

    DefectResponse getDefect(DefectResponse actualDefect) {
        return defectApiClients.getDefect(project.getCode(), actualDefect.getResult().getId(), HttpStatus.SC_OK);
    }
}
