package blackmhofu.com.whatsapp.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/whatsapp")
public class WhatsAppController {

    @PostMapping
    public  String CallBack(@RequestBody Object req ){

        return  "200";
    }
}
