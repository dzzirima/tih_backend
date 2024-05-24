package blackmhofu.com.whatsapp.controller;


import blackmhofu.com.client_order.dto.ClientOrderReqDto;
import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.utils.api_response.ResponseHandler;
import blackmhofu.com.whatsapp.service.WhatsAppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/whatsapp")
public class WhatsAppController {


    @Autowired

    private WhatsAppServiceImpl whatsAppService;

    @GetMapping("byPhoneId/{phoneNumber}")
    public ResponseEntity<?> findAllByPhoneNumber(
            @PathVariable String phoneNumber

    ) {
        try {
            System.out.println("phoneNumber = " + phoneNumber);
            List<ClientOrderResDto> allDeliveries = whatsAppService.findByPhoneNumber(phoneNumber);
            return ResponseHandler.generateResponse("Deliveries found  ", HttpStatus.OK, allDeliveries, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }

    @GetMapping("byDeliveryId/{deliveryId}")
    public ResponseEntity<?> findByDeliveryId(
            @PathVariable String deliveryId

    ) {
        try {
            System.out.println("deliveryId = " + deliveryId);
            ClientOrderResDto foundDelivery = whatsAppService.findByOrderNumber(deliveryId);
            return ResponseHandler.generateResponse("Delivery  found  ", HttpStatus.OK, foundDelivery, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }







}
