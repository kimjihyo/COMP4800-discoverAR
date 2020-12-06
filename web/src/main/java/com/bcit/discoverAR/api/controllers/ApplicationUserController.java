package com.bcit.discoverAR.api.controllers;

import com.bcit.discoverAR.models.ApplicationUser;
import com.bcit.discoverAR.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository repository;

    @GetMapping("/findall")
    public List<ApplicationUser> findAll() {
        List<ApplicationUser> users = repository.findAll();
        return users;
    }

    @PostMapping("/create")
    public String create(@RequestBody ApplicationUser user){
        // save a single User
        repository.save(new ApplicationUser(user.getUsername(), user.getEmail(), user.getPassword()));
        return "User is created";
    }

    @RequestMapping("/search/{id}")
    public ApplicationUser search(@PathVariable long id) {
        Optional<ApplicationUser> customer = repository.findById(id);
        // TO-DO: handle null results
        return customer.get();
    }
}
