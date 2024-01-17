package com.sb04.employee.repo;

import com.sb04.employee.entity.Customer;
import com.sb04.employee.entity.Customer_R;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,String> {

    List<Customer> findByStateContaining(String state);

    List<Customer> findByCityContaining(String city);
}
