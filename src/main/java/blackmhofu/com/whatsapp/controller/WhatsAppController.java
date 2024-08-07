package blackmhofu.com.whatsapp.controller;


import blackmhofu.com.client_order.dto.ClientOrderReqDto;
import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.utils.api_response.ResponseHandler;
import blackmhofu.com.whatsapp.service.WhatsAppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

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


    @PostMapping("send/{deliveryId}")
    public ResponseEntity<?> sendMessage(
            @PathVariable String deliveryId
    ) {
        try {
            System.out.println("deliveryId = " + deliveryId);

            String phone = "263785395827";
            String bear = "EAAOKDmORL5QBO3BsoYIjFvZCDeWStpZArslbgZCTAMkw02oXk4sG5AqDHbVHHfyRhkJ2OtH1ZB86P63q046Ri5wqo6SUBM9zhpSa9BYGAvdPb5ZAFXRyZAi0BkQ7ZAfqdEvxZAmJKdMqTjSj8wZBxYyCkz0YpZBgTRwhcFPkEKniDaj6Tl7XeRCP8Orz2elMBqBB8Kfd6Tszb0p4y8dgQS";
            String phoneNumberId = "356466357540686";


                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("https://graph.facebook.com/v13.0/"+phoneNumberId+"/messages"))
                        .header("Authorization", "Bearer "+bear)
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"263785395827\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"This is an example of a text message\" } }"))
//                        .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"<TARGET PHONE NUMBER>\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
                        .build();
                HttpClient http = HttpClient.newHttpClient();
                HttpResponse<String> response = http.send(request,BodyHandlers.ofString());
                System.out.println(response);


//            ClientOrderResDto foundDelivery = whatsAppService.findByOrderNumber(deliveryId);
            return ResponseHandler.generateResponse("Delivery  found  ", HttpStatus.OK, deliveryId, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 0, true);
        }
    }








}
