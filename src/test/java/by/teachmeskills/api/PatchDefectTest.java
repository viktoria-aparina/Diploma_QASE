package by.teachmeskills.api;

import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.defect.Status;
import by.teachmeskills.api.dto.defect.response.DefectResponse;
import by.teachmeskills.api.providers.DefectProvider;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PatchDefectTest extends BaseTest {

    Defect expectedDefect;
    DefectResponse postActualDefect;

    @BeforeMethod
    public void createDefect() {
        expectedDefect = new DefectProvider().getDefectWithRequiredFields();
        postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);
    }

    @Test
    public void updateDefect() {
        Defect updatedDefect = expectedDefect.setTitle("HOME");
        DefectResponse patchUpdatedDefect = defectApiClients.updateDefect(updatedDefect, project.getCode(),
                                                                          postActualDefect.getResult().getId(),
                                                                          HttpStatus.SC_OK);
        assertDefectResponse(patchUpdatedDefect);

        DefectResponse getActualDefect = getDefect(patchUpdatedDefect);
        assertThat(getActualDefect).as("Defects are different").usingRecursiveComparison()
                                   .comparingOnlyFields("title")
                                   .isEqualTo(updatedDefect);
    }

    @Test
    public void resolveDefect() {
        DefectResponse patchActualDefect = defectApiClients.resolveDefect(project.getCode(),
                                                                          postActualDefect.getResult().getId(),
                                                                          HttpStatus.SC_OK);
        assertDefectResponse(patchActualDefect);

        DefectResponse getActualDefect = getDefect(patchActualDefect);
        assertThat(getActualDefect.getResult().getStatus()).as("The status in the response doesn't match expected status")
                                                           .isEqualTo(Status.RESOLVED.toString().toLowerCase());
    }

    @Test
    public void updateDefectStatus() {
        Defect updatedDefectStatus = expectedDefect.setStatus(Status.IN_PROGRESS.toString().toLowerCase());
        DefectResponse patchUpdatedDefectStatus = defectApiClients.changeDefectStatus(updatedDefectStatus, project.getCode(),
                                                                                      postActualDefect.getResult().getId(),
                                                                                      HttpStatus.SC_OK);
        assertThat(patchUpdatedDefectStatus.isStatus()).as("The status in the response doesn't match expected status")
                                                       .isEqualTo(true);

        DefectResponse getActualDefect = getDefect(postActualDefect);
        assertThat(getActualDefect.getResult().getStatus()).as("Statuses are different")
                                                           .isEqualTo(Status.IN_PROGRESS.toString().toLowerCase());
    }

    @AfterMethod
    public void deleteDefects() {
        defectApiClients.deleteDefect(project.getCode(), postActualDefect.getResult().getId(), HttpStatus.SC_OK);
    }
}