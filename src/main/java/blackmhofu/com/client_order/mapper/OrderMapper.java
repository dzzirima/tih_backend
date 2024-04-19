package blackmhofu.com.client_order.mapper;

import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.model.ClientOrder;
import blackmhofu.com.media.service.MediaServiceImpl;
import blackmhofu.com.order_step.dto.OrderStepResDto;
import blackmhofu.com.order_step.service.OrderStepServiceImpl;
import blackmhofu.com.step.dto.StepResDTO;
import blackmhofu.com.step.model.Step;
import blackmhofu.com.step.service.StepServiceImpl;
import blackmhofu.com.steptemplate.service.StepTemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {


    @Autowired
    private StepServiceImpl stepService;

    @Autowired
    private OrderStepServiceImpl orderStepService;




    public ClientOrderResDto toDto(ClientOrder clientOrder){

        List<StepResDTO> templateSteps = stepService.findAllByTemplateId(clientOrder.getStepTemplate().getId());
        List<OrderStepResDto> currentSteps = orderStepService.findAllByOrderId(clientOrder.getId());


        return  ClientOrderResDto
                .builder()
                .id(clientOrder.getId())
                .currentStep(clientOrder.getCurrentStep())
                .globalStep(clientOrder.getGlobalStep())
                .customerName(clientOrder.getCustomer().getName())
                .orderPaymentStatus(clientOrder.getOrderPaymentStatus())
                .organisation(clientOrder.getOrganisation().getName())
                .stepTemplateId(clientOrder.getStepTemplate().getId())
                .address(clientOrder.getAddress())
                .description(clientOrder.getDescription())
                .templateSteps(templateSteps)
                .currentSteps(currentSteps)
                .build();
    }
}
