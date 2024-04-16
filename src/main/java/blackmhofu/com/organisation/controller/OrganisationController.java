package blackmhofu.com.organisation.controller;


import blackmhofu.com.organisation.dto.OrganisationReqDTO;
import blackmhofu.com.organisation.dto.OrganisationResDTO;
import blackmhofu.com.organisation.service.OrganisationServiceImpl;
import blackmhofu.com.utils.api_response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organisation")
@RequiredArgsConstructor
public class OrganisationController {

    @Autowired
    private OrganisationServiceImpl organisationService;


    @PostMapping
    public ResponseEntity<Object> createOrganisation(@RequestBody OrganisationReqDTO organisationReqDTO) {

        try {
            OrganisationResDTO savedOrganisation = organisationService.saveOrganisation(organisationReqDTO);
            return ResponseHandler.generateResponse(
                    "Organisation successfully saved ",
                    HttpStatus.CREATED,
                    savedOrganisation,
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


    @GetMapping("/{organisationId}")
    public ResponseEntity<Object> findOrganisationById(
            @PathVariable UUID organisationId
            ) {

        try {
            OrganisationResDTO foundOrganisation = organisationService.findById(organisationId.toString());
            return ResponseHandler.generateResponse(
                    "Organisation found",
                    HttpStatus.OK,
                    foundOrganisation,
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


    @DeleteMapping("/{organisationId}")
    public ResponseEntity<Object> deleteOrganisation(
            @PathVariable UUID organisationId
    ) {

        try {
            String  deleteRes  = organisationService.delete(organisationId);
            return ResponseHandler.generateResponse(
                    "Delete Operation ",
                    HttpStatus.OK,
                    deleteRes,
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


    @GetMapping()
    public ResponseEntity<Object> findAllOrganisations(

    ) {

        try {
            List<OrganisationResDTO> organisationResDTOS  = organisationService.findAll();
            return ResponseHandler.generateResponse(
                    "Found Organisations ",
                    HttpStatus.OK,
                    organisationResDTOS,
                    organisationResDTOS.size(),
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
