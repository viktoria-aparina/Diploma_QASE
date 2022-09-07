package by.teachmeskills.api.clients;

import by.teachmeskills.api.dto.project.Project;
import by.teachmeskills.api.dto.project.response.ProjectResponse;
import io.restassured.response.Response;

import java.util.Map;

public class ProjectApiClient extends BaseApiClient {

    private static final String PROJECT_URI = "v1/project";
    private static final String PROJECT_URI_WITH_CODE = PROJECT_URI + "/{code}";
    private static final String PROJECT_CODE = "code";

    public ProjectResponse postProject(Project project, int httpStatusCode) {
        Response response = basePostProject(PROJECT_URI, project);
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }

    public ProjectResponse deleteProject(String code, int httpStatusCode) {
        Response response = baseDeleteProject(PROJECT_URI_WITH_CODE, Map.of(PROJECT_CODE, code));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }
}


