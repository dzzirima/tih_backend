package blackmhofu.com.client_order.mapper;

import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.model.ClientOrder;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {







    public ClientOrderResDto toDto(ClientOrder clientOrder){



        return  ClientOrderResDto
                .builder()
                .id(clientOrder.getId())
                .currentStep(clientOrder.getCurrentStep())
                .globalStep(clientOrder.getGlobalStep())
                .customerName(clientOrder.getCustomer()!=null ? clientOrder.getCustomer().getName() :"N/A")
                .orderPaymentStatus(clientOrder.getOrderPaymentStatus())

                .address(clientOrder.getAddress())

                .description(clientOrder.getDescription())
                .orderPlacedDate(clientOrder.getCreatedAt())

                .build();
    }
}
