package by.teachmeskills.API.dto.defect.allDefects;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class DefectModal {

    public int id;
    public String title;
    @SerializedName("actual_result")
    public String actualResult;
    public String status;
    @SerializedName("milestone_id")
    public Object milestoneId;
    @SerializedName("project_id")
    public int projectId;
    public String severity;
    @SerializedName("member_id")
    public int memberId;
    public List<Object> attachments;
    @SerializedName("custom_fields")
    public List<Object> customFields;
    @SerializedName("external_data")
    public String externalData;
    @SerializedName("resolved_at")
    public Date resolvedAt;
    public String created;
    public String updated;
    @SerializedName("created_at")
    public Date createdAt;
    @SerializedName("updated_at")
    public Date updatedAt;
    public List<Tag> tags;
}
