package blackmhofu.com.order_step.mapper;


import blackmhofu.com.media.model.Media;
import blackmhofu.com.media.service.MediaServiceImpl;
import blackmhofu.com.order_step.dto.OrderStepResDto;
import blackmhofu.com.order_step.model.Order_Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderStepMapper {
    @Autowired
    private MediaServiceImpl mediaService;
    public OrderStepResDto toDto(Order_Step orderStep){

        List<Media> stepOrderMedia = mediaService.findAllByStepOrderId(orderStep.getId());

        StringBuilder mediaList = new StringBuilder();
        stepOrderMedia.forEach(media -> mediaList.append(media.getName()).append(","));



        return OrderStepResDto
                .builder()
                .id(orderStep.getId())
                .createdAt(orderStep.getOrder().getCreatedAt())
                .stepNumber(orderStep.getStep().getStepNumber())
                .mediaList(mediaList.toString())
                .description(orderStep.getDescription())
                .build();
    }
}
