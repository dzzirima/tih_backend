package blackmhofu.com.client_order.controller;


import blackmhofu.com.client_order.dto.ClientOrderReqDto;
import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.dto.ClientOrderUpdateReqDto;
import blackmhofu.com.client_order.dto.ClientOrderWhatsAppResDto;
import blackmhofu.com.client_order.model.ClientOrder;
import blackmhofu.com.client_order.service.ClientOrderServiceImpl;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.utils.api_response.ResponseHandler;
import blackmhofu.com.whatsapp.service.WhatsAppServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/client-order")
public class ClientOrderController {

    @Autowired
    private ClientOrderServiceImpl clientOrderService;



    @PostMapping
    public ResponseEntity<?> createClientOrder(@RequestBody ClientOrderReqDto clientOrderReqDto) {
        try {
            ClientOrderResDto clientOrderResDto = clientOrderService.save(clientOrderReqDto);


            // notify the client
            return ResponseHandler.generateResponse("ClientOrder successfully created  ", HttpStatus.CREATED, clientOrderResDto, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(

    ) {
        try {
            List<ClientOrderResDto> allOrders = clientOrderService.findAll();
            return ResponseHandler.generateResponse("ClientOrder found  ", HttpStatus.CREATED, allOrders, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }

    @GetMapping("byId/{orderId}")
    public ResponseEntity<?> findClientOrderById(
            @PathVariable UUID orderId
            ) {
        try {
            ClientOrderResDto clientOrderRes = clientOrderService.findById(orderId.toString());


            return ResponseHandler.generateResponse("Order found  ", HttpStatus.CREATED, clientOrderRes, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }

    @PatchMapping()
    public ResponseEntity<?> upDateClientOrder(
            @RequestBody ClientOrderUpdateReqDto clientOrderUpdateReqDto
            ) {
        try {
            String updateRes  = clientOrderService.upDate(clientOrderUpdateReqDto);
            return ResponseHandler.generateResponse(updateRes, HttpStatus.CREATED, null, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }

    @PatchMapping("/bulkUpdate")
    public ResponseEntity<?> BulkUpDateClientOrder(
            @RequestBody ClientOrderUpdateReqDto clientOrderUpdateReqDto
    ) {
        try {


            String updateRes  = clientOrderService.bulkUpDate(clientOrderUpdateReqDto);



            return ResponseHandler.generateResponse(updateRes, HttpStatus.CREATED, null, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }
    @GetMapping("/by-organisation-id/{organisationId}")
    public ResponseEntity<?> findClientOrderByOrganisationId(
            @PathVariable UUID organisationId
    ) {
        try {
            List<ClientOrderResDto> clientOrderRes = clientOrderService.findByOrganisationId(organisationId);
            return ResponseHandler.generateResponse("ClientOrders found  ", HttpStatus.CREATED, clientOrderRes, clientOrderRes.size(), true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }

}
