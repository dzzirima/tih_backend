package blackmhofu.com.client_order.controller;


import blackmhofu.com.client_order.dto.ClientOrderReqDto;
import blackmhofu.com.client_order.dto.ClientOrderResDto;
import blackmhofu.com.client_order.service.ClientOrderServiceImpl;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.utils.api_response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client-order")
public class ClientOrderController {


    @Autowired
    private ClientOrderServiceImpl clientOrderService;
    @PostMapping
    public ResponseEntity<?> createClientOrder(@RequestBody ClientOrderReqDto clientOrderReqDto) {

        try {
            ClientOrderResDto clientOrderResDto = clientOrderService.save(clientOrderReqDto);
            return ResponseHandler.generateResponse("ClientOrder successfully saved ", HttpStatus.CREATED, clientOrderResDto, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);
        }
    }





}
