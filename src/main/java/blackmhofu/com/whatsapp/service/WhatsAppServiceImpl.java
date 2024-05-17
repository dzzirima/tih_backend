package blackmhofu.com.whatsapp.service;

import com.whatsapp.api.WhatsappApiFactory;
import com.whatsapp.api.configuration.ApiVersion;
import com.whatsapp.api.configuration.WhatsappApiConfig;
import com.whatsapp.api.domain.messages.Message;
import com.whatsapp.api.domain.messages.TextMessage;
import com.whatsapp.api.domain.messages.response.MessageResponse;
import com.whatsapp.api.impl.WhatsappBusinessCloudApi;
import com.whatsapp.api.utils.Formatter;
import org.springframework.stereotype.Service;


@Service
public class WhatsAppServiceImpl {

    private static final String TOKEN = "EAAPTrljvzD8BO3yqZArZBEBBvXEF8suvTUQzhNRjF64frOuOAYcebU5WZA0uhHSheFZBeY4F4tunG58tCrtIXRGH59lSxPi6FzIPaE4tzZBux4aQPT9vcilw3BUZBdq1ejLiQSK7sySOBbONo4nzBAVBNrKrlL63m67dJHaZBsRylFRnVyQSdLxApbboB6XT0k3x9qnjtZAy1y9r6556fqIZD";
    private static  final  String phoneNumber = "263785395827";
    private static final String PHONE_NUMBER_ID = "315089391668255";


    public  String sendWhatsAppMessage() {


        try {

            System.out.println("doing whatsapp ");
            WhatsappApiConfig.setApiVersion(ApiVersion.V18_0);
            WhatsappApiFactory factory = WhatsappApiFactory.newInstance("EAAPTrljvzD8BO3yqZArZBEBBvXEF8suvTUQzhNRjF64frOuOAYcebU5WZA0uhHSheFZBeY4F4tunG58tCrtIXRGH59lSxPi6FzIPaE4tzZBux4aQPT9vcilw3BUZBdq1ejLiQSK7sySOBbONo4nzBAVBNrKrlL63m67dJHaZBsRylFRnVyQSdLxApbboB6XT0k3x9qnjtZAy1y9r6556fqIZD");

            WhatsappBusinessCloudApi whatsappBusinessCloudApi = factory.newBusinessCloudApi();
            var message = Message.MessageBuilder.builder()//
                    .setTo("263785395827")//
                    .buildTextMessage(new TextMessage()//
                            .setBody(Formatter.bold("Hello world!") + "\nSome code here: \n" + Formatter.code("hello world code here"))//
                            .setPreviewUrl(false));


            MessageResponse messageResponse = whatsappBusinessCloudApi.sendMessage("315089391668255", message);

            System.out.println(messageResponse);


        }catch (Exception e){

            System.out.println("e = " + e.getMessage());

            return  null;

        }


        return "whatsapp";
    }
}
