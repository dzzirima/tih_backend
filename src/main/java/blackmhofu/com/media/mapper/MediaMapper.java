package blackmhofu.com.media.mapper;

import blackmhofu.com.media.dto.MediaResDto;
import blackmhofu.com.media.model.Media;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {

    public MediaResDto dto(Media media){
        return  MediaResDto
                .builder()
                .size(media.getSize())
                .name(media.getName())
                .executor("N/A")
                .order_step(2)
//                .order_step(media.getOrder_step().getStep().getStepNumber())
                .build();
    }
}
