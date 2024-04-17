package blackmhofu.com.order.service;

import blackmhofu.com.order.dto.ClientOrderReqDto;
import blackmhofu.com.order.dto.ClientOrderResDto;
import blackmhofu.com.order.dto.ClientOrderUpdateReqDto;
import blackmhofu.com.order.mapper.OrderMapper;
import blackmhofu.com.order.model.ClientOrder;
import blackmhofu.com.order.repository.ClientOrderRepository;
import blackmhofu.com.organisation.model.Organisation;
import blackmhofu.com.organisation.service.OrganisationServiceImpl;
import blackmhofu.com.steptemplate.model.StepTemplate;
import blackmhofu.com.steptemplate.service.StepTemplateServiceImpl;
import blackmhofu.com.users.model.User;
import blackmhofu.com.users.service.UserServiceImpl;
import blackmhofu.com.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientOrderServiceImpl implements  IClientOrderService{
    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrganisationServiceImpl organisationService;

    @Autowired
    private StepTemplateServiceImpl stepTemplateService;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public ClientOrderResDto save(ClientOrderReqDto clientOrderReqDto) {


        Organisation organisation = null;
        User customer = null;
        StepTemplate  stepTemplate= stepTemplateService.findById(clientOrderReqDto.getStepTemplateId().toString());

        if(clientOrderReqDto.getOrganisationId() != null){
            organisation = organisationService.findById(clientOrderReqDto.getOrganisationId());
        }


        if(clientOrderReqDto.getCustomerId() != null){
            customer =userService.findById(clientOrderReqDto.getCustomerId());
        }


        ClientOrder clientOrderToBeSaved = ClientOrder
                .builder()
                .description(clientOrderReqDto.getDescription())
                .address(clientOrderReqDto.getAddress())
                .currentStep(clientOrderReqDto.getCurrentStep())
                .globalStep(clientOrderReqDto.getGlobalStep())
                .orderPaymentStatus(clientOrderReqDto.getOrderPaymentStatus())
                .stepTemplate(stepTemplate)
                .customer(customer)
                .organisation(organisation)
                .build();
      ClientOrder saveClientOrder = clientOrderRepository.save(clientOrderToBeSaved);

      return  orderMapper.toDto(saveClientOrder);
    }

    @Override
    public ClientOrder findById(UUID clientOrderId) {

        ClientOrder foundClientOrder = clientOrderRepository.findById(clientOrderId).orElseThrow(() -> new ResourceNotFoundException("Client order with id [ %s ] not found".formatted(clientOrderId)));

        return foundClientOrder;
    }

    @Override
    public ClientOrderResDto findById(String clientOrderId) {

        ClientOrder foundClientOrder = findById(UUID.fromString( clientOrderId));
        return  orderMapper.toDto(foundClientOrder);
    }

    @Override
    public String upDate(ClientOrderUpdateReqDto clientOrderUpdateTeqDto) {
        return null;
    }

    @Override
    public String delete(UUID clientOrderId) {
        return null;
    }

    @Override
    public List<ClientOrderResDto> findByClientId(UUID clientId) {
        return null;
    }

    @Override
    public List<ClientOrderResDto> findByOrganisationId(UUID organisationId) {
        return null;
    }
}
