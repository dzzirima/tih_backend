package blackmhofu.com.order.mapper;

import blackmhofu.com.order.dto.ClientOrderResDto;
import blackmhofu.com.order.model.ClientOrder;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {



    public ClientOrderResDto toDto(ClientOrder clientOrder){

        return  ClientOrderResDto
                .builder()
                .currentStep(clientOrder.getCurrentStep())
                .globalStep(clientOrder.getGlobalStep())
                .customerName(clientOrder.getCustomer().getName())
                .organisation(clientOrder.getOrganisation().getName())
                .stepTemplateId(clientOrder.getStepTemplate().getId())
                .address(clientOrder.getAddress())
                .description(clientOrder.getDescription())
                .build();
    }
}
