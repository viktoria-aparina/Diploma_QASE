package by.teachmeskills.api.dto.defect.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class DefectResponse {

    private boolean status;
    private Result result;
    private String errorMessage;
    private List<ErrorFields> errorFields;
}
