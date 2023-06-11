package com.example.Customers.Repository;

import com.example.Customers.Model.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
