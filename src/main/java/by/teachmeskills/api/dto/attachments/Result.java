package by.teachmeskills.api.dto.attachments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
@Builder
@Accessors(chain = true)
public class Result {

    private String filename;
    private String url;
    private String extension;
    private String hash;
    private String mime;
    private String team;
}
