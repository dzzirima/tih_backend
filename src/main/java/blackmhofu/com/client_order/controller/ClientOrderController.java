package blackmhofu.com.client_order.controller;


import blackmhofu.com.client_order.dto.ClientOrderReqDto;
import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.service.ClientOrderServiceImpl;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.utils.api_response.ResponseHandler;
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
            return ResponseHandler.generateResponse("ClientOrder successfully created  ", HttpStatus.CREATED, clientOrderResDto, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }

    @GetMapping("/{clientOrderId}")
    public ResponseEntity<?> findClientOrderById(
            @PathVariable UUID clientOrderId
            ) {
        try {
            ClientOrderResDto clientOrderRes = clientOrderService.findById(clientOrderId.toString());
            return ResponseHandler.generateResponse("ClientOrder found  ", HttpStatus.CREATED, clientOrderRes, 1, true);
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
