package blackmhofu.com.whatsapp.controller;


import blackmhofu.com.whatsapp.service.WhatsAppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/whatsapp")
public class WhatsAppController {


    @Autowired
    private WhatsAppServiceImpl whatsAppService;


    @PostMapping
    public  String CallBack(){

        return whatsAppService.sendWhatsAppMessage();


    }
}
