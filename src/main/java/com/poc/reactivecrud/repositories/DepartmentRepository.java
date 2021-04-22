package com.poc.reactivecrud.repositories;

import com.poc.reactivecrud.models.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DepartmentRepository extends ReactiveCrudRepository<Department,Integer> {
}

