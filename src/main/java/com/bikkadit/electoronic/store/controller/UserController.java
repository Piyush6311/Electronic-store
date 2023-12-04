package com.bikkadit.electoronic.store.controller;


import com.bikkadit.electoronic.store.payload.ApiResponse;
import com.bikkadit.electoronic.store.payload.PageableResponse;
import com.bikkadit.electoronic.store.payload.UserDto;
import com.bikkadit.electoronic.store.service.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceI userServiceI;

    @PostMapping("/")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto user) {
        log.info("Enter the  request for Save the User : {}", user);
        UserDto user1 = this.userServiceI.createUser(user);
        log.info("Completed the request for Save the User : {}",user);
        return new ResponseEntity<UserDto>(user1, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<PageableResponse> getAllUsers(

            @RequestParam (value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "2",required = false) Integer pageSize,
            @RequestParam (value = "sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "direction",defaultValue = "asc",required = false) String direction)

    {
        log.info("Enter the  request for get all users  ");
        PageableResponse allUsers = this.userServiceI.getAllUsers(pageNumber, pageSize, sortBy, direction);
        log.info("Completed the request for get all users  ");
        return new ResponseEntity<PageableResponse>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable String userId) {
        log.info("Enter the  request for get the user with userId :{} ",userId);
        UserDto singleUser = this.userServiceI.getSingleUser(userId);
        log.info("Completed the  request for get the user with userId :{} ",userId);
        return new ResponseEntity<UserDto>(singleUser, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        log.info("Enter the  request for get the user with EmailId :{} ",email);
        UserDto userByEmailId = this.userServiceI.getUserByEmailId(email);
        log.info("Completed the  request for get the user with EmailId :{} ",email);
        return new ResponseEntity<UserDto>(userByEmailId, HttpStatus.OK);
    }

    @GetMapping("/email/{email}/pass/{password}")
    public ResponseEntity<UserDto> getUserByEmailAndPass(@PathVariable String email, @PathVariable String password) {

        log.info("Enter the  request for get the user with EmailId And Password :{} :{} ",email,password);
        UserDto userByEmailAndPassword = this.userServiceI.getUserByEmailAndPassword(email, password);
        log.info("Completed the  request for get the user with EmailId And Password :{} :{} ",email,password);
        return  new ResponseEntity<UserDto>(userByEmailAndPassword,HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> getUserContaing(@PathVariable String keyword) {

        log.info("Enter the  request for get the user containing :{} ",keyword);
        List<UserDto> userDtos = this.userServiceI.searchUser(keyword);
        log.info("Completed the  request for get the user containing :{} ",keyword);

        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }




    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userid) {
        log.info("Enter the  request for Delete the user with UserId :{} ",userid);
        this.userServiceI.deleteUser(userid);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("User Deleted Successfully :"+userid);
        apiResponse.setStatus(true);
        apiResponse.setStatusCode(HttpStatus.OK);
        log.info("Completed  the  request for Delete the user with UserId :{} ",userid);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("/{str}")
    public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto, @PathVariable String str){
        log.info("Enter the  request for update  the user with UserId :{} ",str);
        UserDto userDto1 = this.userServiceI.updateUser(userDto,str);
        log.info("Completed the  request for update  the user with UserId :{} ",str);
        return  new ResponseEntity<UserDto>(userDto1,HttpStatus.OK);
    }
}
