package blackmhofu.com.media.dto;

import blackmhofu.com.order_step.model.Order_Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaResDto {

    UUID id;
    String name;
    Long size;
    String executor;
    int order_step;
}
