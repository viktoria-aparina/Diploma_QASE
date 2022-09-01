package by.teachmeskills.api;

import by.teachmeskills.api.dto.attachments.AttachmentResponse;
import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.defect.response.DefectResponse;
import by.teachmeskills.api.dto.milestones.Milestone;
import by.teachmeskills.api.dto.milestones.MilestoneResponse;
import by.teachmeskills.api.providers.DefectProvider;
import by.teachmeskills.api.providers.MilestoneProvider;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class PostDefectWithAllFieldsTest extends BaseTest {

    DefectResponse postActualDefect;
    private String hashCode;
    private int milestoneId;

    @BeforeMethod
    public void prepareFields() {

        String filepath = System.getProperty("user.dir") + "\\TestAttachment.png";
        AttachmentResponse postAttachmentResponse = attachmentApiClient.postAttachment(new File(filepath), project.getCode(),
                                                                                       HttpStatus.SC_OK);
        assertThat(postAttachmentResponse.isStatus()).as("The status is incorrect").isEqualTo(true);
        hashCode = postAttachmentResponse.getResult().get(0).getHash();

        Milestone expectedMilestone = new MilestoneProvider().getMilestone();
        MilestoneResponse postActualMilestone = milestonesApiClient.postMilestone(expectedMilestone, project.getCode(),
                                                                                  HttpStatus.SC_OK);
        assertThat(postActualMilestone.isStatus()).as("The status in the response doesn't match expected status")
                                                  .isEqualTo(true);

        assertThat(postActualMilestone.getResult().getId()).as("The id is null in the response")
                                                           .isNotNull()
                                                           .isNotEqualTo(0);
        milestoneId = postActualMilestone.getResult().getId();
    }

    @Test
    public void createDefectTestWithAllFields() {
        Defect expectedDefect = new DefectProvider().getDefectWithAllField(hashCode, milestoneId);
        postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);
        assertDefectResponse(postActualDefect);

        DefectResponse getActualDefect = getDefect(postActualDefect);
        assertThat(getActualDefect).as("Defects are different").usingRecursiveComparison()
                                   .comparingOnlyFields("result.title", "result.actual_result", "result.severity", "result.attachment", "result.tag")
                                   .isEqualTo(expectedDefect);
    }

    @AfterMethod
    public void deleteDefect() {
        defectApiClients.deleteDefect(project.getCode(), postActualDefect.getResult().getId(), HttpStatus.SC_OK);
    }
}
