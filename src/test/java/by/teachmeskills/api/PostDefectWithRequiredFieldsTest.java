package by.teachmeskills.api;

import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.defect.Severity;
import by.teachmeskills.api.dto.defect.response.DefectResponse;
import by.teachmeskills.api.providers.DefectProvider;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostDefectWithRequiredFieldsTest extends BaseTest {

    @Test(groups = "smoke API tests", description = "API: Create defect with required fields")
    public void createDefectTestWithRequiredFields() {
        Defect expectedDefect = new DefectProvider().getDefectWithRequiredFields();
        DefectResponse postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);
        assertDefectResponse(postActualDefect);

        DefectResponse getActualDefect = getDefect(postActualDefect);
        assertThat(getActualDefect).as("Defects are different").usingRecursiveComparison()
                                   .comparingOnlyFields("result.title", "result.actual_result")
                                   .isEqualTo(expectedDefect);
        assertThat(getActualDefect.getResult().getSeverity()).as("The severity is different in actual and expected result")
                                                             .isEqualTo(Severity.getSeverityByCode(expectedDefect.getSeverity())
                                                                                .toString());
    }
}
