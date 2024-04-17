package blackmhofu.com.media.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MediaUpDateDto {
    UUID mediaId;
    UUID mediaStepId;
}
