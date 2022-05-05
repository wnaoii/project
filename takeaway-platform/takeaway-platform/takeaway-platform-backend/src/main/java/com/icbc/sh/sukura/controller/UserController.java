package com.icbc.sh.sukura.controller;


import com.icbc.sh.sukura.bo.AddressBo;
import com.icbc.sh.sukura.bo.User;
import com.icbc.sh.sukura.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserInfo (
            @PathVariable("id") String userId
    ) {
        return userService.getUserInfo(userId);
    }

    @PostMapping("/address")
    public void addAddress(
            @RequestBody AddressBo addressBo
            ){
        userService.addAddress(addressBo);
    }

}
