package blackmhofu.com.order_step.controller;

import blackmhofu.com.order_step.dto.OrderStepReqDto;
import blackmhofu.com.order_step.dto.OrderStepResDto;
import blackmhofu.com.order_step.service.OrderStepServiceImpl;
import blackmhofu.com.organisation.dto.OrganisationReqDTO;
import blackmhofu.com.organisation.dto.OrganisationResDTO;
import blackmhofu.com.utils.api_response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order-step")
public class OrderStepController {

    @Autowired
    private OrderStepServiceImpl orderStepService;
    @PostMapping
    public ResponseEntity<Object> createOrderStep(@RequestBody OrderStepReqDto orderStepReqDto) {

        System.out.println("orderStepReqDto = " + orderStepReqDto);

        try {
            OrderStepResDto savedOrderStepResDto = orderStepService.save(orderStepReqDto);
            return ResponseHandler.generateResponse(
                    " OrderStep  successfully saved ",
                    HttpStatus.CREATED,
                    savedOrderStepResDto,
                    1,
                    true
            );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    1,
                    true
            );

        }

    }
}
