package by.teachmeskills.api.clients;

import by.teachmeskills.api.dto.milestones.Milestone;
import by.teachmeskills.api.dto.milestones.MilestoneResponse;
import io.restassured.response.Response;

import java.util.Map;

public class MilestonesApiClient extends BaseApiClient {

    private static final String MILESTONE_URI = "v1/milestone";
    private static final String MILESTONE_URI_WITH_CODE = MILESTONE_URI + "/{code}";
    private static final String PROJECT_CODE = "code";

    public MilestoneResponse postMilestone(Milestone milestone, String code, int httpStatusCode) {

        Response response = basePostMilestone(MILESTONE_URI_WITH_CODE, milestone, Map.of(PROJECT_CODE, code));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(MilestoneResponse.class);
    }
}
