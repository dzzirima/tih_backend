package blackmhofu.com.client_order.service;

import blackmhofu.com.client_order.dto.ClientOrderReqDto;
import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.dto.ClientOrderUpdateReqDto;
import blackmhofu.com.client_order.model.ClientOrder;

import java.util.List;
import java.util.UUID;

public interface IClientOrderService {


    public ClientOrderResDto save (ClientOrderReqDto clientOrderReqDto);

    public  ClientOrder findById (UUID clientOrderId);

    public ClientOrderResDto findById( String clientOrderId);


    public String upDate ( ClientOrderUpdateReqDto clientOrderUpdateTeqDto);

    public  String delete(UUID clientOrderId);

    public List<ClientOrderResDto> findAll();
    public List<ClientOrderResDto> findByClientId(UUID clientId);


    public List<ClientOrderResDto> findByOrganisationId(UUID organisationId);

    public List<ClientOrderResDto> findByAgentId(UUID agentId);


    // bulk operations

    public String bulkUpDate ( ClientOrderUpdateReqDto clientOrderUpdateTeqDto);


}
