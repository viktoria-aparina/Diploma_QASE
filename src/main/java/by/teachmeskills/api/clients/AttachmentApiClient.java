package by.teachmeskills.api.clients;

import by.teachmeskills.api.dto.attachments.AttachmentResponse;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

public class AttachmentApiClient extends BaseApiClient {

    private static final String ATTACHMENT_URI = "v1/attachment";
    private static final String ATTACHMENT_URI_WITH_CODE = ATTACHMENT_URI + "/{code}";
    private static final String PROJECT_CODE = "code";

    public AttachmentResponse postAttachment(File attachment, String code, int httpStatusCode) {
        Response response = basePostAttachment(ATTACHMENT_URI_WITH_CODE, attachment, Map.of(PROJECT_CODE, code));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(AttachmentResponse.class);
    }
}
