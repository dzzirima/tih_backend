package blackmhofu.com.delivery_time_lines.service;

import blackmhofu.com.client_order.model.ClientOrder;
import blackmhofu.com.client_order.service.ClientOrderServiceImpl;
import blackmhofu.com.delivery_time_lines.dto.DeliveryTimeLineReqDto;
import blackmhofu.com.delivery_time_lines.dto.DeliveryTimeLineResDto;
import blackmhofu.com.delivery_time_lines.mapper.DeliveryTimeLineMapper;
import blackmhofu.com.delivery_time_lines.model.DeliveryTimeLine;
import blackmhofu.com.delivery_time_lines.repository.DeliveryTimeLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeliveryTimeLineServiceImpl implements  IDeliveryTimeLineService{

    @Autowired
    private ClientOrderServiceImpl clientOrderService;

    @Autowired
    private DeliveryTimeLineRepository deliveryTimeLineRepository;

    @Autowired
    private DeliveryTimeLineMapper deliveryTimeLineMapper;

    @Override
    public DeliveryTimeLineResDto saveDeliveryTimeLine(DeliveryTimeLineReqDto deliveryTimeLineReqDto) {

        ClientOrder orderFound = clientOrderService.findById(deliveryTimeLineReqDto.getClientOrderId());


        DeliveryTimeLine deliveryTimeLine = DeliveryTimeLine
                .builder()
                .clientOrder(orderFound)
                .description(deliveryTimeLineReqDto.getDeliveryUpDates())
                .orderPaymentStatus(deliveryTimeLineReqDto.getOrderPaymentStatus())
                .globalStep(deliveryTimeLineReqDto.getGlobalStep())
                .build();

        DeliveryTimeLine savedDeliveryTimeLIne = deliveryTimeLineRepository.save(deliveryTimeLine);

        return  deliveryTimeLineMapper.todo(savedDeliveryTimeLIne);
    }

    @Override
    public List<DeliveryTimeLineResDto> findDeliveryTimeLineById(UUID deliveryId) {

        List<DeliveryTimeLine> deliveryTimeLines = deliveryTimeLineRepository.findDeliveryTimeLinesByClientOrder_Id(deliveryId);

        return  deliveryTimeLines.stream().sorted(Comparator.comparing(DeliveryTimeLine::getCreatedAt)).map(deliveryTimeLine -> deliveryTimeLineMapper.todo(deliveryTimeLine)).collect(Collectors.toList());

    }
}
