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

import java.net.URI;
import java.net.http.HttpRequest;


@Service
public class WhatsAppServiceImpl {

    private static final String TOKEN = "EAAPTrljvzD8BO3yqZArZBEBBvXEF8suvTUQzhNRjF64frOuOAYcebU5WZA0uhHSheFZBeY4F4tunG58tCrtIXRGH59lSxPi6FzIPaE4tzZBux4aQPT9vcilw3BUZBdq1ejLiQSK7sySOBbONo4nzBAVBNrKrlL63m67dJHaZBsRylFRnVyQSdLxApbboB6XT0k3x9qnjtZAy1y9r6556fqIZD";
    private static  final  String phoneNumber = "263785395827";
    private static final String PHONE_NUMBER_ID = "315089391668255";


    public  String sendWhatsAppMessage() {


        try {

//            System.out.println("doing whatsapp ");
//            WhatsappApiConfig.setApiVersion(ApiVersion.V19_0);
//            WhatsappApiFactory factory = WhatsappApiFactory.newInstance("EAAPTrljvzD8BO4xhg81Je2PXtUumQkDvjWJbedLIVuKO4OY4qK69QhdUkM7IcmB68g5XGzDn9ZCOGwTu3DVl5Kkz0VpOrcnJZBHn4VtbFGRdPBaG9Xcq7tZAvBwz6fv9ZA0Fg5nuxDVAhLc2aydsRfo8kZCu1hFPbRBdihlG7aYOCpGp8K17uV5jjtizZBdZAJe3Jn5toWWzhYigLtBZByMZD");
//
//            WhatsappBusinessCloudApi whatsappBusinessCloudApi = factory.newBusinessCloudApi();
//            var message = Message.MessageBuilder.builder()//
//                    .setTo("263785395827")//
//                    .buildTextMessage(new TextMessage()//
//                            .setBody(Formatter.bold("Hello world!") + "\nSome code here: \n" + Formatter.code("hello world code here"))//
//                            .setPreviewUrl(false));
//
//
//            MessageResponse messageResponse = whatsappBusinessCloudApi.sendMessage("315089391668255", message);
//
//            System.out.println(messageResponse);


            // Doing it manually

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v19.0/315089391668255/messages"))
                    .GET()
                    .build();


        }catch (Exception e){

            System.out.println("e = " + e.getMessage());

            return  null;

        }


        return "whatsapp";
    }
}
