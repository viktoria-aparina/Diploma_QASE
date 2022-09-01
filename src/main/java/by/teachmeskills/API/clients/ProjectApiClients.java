package by.teachmeskills.API.clients;

import by.teachmeskills.API.dto.project.Project;
import by.teachmeskills.API.dto.project.response.ProjectResponse;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class ProjectApiClients extends BaseApiClient {

    private final static String PROJECT_URI = "v1/project";
    private static final String PROJECT_URI_WITH_CODE = PROJECT_URI + "/{code}";
    private static final String PROJECT_CODE = "code";

    public ProjectResponse postProject(Project project, int httpStatusCode) {
        Response response = basePost(PROJECT_URI, project);
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }

    public ProjectResponse getProject(String code, int httpStatusCode) {
        Response response = get(PROJECT_URI_WITH_CODE, Map.of(PROJECT_CODE, code));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }

    public Response getProjectResponse(String code) {
        return get(PROJECT_URI_WITH_CODE, Map.of(PROJECT_CODE, code));
    }
}


