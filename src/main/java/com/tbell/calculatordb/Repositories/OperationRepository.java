package com.tbell.calculatordb.Repositories;

import com.tbell.calculatordb.models.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
    Iterable<Operation> findAllByOperationUser_Id(long userId);
}
