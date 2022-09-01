package by.teachmeskills.api;

import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.allDefects.AllDefects;
import by.teachmeskills.api.dto.defect.response.DefectResponse;
import by.teachmeskills.api.providers.DefectProvider;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class GetAllDefectsTest extends BaseTest {

    Defect expectedDefect1;
    Defect expectedDefect2;
    Defect expectedDefect3;
    DefectResponse postActualDefect1;
    DefectResponse postActualDefect2;
    DefectResponse postActualDefect3;

    @BeforeMethod
    public void createProjectAndDefects() {
        expectedDefect1 = new DefectProvider().getDefectWithRequiredFields();
        postActualDefect1 = defectApiClients.postDefect(expectedDefect1, project.getCode(), HttpStatus.SC_OK);
        expectedDefect2 = new DefectProvider().getDefectWithRequiredFields();
        postActualDefect2 = defectApiClients.postDefect(expectedDefect2, project.getCode(), HttpStatus.SC_OK);
        expectedDefect3 = new DefectProvider().getDefectWithRequiredFields();
        postActualDefect3 = defectApiClients.postDefect(expectedDefect3, project.getCode(), HttpStatus.SC_OK);
    }

    @Test
    public void getAllDefectsTest() {
        int limit = 2;
        int offset = 1;
        AllDefects actualAllDefects = defectApiClients.getAllDefects(project.getCode(), limit, offset);
        assertThat(actualAllDefects.isStatus()).as("All defects weren't loaded").isEqualTo(true);

        Response actualAllDefectsResponse = defectApiClients.getAllDefectsResponse(project.getCode(), limit, offset);
        assertThat(actualAllDefectsResponse.jsonPath().get("result.count").toString()).as("Count of project is incorrect")
                                                                                      .isEqualTo(valueOf(2));
    }

    @AfterMethod
    public void deleteDefects() {
        defectApiClients.deleteDefect(project.getCode(), postActualDefect1.getResult().getId(), HttpStatus.SC_OK);
        defectApiClients.deleteDefect(project.getCode(), postActualDefect2.getResult().getId(), HttpStatus.SC_OK);
        defectApiClients.deleteDefect(project.getCode(), postActualDefect3.getResult().getId(), HttpStatus.SC_OK);
    }
}
