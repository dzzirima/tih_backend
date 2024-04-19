package blackmhofu.com.steptemplate.controller;


import blackmhofu.com.steptemplate.dto.StepTemplateReqDTO;
import blackmhofu.com.steptemplate.dto.StepTemplateResDTO;
import blackmhofu.com.steptemplate.model.StepTemplate;
import blackmhofu.com.steptemplate.service.StepTemplateServiceImpl;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.utils.api_response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/step-template")
public class StepTemplateController {

    @Autowired
    private StepTemplateServiceImpl stepTemplateService;


    @PostMapping
    public ResponseEntity<?> createStepTemplate(@RequestBody StepTemplateReqDTO stepTemplateReqDTO) {

        try {
            StepTemplateResDTO stepTemplateResDTO = stepTemplateService.saveStepTemplate(stepTemplateReqDTO);
            return ResponseHandler.generateResponse("Step Template  successfully created  ", HttpStatus.CREATED, stepTemplateResDTO, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);

        }

    }


    @GetMapping
    public ResponseEntity<?> createStepTemplate() {

        try {
            List<StepTemplateResDTO> stepTemplateResDTO = stepTemplateService.findAll();
            return ResponseHandler.generateResponse("Step Templates found  ", HttpStatus.CREATED, stepTemplateResDTO, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);

        }

    }


    @GetMapping("by-organisation-id/{organisationId}")
    public ResponseEntity<?> findByOrganisationId(
            @PathVariable UUID organisationId
            ) {

        try {
            List<StepTemplateResDTO> stepTemplateResDTO = stepTemplateService.findAllByOrganisationId(organisationId);
            return ResponseHandler.generateResponse("Step Templates found  ", HttpStatus.CREATED, stepTemplateResDTO, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);

        }

    }




}
