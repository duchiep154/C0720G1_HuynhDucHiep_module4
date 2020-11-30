package com.example.controller;

import com.example.entity.Address;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Các bạn có thể tìm hiểu thêm  phần này tại blogs: https://levunguyen.com,
 * blogs chuyên về lập trình và các kỹ năng mềm trong nghề lập trình
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registUser")
    public void registUser() {
        Address address = new Address("Nguyen Tat Thanh", "Da Nang");

        User user = new User("Nguyen Van A", "09012345678");
        user.setAddress(address);

        this.userService.save(user);
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Long id) {
        return this.userService.findById(id);
    }
}
