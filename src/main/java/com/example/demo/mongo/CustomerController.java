package com.example.demo.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerRepository.findById(id).orElse(new Customer());
    }

    @PostMapping("")
    @Transactional(transactionManager = "mongoTransactionManager")
    public Boolean addorUpdateNewCustomer(@RequestBody Customer customer) {

        customerRepository.save(customer);

        int i = 1 / 0;

        return true;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable String id) {
        customerRepository.deleteById(id);
    }
}