package blackmhofu.com.client_order.service;

import blackmhofu.com.client_order.dto.ClientOrderReqDto;
import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.dto.ClientOrderUpdateReqDto;
import blackmhofu.com.client_order.mapper.OrderMapper;
import blackmhofu.com.client_order.model.ClientOrder;
import blackmhofu.com.client_order.repository.ClientOrderRepository;
import blackmhofu.com.order_step.dto.OrderStepReqDto;
import blackmhofu.com.order_step.service.OrderStepServiceImpl;
import blackmhofu.com.organisation.model.Organisation;
import blackmhofu.com.organisation.service.OrganisationServiceImpl;
import blackmhofu.com.step.model.Step;
import blackmhofu.com.step.service.StepServiceImpl;
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

    @Autowired
    private StepServiceImpl stepService;


    @Autowired
    private OrderStepServiceImpl orderStepService;


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
    public String upDate(ClientOrderUpdateReqDto clientOrderUpdateReqDto) {


        ClientOrder foundClientOrder = findById(clientOrderUpdateReqDto.getOrderId());

        boolean changes = false;

        if(clientOrderUpdateReqDto.getGlobalStep() != null && clientOrderUpdateReqDto.getGlobalStep() != foundClientOrder.getGlobalStep()){
            foundClientOrder.setGlobalStep(clientOrderUpdateReqDto.getGlobalStep());
            changes = true;
        }

        if(clientOrderUpdateReqDto.getOrderPaymentStatus() != null && clientOrderUpdateReqDto.getOrderPaymentStatus() != foundClientOrder.getOrderPaymentStatus()){
            foundClientOrder.setOrderPaymentStatus(clientOrderUpdateReqDto.getOrderPaymentStatus());
            changes = true;
        }

        if(clientOrderUpdateReqDto.getPhoneNumber() != null && !clientOrderUpdateReqDto.getPhoneNumber().equals(foundClientOrder.getPhoneNumber())){
            foundClientOrder.setPhoneNumber(clientOrderUpdateReqDto.getPhoneNumber());
            changes = true;
        }
        if(clientOrderUpdateReqDto.getAddress() != null && !clientOrderUpdateReqDto.getAddress().equals(foundClientOrder.getAddress())){
            foundClientOrder.setAddress(clientOrderUpdateReqDto.getAddress());
            changes = true;
        }
        if(clientOrderUpdateReqDto.getDescription() != null && !clientOrderUpdateReqDto.getDescription().equals(foundClientOrder.getDescription())){
            foundClientOrder.setDescription(clientOrderUpdateReqDto.getDescription());
            changes = true;
        }


        //TODO  transaction after all checks are checked

        if(clientOrderUpdateReqDto.getCurrentStep() != null){
            foundClientOrder.setCurrentStep(clientOrderUpdateReqDto.getCurrentStep());

            // it means we want to move to new step

            Step foundStep = stepService.findByTemplateIdAndStepNumber(foundClientOrder.getStepTemplate().getId() , clientOrderUpdateReqDto.getCurrentStep());

            // create the order step specific to an order

            OrderStepReqDto orderStepReqDto = OrderStepReqDto
                    .builder()
                    .stepId(foundStep.getId())
                    .orderId(foundClientOrder.getId())
                    .attachedMediaIdsList(clientOrderUpdateReqDto.getAttachedMediaIdsList())
                    .build();

            orderStepService.save(orderStepReqDto);
            changes = true;
        }

        if(changes){
            ClientOrder clientOrderWithUpdates = clientOrderRepository.save(foundClientOrder);
            return  "Order with id [ %s ] was successfully updated".formatted(clientOrderWithUpdates.getId());
        }


        // updating when we change the template steps


        return  "No changes were made .";
    }

    @Override
    public String delete(UUID clientOrderId) {

        ClientOrder foundClientOrder = findById(clientOrderId);

        if(foundClientOrder!=null){
            clientOrderRepository.deleteById(clientOrderId);
            return "Client order with id [ %s ] was deleted successfully " .formatted(clientOrderId);
        }
        return "Error while deleting client order ";
    }

    @Override
    public List<ClientOrderResDto> findByClientId(UUID clientId) {

        List<ClientOrder > foundClientOrders = clientOrderRepository.findAll();
        return  foundClientOrders.stream().map(clientOrder -> orderMapper.toDto(clientOrder)).toList();
    }

    @Override
    public List<ClientOrderResDto> findByOrganisationId(UUID organisationId) {
        List<ClientOrder > foundClientOrders = clientOrderRepository.findClientOrderByOrganisationId(organisationId);
        return  foundClientOrders.stream().map(clientOrder -> orderMapper.toDto(clientOrder)).toList();
    }
}
