package com.example.spring.adder;

import org.springframework.data.repository.CrudRepository;

public interface AdditionRepository extends CrudRepository<LoggedSum, Integer> {}