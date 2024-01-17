package com.sb04.employee.service;

import com.sb04.employee.entity.*;

import com.sb04.employee.exception.UserNotFoundException;
import com.sb04.employee.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepo customerRepo;

    public Customer postCustomer(Customer cust) {
        return customerRepo.save(cust);
    }

    public CustResponse getAll(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber,pageSize, sort);
        Page<Customer> pagePost = this.customerRepo.findAll(p);

        List<Customer> allCust = pagePost.getContent();

        CustResponse custResponse = new CustResponse();

        custResponse.setData(allCust);
        custResponse.setPageNumber(pagePost.getNumber());
        custResponse.setPageSize(pagePost.getSize());
        custResponse.setTotalPages(pagePost.getTotalPages());
        custResponse.setTotalElements(pagePost.getTotalElements());
        custResponse.setLastPage(pagePost.isLast());

        return custResponse;


    }

    public Customer getById(String id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public Customer updateCust(Customer c, String id) {
        return customerRepo.findById(id)
                .map(user -> {
                    user.setFirst_name(c.getFirst_name());
                    user.setLast_name(c.getLast_name());
                    user.setCity(c.getCity());
                    user.setPhone(c.getPhone());
                    user.setAddress(c.getAddress());
                    user.setStreet(c.getStreet());
                    user.setState(c.getState());
                    user.setEmail(c.getEmail());

                    return customerRepo.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public String delete(String id) {
        if (!customerRepo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        customerRepo.deleteById(id);
        return "User with id " + id + " is deleted";
    }

    public String sync(Credentials c) {

        String uri = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";


        WebClient.Builder builder = WebClient.builder();

        String t = builder.build().post()
                .uri(uri)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(Mono.just(c),Credentials.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();


//        try{
//
//
//            assert t != null;
//            return t.getAccess_token();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }





//        String accessToken = t != null ? t.getAccess_token() : null;
//
//        System.out.println(accessToken);

        String token = t.substring(19,59);

         Flux<Customer_R> cust_list = builder.build().get()
                    .uri("https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToFlux(Customer_R.class);


        cust_list.subscribe(customer -> {
            // Here, 'customer' is each individual Customer object in the Flux
            if(!this.customerRepo.existsById(customer.getId())){
                Customer cust_r = new Customer();
                cust_r.setId(customer.getId());

                cust_r.setState(customer.getState());
                cust_r.setPhone(customer.getPhone());
                cust_r.setCity(customer.getCity());
                cust_r.setStreet(customer.getStreet());
                cust_r.setLast_name(customer.getLast_name());
                cust_r.setFirst_name(customer.getFirst_name());
                cust_r.setEmail(customer.getEmail());
                cust_r.setAddress(customer.getAddress());


                this.customerRepo.save(cust_r);
            }
            else {

                System.out.println("Customer with ID " + customer.getId() + " already exists || updating");
                customerRepo.findById(customer.getId())
                        .map(user -> {
                            user.setFirst_name(customer.getFirst_name());
                            user.setLast_name(customer.getLast_name());
                            user.setCity(customer.getCity());
                            user.setPhone(customer.getPhone());
                            user.setAddress(customer.getAddress());
                            user.setStreet(customer.getStreet());
                            user.setState(customer.getState());
                            user.setEmail(customer.getEmail());

                            return customerRepo.save(user);
                        });


            }

        }, error -> {

            System.err.println("Error occurred: " + error.getMessage());
        }, () -> {

            System.out.println("API call completed");
        });




         return "last";
    }

    public List<Customer> searchCity(String key){
        List<Customer> custs = this.customerRepo.findByCityContaining(key);

        return custs;
    }

    public List<Customer> searchState(String key){
        List<Customer> custs = this.customerRepo.findByStateContaining(key);

        return custs;
    }

}
