package com.sb04.employee.service;

import com.sb04.employee.entity.Credentials;
import com.sb04.employee.entity.Cust_List;
import com.sb04.employee.entity.Customer;
import com.sb04.employee.entity.Token;
import com.sb04.employee.exception.UserNotFoundException;
import com.sb04.employee.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
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

    public List<Customer> getAll() {
        return customerRepo.findAll();
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

        String uri = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";


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

         Flux<Customer> cust_list = builder.build().get()
                    .uri("https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToFlux(Customer.class);


        cust_list.subscribe(customer -> {
            // Here, 'customer' is each individual Customer object in the Flux
            if(!customerRepo.existsById(customer.getId())){
                customerRepo.save(customer);
            }
            else {

                System.out.println("Customer with ID " + customer.getId() + " already exists.");
            }

        }, error -> {

            System.err.println("Error occurred: " + error.getMessage());
        }, () -> {

            System.out.println("API call completed");
        });




         return "last";
    }

}
