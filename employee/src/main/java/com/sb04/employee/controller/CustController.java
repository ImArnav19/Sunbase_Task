package com.sb04.employee.controller;


import com.sb04.employee.entity.Customer;
import com.sb04.employee.entity.Credentials;
import com.sb04.employee.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class CustController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public Customer post_cust(@RequestBody Customer c){
        return customerService.postCustomer(c);
    }

    @GetMapping("/customer")
    public List<Customer> get_all(){
        return customerService.getAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustById(@PathVariable String id){
        return customerService.getById(id);
    }
    @PutMapping("/customer/{id}")
    public Customer updateCust(@RequestBody Customer c,@PathVariable String id){
        return customerService.updateCust(c,id);
    }

    @DeleteMapping("/customer/{id}")
    String delete(@PathVariable String id){
        return customerService.delete(id);
    }

    @PostMapping("/token")
    String sync(@RequestBody Credentials c){
        return customerService.sync(c);
    }


}
