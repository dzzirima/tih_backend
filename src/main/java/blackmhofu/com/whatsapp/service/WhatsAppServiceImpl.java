package blackmhofu.com.whatsapp.service;

import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.dto.ClientOrderWhatsAppResDto;
import blackmhofu.com.client_order.model.ClientOrder;
import blackmhofu.com.client_order.service.ClientOrderServiceImpl;
import blackmhofu.com.config.WhatsAppConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class WhatsAppServiceImpl implements  IWhatsAppService{

     private  final String WHATSAPP_URL = "http://localhost:4000/messages/send"  ;


    @Autowired
    ClientOrderServiceImpl clientOrderService ;


    @Autowired
    private WhatsAppConfig whatsAppConfig;
    @Override
    public ClientOrderResDto findByOrderNumber(String deliveryId) {
        return  clientOrderService.findByOrderNumber(deliveryId);
    }

    @Override
    public List<ClientOrderResDto> findByPhoneNumber(String phoneNumber) {
        return  clientOrderService.findByPhoneNumber(phoneNumber);
    }

    @Override
    public String sendWhatsAppMessage(ClientOrderWhatsAppResDto updatedOrder) {


        try{

            whatsAppConfig.sendWhatsAppUpDate("DGdeb8acfc");


//            // Convert object to JSON string using a library like Jackson
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonString = mapper.writeValueAsString(updatedOrder);
//
//
//                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(URI.create(WHATSAPP_URL))
//                        .POST(HttpRequest.BodyPublishers.ofString(jsonString, StandardCharsets.UTF_8))
//                        .header("Content-Type", "application/json")
//                        .build();
//
//            CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder()
//                    .build()
//                    .sendAsync(request, HttpResponse.BodyHandlers.ofString());
//
//
//            System.out.println("Status Code: " + response.get().statusCode());
//            System.out.println("Response Body: " + response.get().body());

        } catch (Exception e){

            System.out.println("Error while sending an update to client = " + e.getMessage());

        }


        return null;
    }

    @Override
    public void sendUpDateViaWhatsApp(String deliveryId) {

//        ClientOrderResDto foundOrder = findByOrderNumber(deliveryId);

        whatsAppConfig.sendWhatsAppUpDate("DGdeb8acfc");

    }
}
