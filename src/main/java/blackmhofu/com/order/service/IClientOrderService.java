package blackmhofu.com.order.service;

import blackmhofu.com.order.dto.ClientOrderReqDto;
import blackmhofu.com.order.dto.ClientOrderResDto;
import blackmhofu.com.order.dto.ClientOrderUpdateReqDto;
import blackmhofu.com.order.model.ClientOrder;

import java.util.List;
import java.util.UUID;

public interface IClientOrderService {


    public ClientOrderResDto save (ClientOrderReqDto clientOrderReqDto);

    public  ClientOrder findById (UUID clientOrderId);

    public ClientOrderResDto findById( String clientOrderId);


    public String upDate ( ClientOrderUpdateReqDto clientOrderUpdateTeqDto);

    public  String delete(UUID clientOrderId);

    public List<ClientOrderResDto> findByClientId(UUID clientId);
    public List<ClientOrderResDto> findByOrganisationId(UUID organisationId);
}
