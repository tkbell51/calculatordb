package com.tbell.calculatordb.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "operation_user")
public class OperationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @OneToMany(mappedBy = "operationUser", cascade = CascadeType.ALL)
    private List<Operation> operations;

    public OperationUser() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
