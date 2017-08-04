package com.tbell.calculatordb.Repositories;

import com.tbell.calculatordb.models.OperationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationUserRepository extends CrudRepository<OperationUser, Long>{

}
