package by.teachmeskills.api;

import by.teachmeskills.api.dto.allDefects.AllDefects;
import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.defect.response.DefectResponse;
import by.teachmeskills.api.providers.DefectProvider;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class GetAllDefectsTest extends BaseTest {

    @BeforeMethod
    public void createProjectAndDefects() {
        Defect expectedDefect1 = new DefectProvider().getDefectWithRequiredFields();
        DefectResponse postActualDefect1 = defectApiClients.postDefect(expectedDefect1, project.getCode(), HttpStatus.SC_OK);
        Defect expectedDefect2 = new DefectProvider().getDefectWithRequiredFields();
        DefectResponse postActualDefect2 = defectApiClients.postDefect(expectedDefect2, project.getCode(), HttpStatus.SC_OK);
        Defect expectedDefect3 = new DefectProvider().getDefectWithRequiredFields();
        DefectResponse postActualDefect3 = defectApiClients.postDefect(expectedDefect3, project.getCode(), HttpStatus.SC_OK);
    }

    @Test(groups = "smoke API tests", description = "API: Get all project's defects")
    public void getAllDefectsTest() {
        int limit = 2;
        int offset = 1;
        AllDefects actualAllDefects = defectApiClients.getAllDefects(project.getCode(), limit, offset);
        assertThat(actualAllDefects.isStatus()).as("All defects weren't loaded").isEqualTo(true);

        Response actualAllDefectsResponse = defectApiClients.getAllDefectsResponse(project.getCode(), limit, offset);
        assertThat(actualAllDefectsResponse.jsonPath().get("result.count").toString()).as("Count of project is incorrect")
                                                                                      .isEqualTo(valueOf(2));
        assertThat(actualAllDefectsResponse.jsonPath().getList("result.entities").size()).as("").isEqualTo(2);
    }
}
