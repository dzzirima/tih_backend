package blackmhofu.com.whatsapp.controller;

import com.whatsapp.api.WhatsappApiFactory;
import com.whatsapp.api.domain.messages.Message;
import com.whatsapp.api.domain.messages.TextMessage;
import com.whatsapp.api.impl.WhatsappBusinessCloudApi;
import com.whatsapp.api.utils.Formatter;

public class TestController {
    public static void main(String[] args) {

        String token = "EAAOKDmORL5QBO3BsoYIjFvZCDeWStpZArslbgZCTAMkw02oXk4sG5AqDHbVHHfyRhkJ2OtH1ZB86P63q046Ri5wqo6SUBM9zhpSa9BYGAvdPb5ZAFXRyZAi0BkQ7ZAfqdEvxZAmJKdMqTjSj8wZBxYyCkz0YpZBgTRwhcFPkEKniDaj6Tl7XeRCP8Orz2elMBqBB8Kfd6Tszb0p4y8dgQS";
        String PHONE_NUMBER_1 = "263785395827";
        String PHONE_NUMBER_ID = "356466357540686";

        WhatsappApiFactory factory = WhatsappApiFactory.newInstance(token);

        WhatsappBusinessCloudApi whatsappBusinessCloudApi = factory.newBusinessCloudApi();

        var message = Message.MessageBuilder.builder()//
                .setTo(PHONE_NUMBER_1)//
                .buildTextMessage(new TextMessage()//
                        .setBody(Formatter.bold("Hello world!") + "\nSome code here: \n" + Formatter.bold("hello world code here"))//
                        .setPreviewUrl(false));


        whatsappBusinessCloudApi.sendMessage(PHONE_NUMBER_ID, message);

    }
}
