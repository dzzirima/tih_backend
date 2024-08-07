package blackmhofu.com.config;

import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.service.ClientOrderServiceImpl;
import com.whatsapp.api.WhatsappApiFactory;
import com.whatsapp.api.domain.messages.Message;
import com.whatsapp.api.domain.messages.TextMessage;
import com.whatsapp.api.impl.WhatsappBusinessCloudApi;
import com.whatsapp.api.utils.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WhatsAppConfig {

  private  final String token = "EAAOKDmORL5QBO3BsoYIjFvZCDeWStpZArslbgZCTAMkw02oXk4sG5AqDHbVHHfyRhkJ2OtH1ZB86P63q046Ri5wqo6SUBM9zhpSa9BYGAvdPb5ZAFXRyZAi0BkQ7ZAfqdEvxZAmJKdMqTjSj8wZBxYyCkz0YpZBgTRwhcFPkEKniDaj6Tl7XeRCP8Orz2elMBqBB8Kfd6Tszb0p4y8dgQS";

   private  final String PHONE_NUMBER_ID = "356466357540686";
   @Autowired
   private ClientOrderServiceImpl clientOrderService;

   public void  sendWhatsAppUpDate(String deliveryId){
       try {

           ClientOrderResDto foundOrder = clientOrderService.findByOrderNumber(deliveryId);
//            String PHONE_NUMBER_1 = "263785395827";
            String PHONE_NUMBER_1 = foundOrder.getPhoneNumber();

           String newLine = System.getProperty("line.separator");

           String updateMessage  = new  StringBuilder()
                   .append("************ Order Updates *******")
                   .append(newLine)
                   .append("Order Number : %s".formatted(foundOrder.getTrackingNumber()))
                   .append(newLine)
                   .append("Status : %s".formatted(foundOrder.getGlobalStep()) )
                   .append(newLine)
                   .append("Payment Status : %s".formatted(foundOrder.getOrderPaymentStatus()))
                   .append(newLine)
                   .append("Description : %s".formatted(foundOrder.getDescription()))
                   .append(newLine)
                   .append("** Thank you for shopping with us  **")
                   .toString();
           WhatsappApiFactory factory = WhatsappApiFactory.newInstance(token);

           WhatsappBusinessCloudApi whatsappBusinessCloudApi = factory.newBusinessCloudApi();

           var message = Message.MessageBuilder.builder()//
                   .setTo(PHONE_NUMBER_1)//
                   .buildTextMessage(new TextMessage()//
                           .setBody(updateMessage)//
                           .setPreviewUrl(false));
           whatsappBusinessCloudApi.sendMessage(PHONE_NUMBER_ID, message);
       }catch (Exception e) {
           System.out.println("****** Error while sending an update to a client  = " + e);

       }
   }




}
