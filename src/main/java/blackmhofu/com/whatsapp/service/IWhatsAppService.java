package blackmhofu.com.whatsapp.service;

import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.dto.ClientOrderWhatsAppResDto;

import java.util.List;

public interface IWhatsAppService {

    ClientOrderResDto findByOrderNumber (String deliveryId);

    List<ClientOrderResDto> findByPhoneNumber(String phoneNumber);// we are finding all deliveries which belong to a client
    String sendWhatsAppMessage( ClientOrderWhatsAppResDto updatedOrder );

    void sendUpDateViaWhatsApp(String deliveryId);

}
