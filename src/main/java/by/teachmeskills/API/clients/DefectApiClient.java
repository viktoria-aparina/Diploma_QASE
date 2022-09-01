package by.teachmeskills.API.clients;

import by.teachmeskills.API.dto.defect.Defect;
import by.teachmeskills.API.dto.defect.allDefects.AllDefects;
import by.teachmeskills.API.dto.defect.response.DefectResponse;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;

import java.util.Map;

public class DefectApiClient extends BaseApiClient {

    private final static String DEFECT_URI = "v1/defect";
    private static final String DEFECT_URI_WITH_CODE = DEFECT_URI + "/{code}";
    private static final String DEFECT_URI_WITH_CODE_AND_ID = DEFECT_URI_WITH_CODE + "/{id}";
    private static final String DEFECT_URI_WITH_CODE_AND_ID_RESOLVE = DEFECT_URI + "/{code}/resolve/{id}";
    private static final String DEFECT_URI_WITH_CODE_AND_ID_STATUS = DEFECT_URI + "/{code}/status/{id}";
    private static final String DEFECT_CODE = "code";
    private static final String DEFECT_ID = "id";
    private static final String DEFECT_LIMIT = "limit";
    private static final String DEFECT_OFFSET = "offset";

    public DefectResponse postDefect(Defect defect, String code, int httpStatusCode) {
        Response response = post(DEFECT_URI_WITH_CODE, defect, Map.of(DEFECT_CODE, code));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(DefectResponse.class);
    }

    public DefectResponse getDefect(String code, int id, int httpStatusCode) {
        Response response = get(DEFECT_URI_WITH_CODE_AND_ID, Map.of(DEFECT_CODE, code, DEFECT_ID, id));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(DefectResponse.class);
    }

    public Response getDefectResponse(String code, int id) {
        return get(DEFECT_URI_WITH_CODE_AND_ID, Map.of(DEFECT_CODE, code, DEFECT_ID, id));
    }

    public DefectResponse deleteDefect(String code, int id, int httpStatusCode) {
        Response response = delete(DEFECT_URI_WITH_CODE_AND_ID, Map.of(DEFECT_CODE, code, DEFECT_ID, id));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(DefectResponse.class);
    }

    public AllDefects getAllDefects(String code, int limit, int offset) {
        Response response = getAll(DEFECT_URI_WITH_CODE, Map.of(DEFECT_CODE, code), Map.of(DEFECT_LIMIT, limit, DEFECT_OFFSET, offset));
        return response.then()
                       .statusCode(HttpStatus.SC_OK)
                       .extract()
                       .body()
                       .as(AllDefects.class);
    }

    public Response getAllDefectsResponse(String code, int limit, int offset) {
        return getAll(DEFECT_URI_WITH_CODE, Map.of(DEFECT_CODE, code), Map.of(DEFECT_LIMIT, limit, DEFECT_OFFSET, offset));
    }

    public DefectResponse updateDefect(Defect defect, String code, int id, int httpStatusCode) {
        Response response = patchBodyDefect(DEFECT_URI_WITH_CODE_AND_ID, defect, Map.of(DEFECT_CODE, code, DEFECT_ID, id));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(DefectResponse.class);
    }

    public DefectResponse resolveDefect(String code, int id, int httpStatusCode) {
        Response response = patchResolve(DEFECT_URI_WITH_CODE_AND_ID_RESOLVE, Map.of(DEFECT_CODE, code, DEFECT_ID, id));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(DefectResponse.class);
    }

    public DefectResponse changeDefectStatus(Defect defect, String code, int id, int httpStatusCode) {
        Response response = patchBodyDefect(DEFECT_URI_WITH_CODE_AND_ID_STATUS, defect, Map.of(DEFECT_CODE, code, DEFECT_ID, id));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(DefectResponse.class);
    }
}
