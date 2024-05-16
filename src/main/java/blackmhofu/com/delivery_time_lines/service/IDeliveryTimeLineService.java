package blackmhofu.com.delivery_time_lines.service;

import blackmhofu.com.delivery_time_lines.dto.DeliveryTimeLineReqDto;
import blackmhofu.com.delivery_time_lines.dto.DeliveryTimeLineResDto;

import java.util.List;
import java.util.UUID;

public interface IDeliveryTimeLineService {

    DeliveryTimeLineResDto saveDeliveryTimeLine(DeliveryTimeLineReqDto deliveryTimeLineReqDto);
    List<DeliveryTimeLineResDto> findDeliveryTimeLineById(UUID deliveryId);


}
