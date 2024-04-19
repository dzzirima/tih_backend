package blackmhofu.com.order_step.service;

import blackmhofu.com.client_order.model.ClientOrder;
import blackmhofu.com.client_order.service.ClientOrderServiceImpl;
import blackmhofu.com.media.dto.MediaUpDateDto;
import blackmhofu.com.media.service.MediaServiceImpl;
import blackmhofu.com.order_step.dto.OrderStepReqDto;
import blackmhofu.com.order_step.dto.OrderStepResDto;
import blackmhofu.com.order_step.mapper.OrderStepMapper;
import blackmhofu.com.order_step.model.Order_Step;
import blackmhofu.com.order_step.repository.OrderStepRepository;
import blackmhofu.com.step.model.Step;
import blackmhofu.com.step.service.StepServiceImpl;
import blackmhofu.com.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class OrderStepServiceImpl implements  IOrderStepService {

    @Autowired
    private ClientOrderServiceImpl clientOrderService;

    @Autowired
    private StepServiceImpl stepService;

    @Autowired
    private OrderStepRepository orderStepRepository;

    @Autowired
    private OrderStepMapper orderStepMapper;

    @Autowired
    private MediaServiceImpl mediaService;

    @Override
    public OrderStepResDto save(OrderStepReqDto orderStepReqDto) {

        ClientOrder foundClientOrder = clientOrderService.findById(orderStepReqDto.getOrderId());
        Step foundStep = stepService.findById(orderStepReqDto.getStepId());
        
        Order_Step orderStepToSave = Order_Step
                .builder()
                .order(foundClientOrder)
                .step(foundStep)
                .description(orderStepReqDto.getDescription())
                .build();
        Order_Step savedOeOrderStep = orderStepRepository.save(orderStepToSave);

        // attaching the media

        if(!orderStepReqDto.getAttachedMediaIdsList().isEmpty()){

            String[] mediaIdArray = orderStepReqDto.getAttachedMediaIdsList().split(",");

            // then for each id then lets get the corresponding id in db

            for (String stringMediaId:mediaIdArray) {
                UUID  mediaId = UUID.fromString(stringMediaId);

                // create a media update dto
                MediaUpDateDto mediaUpDateDto = MediaUpDateDto
                        .builder()
                        .mediaId(mediaId)
                        .mediaStepId(savedOeOrderStep.getId())
                        .build();

                // calling the media service to save
                mediaService.update(mediaUpDateDto);


            }
            // ready for updating the query
        }

        return  orderStepMapper.toDto(savedOeOrderStep);
    }

    @Override
    public List<OrderStepResDto> findAllByOrderId(UUID orderId) {
        List<Order_Step> foundOrderStep = orderStepRepository.findOrder_StepsByOrder_Id(orderId);
        return  foundOrderStep.stream().map(orderStep -> orderStepMapper.toDto(orderStep)).toList();

    }

    @Override
    public Order_Step findById(UUID orderStepId) {
        return  orderStepRepository.findById(orderStepId).orElseThrow(() -> new ResourceNotFoundException("Order step with id [ %s ] not found ".formatted(orderStepId)));
    }
}
