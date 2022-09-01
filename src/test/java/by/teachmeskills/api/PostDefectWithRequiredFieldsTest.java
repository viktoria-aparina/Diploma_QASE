package by.teachmeskills.api;

import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.defect.response.DefectResponse;
import by.teachmeskills.api.providers.DefectProvider;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostDefectWithRequiredFieldsTest extends BaseTest {

    DefectResponse postActualDefect;

    @Test
    public void createDefectTestWithRequiredFields() {
        Defect expectedDefect = new DefectProvider().getDefectWithRequiredFields();
        postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);
        assertDefectResponse(postActualDefect);

        DefectResponse getActualDefect = getDefect(postActualDefect);
        assertThat(getActualDefect).as("Defects are different").usingRecursiveComparison()
                                   .comparingOnlyFields("result.title", "result.actual_result", "result.severity")
                                   .isEqualTo(expectedDefect);
    }

    @AfterMethod
    public void deleteDefect() {
        defectApiClients.deleteDefect(project.getCode(), postActualDefect.getResult().getId(), HttpStatus.SC_OK);
    }
}
