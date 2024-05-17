package blackmhofu.com.client_order.service;

import blackmhofu.com.client_order.dto.ClientOrderReqDto;
import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.dto.ClientOrderUpdateReqDto;
import blackmhofu.com.client_order.mapper.OrderMapper;
import blackmhofu.com.client_order.model.ClientOrder;
import blackmhofu.com.client_order.repository.ClientOrderRepository;
import blackmhofu.com.client_order.type.GlobalStep;
import blackmhofu.com.client_order.type.OrderPaymentStatus;
import blackmhofu.com.delivery_time_lines.dto.DeliveryTimeLineReqDto;
import blackmhofu.com.delivery_time_lines.dto.DeliveryTimeLineResDto;
import blackmhofu.com.delivery_time_lines.service.DeliveryTimeLineServiceImpl;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.users.model.User;
import blackmhofu.com.users.service.UserServiceImpl;
import blackmhofu.com.users.type.UserRole;
import blackmhofu.com.utils.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientOrderServiceImpl implements  IClientOrderService{
    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DeliveryTimeLineServiceImpl deliveryTimeLineService;




    @Override
    public ClientOrderResDto save(ClientOrderReqDto clientOrderReqDto) {



        User customer = null;



        // for client first check to see if that client exists

        Optional<User> customerInDb = userService.findByName(clientOrderReqDto.getClientName());

        if(customerInDb.isEmpty()){

            UserReqDTO reqToBeAdded = UserReqDTO
                    .builder()
                    .name(clientOrderReqDto.getClientName())
                    .phoneNumber(clientOrderReqDto.getPhoneNumber())
                    .role(UserRole.CUSTOMER)
                    .address(clientOrderReqDto.getAddress())
                    .email(clientOrderReqDto.getClientName()+"@gmail.com")
                    .password("test123")
                    .build();
            UserResDTO userResDTO = userService.saveUser(reqToBeAdded);

            customer = userService.findById(userResDTO.getId());
        }else {
            customer = customerInDb.get();
        }
        ClientOrder clientOrderToBeSaved = ClientOrder
                .builder()
                .description(clientOrderReqDto.getDescription())
                .address(clientOrderReqDto.getAddress())
                .currentStep(clientOrderReqDto.getCurrentStep())
                .globalStep(GlobalStep.PENDING)
                .orderPaymentStatus(OrderPaymentStatus.PENDING)

                .customer(customer)

                .build();
      ClientOrder saveClientOrder = clientOrderRepository.save(clientOrderToBeSaved);


      // create a timeline for   the delivery

        DeliveryTimeLineReqDto deliveryTimeLineReqDto = DeliveryTimeLineReqDto
                .builder()
                .clientOrderId(saveClientOrder.getId())
                .deliveryUpDates("Your order starts processing")
                .globalStep(GlobalStep.PENDING)
                .orderPaymentStatus(OrderPaymentStatus.PENDING)
                .build();

        DeliveryTimeLineResDto deliveryTimeLineResDto = deliveryTimeLineService.saveDeliveryTimeLine(deliveryTimeLineReqDto);

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

       ArrayList<DeliveryTimeLineReqDto> deliveryTimeLineReqDtoList = new ArrayList<>();


        boolean changes = false;

        if(clientOrderUpdateReqDto.getGlobalStep() != null && clientOrderUpdateReqDto.getGlobalStep() != foundClientOrder.getGlobalStep()){
            foundClientOrder.setGlobalStep(clientOrderUpdateReqDto.getGlobalStep());


            deliveryTimeLineReqDtoList.add(DeliveryTimeLineReqDto
                    .builder()
                    .clientOrderId(foundClientOrder.getId())
                    .deliveryUpDates("Order : " + clientOrderUpdateReqDto.getGlobalStep())
                    .build());
            changes = true;
        }

        if(clientOrderUpdateReqDto.getOrderPaymentStatus() != null && clientOrderUpdateReqDto.getOrderPaymentStatus() != foundClientOrder.getOrderPaymentStatus()){
            foundClientOrder.setOrderPaymentStatus(clientOrderUpdateReqDto.getOrderPaymentStatus());

            deliveryTimeLineReqDtoList.add(DeliveryTimeLineReqDto
                    .builder()
                    .clientOrderId(foundClientOrder.getId())
                    .deliveryUpDates("Order : " + clientOrderUpdateReqDto.getOrderPaymentStatus())
                    .build());
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

        if(clientOrderUpdateReqDto.getDeliveryUpDates() != null){
            changes = true ;
        }

        // those times when  we change state only





        if(changes){
            ClientOrder clientOrderWithUpdates = clientOrderRepository.save(foundClientOrder);




            if(!clientOrderUpdateReqDto.getDeliveryUpDates().isBlank()) {
                // perfect time for creating a timeline
                DeliveryTimeLineReqDto deliveryTimeLineReqDto = DeliveryTimeLineReqDto
                        .builder()
                        .clientOrderId(foundClientOrder.getId())
                        .deliveryUpDates(clientOrderUpdateReqDto.getDeliveryUpDates())
                        .globalStep(clientOrderUpdateReqDto.getGlobalStep())
                        .orderPaymentStatus(clientOrderUpdateReqDto.getOrderPaymentStatus())
                        .build();

                DeliveryTimeLineResDto deliveryTimeLineResDto = deliveryTimeLineService.saveDeliveryTimeLine(deliveryTimeLineReqDto);
            }



            // if the other timelines are not empty

            if(!deliveryTimeLineReqDtoList.isEmpty()){
                for (DeliveryTimeLineReqDto timeLineReqDto : deliveryTimeLineReqDtoList) {

                    deliveryTimeLineService.saveDeliveryTimeLine(timeLineReqDto);
                }

            }


            return  "Order with id [ %s ] was successfully updated".formatted(clientOrderWithUpdates.getId());

            
        }


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
    public List<ClientOrderResDto> findAll() {

        List<ClientOrder > foundClientOrders = clientOrderRepository.findAll();
        return  foundClientOrders.stream().map(clientOrder -> orderMapper.toDto(clientOrder)).toList();
    }
    @Override
    public List<ClientOrderResDto> findByClientId(UUID clientId) {

        List<ClientOrder > foundClientOrders = clientOrderRepository.findAll();
        return  foundClientOrders.stream().map(clientOrder -> orderMapper.toDto(clientOrder)).toList();
    }

    @Override
    public List<ClientOrderResDto> findByOrganisationId(UUID organisationId) {
       return null;
    }

    @Override
    public String bulkUpDate(ClientOrderUpdateReqDto clientOrderUpdateTeqDto) {


        try{
            String rawBulkOrderIds = clientOrderUpdateTeqDto.getBulkUpdateOrderIds();

            ObjectMapper mapper = new ObjectMapper();
            String[] arrayOfStringIds = mapper.readValue(rawBulkOrderIds, String[].class);

            if(arrayOfStringIds.length == 0){
                return " No changes were made";
            }

            for (String stringOrderId : arrayOfStringIds) {

                clientOrderUpdateTeqDto.setOrderId(UUID.fromString(stringOrderId)); // just set the id and reuse the logic already implemented

                upDate(clientOrderUpdateTeqDto);
            }

            return "Updates we success ";


        }catch (Exception e) {

            System.out.println("Error while parsing bulkOrderIds = " + e);
            return  e.getMessage();
        }


    }
}
