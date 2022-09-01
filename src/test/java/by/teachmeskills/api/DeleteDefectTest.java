package by.teachmeskills.api;

import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.defect.response.DefectResponse;
import by.teachmeskills.api.providers.DefectProvider;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteDefectTest extends BaseTest {

    Defect expectedDefect;
    DefectResponse postActualDefect;

    @BeforeMethod
    public void createDefect() {
        expectedDefect = new DefectProvider().getDefectWithRequiredFields();
        postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);
    }

    @Test
    public void deleteDefectTest() {
        DefectResponse deletedDefect = defectApiClients.deleteDefect(project.getCode(),
                                                                     postActualDefect.getResult().getId(),
                                                                     HttpStatus.SC_OK);
        assertThat(deletedDefect.isStatus()).as("The status in the response doesn't match expected status")
                                            .isEqualTo(true);

        assertThat(deletedDefect.getResult().getId()).as("The id is null in the response")
                                                     .isNotNull()
                                                     .isNotEqualTo(0);

        Response deletedDefectResponse = defectApiClients.getDefectResponse(project.getCode(),
                                                                            postActualDefect.getResult().getId());
        assertThat(deletedDefectResponse.statusCode()).as("Status code is invalid in case for request")
                                                      .isEqualTo(HttpStatus.SC_NOT_FOUND);
    }
}
