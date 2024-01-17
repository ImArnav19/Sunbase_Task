package com.sb04.employee.controller;


import com.sb04.employee.entity.CustResponse;
import com.sb04.employee.entity.Customer;
import com.sb04.employee.entity.Credentials;
import com.sb04.employee.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustResponse> get_all(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                @RequestParam(value = "pageSize",defaultValue = "3",required = false) Integer pageSize,
                                                @RequestParam(value ="sortBy",defaultValue = "city",required = false) String sortBy,
                                                @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir
    ){


        CustResponse custresponse = customerService.getAll(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<CustResponse>(custresponse, HttpStatus.OK);
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

    @GetMapping("/search/state/{key}")
    public ResponseEntity<List<Customer>> search_s(@PathVariable("key") String key){
        List<Customer> result = customerService.searchState(key);
        return new ResponseEntity<List<Customer>>(result,HttpStatus.OK);
    }

    @GetMapping("/search/city/{key}")
    public ResponseEntity<List<Customer>> search_c(@PathVariable("key") String key){
        List<Customer> result = customerService.searchCity(key);

        return new ResponseEntity<List<Customer>>(result,HttpStatus.OK);
    }


}
