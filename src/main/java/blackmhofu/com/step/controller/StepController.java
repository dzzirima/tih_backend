package blackmhofu.com.step.controller;


import blackmhofu.com.step.dto.StepReqDTO;
import blackmhofu.com.step.dto.StepResDTO;
import blackmhofu.com.step.service.StepServiceImpl;
import blackmhofu.com.steptemplate.dto.StepTemplateReqDTO;
import blackmhofu.com.steptemplate.dto.StepTemplateResDTO;
import blackmhofu.com.utils.api_response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/step")
public class StepController {


    @Autowired
    private StepServiceImpl stepService;

    @PostMapping
    public ResponseEntity<?> createStep(@RequestBody StepReqDTO stepReqDTO) {

        try {
            StepResDTO stepResDTO= stepService.save(stepReqDTO);
            return ResponseHandler.generateResponse("Step   successfully created  ", HttpStatus.CREATED, stepResDTO, 1, true);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);

        }

    }


    @GetMapping("by-template-id/{templateId}")
    public ResponseEntity<?> getStepByTemplateId(
            @PathVariable UUID templateId
            ) {

        try {
           List<StepResDTO> stepReqDTOs = stepService.findAllByTemplateId(templateId);
            return ResponseHandler.generateResponse("Found Steps", HttpStatus.OK, stepReqDTOs, 1, true);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);

        }

    }


}
