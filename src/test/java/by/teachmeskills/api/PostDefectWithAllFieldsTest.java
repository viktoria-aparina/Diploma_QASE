package by.teachmeskills.api;

import by.teachmeskills.api.dto.attachments.AttachmentResponse;
import by.teachmeskills.api.dto.defect.Defect;
import by.teachmeskills.api.dto.defect.Severity;
import by.teachmeskills.api.dto.defect.response.DefectResponse;
import by.teachmeskills.api.dto.milestones.Milestone;
import by.teachmeskills.api.dto.milestones.response.MilestoneResponse;
import by.teachmeskills.api.providers.DefectProvider;
import by.teachmeskills.api.providers.MilestoneProvider;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostDefectWithAllFieldsTest extends BaseTest {

    private final List<String> hashCodes = new ArrayList<>();
    private int milestoneId;

    @BeforeMethod
    public void prepareFields() {
        String filepath = System.getProperty("user.dir") + "\\src\\main\\resources\\TestAttachment.png";
        AttachmentResponse postAttachmentResponse = attachmentApiClient.postAttachment(new File(filepath), project.getCode(),
                                                                                       HttpStatus.SC_OK);
        assertThat(postAttachmentResponse.isStatus()).as("The status is incorrect").isEqualTo(true);
        hashCodes.add(postAttachmentResponse.getResult().get(0).getHash());

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

    @Test(groups = "smoke API tests")
    public void createDefectTestWithAllFields() {
        Defect expectedDefect = new DefectProvider().getDefectWithAllField(hashCodes, milestoneId);
        DefectResponse postActualDefect = defectApiClients.postDefect(expectedDefect, project.getCode(), HttpStatus.SC_OK);
        assertDefectResponse(postActualDefect);

        DefectResponse getActualDefect = getDefect(postActualDefect);
        assertThat(getActualDefect).as("Defects are different").usingRecursiveComparison()
                                   .comparingOnlyFields("result.title", "result.actual_result",
                                                        "result.milestone_id")
                                   .isEqualTo(expectedDefect);
        assertThat(getActualDefect.getResult().getSeverity()).as("The severity is different in actual and expected result")
                                                             .isEqualTo(Severity.getSeverityByCode(expectedDefect.getSeverity())
                                                                                .toString());
        assertThat(getActualDefect.getResult().getAttachments().get(0).getHash()).as("The attachment's hashCode is different in actual and expected result")
                                                                                 .isEqualTo(expectedDefect.getAttachments().get(0));
        assertThat(getActualDefect.getResult().getTags().get(0).getTitle()).as("The tag is different in actual and expected result")
                                                                           .isEqualTo(expectedDefect.getTags().get(0));
    }
}
