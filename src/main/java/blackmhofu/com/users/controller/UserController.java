package blackmhofu.com.users.controller;
import blackmhofu.com.users.dto.UserReqDTO;
import blackmhofu.com.users.dto.UserResDTO;
import blackmhofu.com.users.service.UserServiceImpl;
import blackmhofu.com.utils.api_response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    private UserServiceImpl userService;


    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserReqDTO userReqDTO) {

        try {
            UserResDTO savedUser = userService.saveUser(userReqDTO);
            return ResponseHandler.generateResponse("User successfully saved ", HttpStatus.CREATED, savedUser, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);

        }

    }


    @GetMapping("/{userId}")
    public ResponseEntity<Object> findUserById(@PathVariable UUID userId) {

        try {
            UserResDTO foundUser = userService.findById(userId.toString());
            return ResponseHandler.generateResponse("User found", HttpStatus.OK, foundUser, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);

        }
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID userId) {

        try {
            String deleteRes = userService.delete(userId);
            return ResponseHandler.generateResponse("Delete Operation ", HttpStatus.OK, deleteRes, 1, true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);

        }
    }

    @GetMapping()
    public ResponseEntity<Object> findAllUsers(

    ) {

        try {
            List<UserResDTO> userResDTOList = userService.findAll();
            return ResponseHandler.generateResponse("Found Users", HttpStatus.OK, userResDTOList, userResDTOList.size(), true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, 1, true);

        }
    }


}
