package blackmhofu.com.client_order.mapper;

import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.model.ClientOrder;
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
