package com.kangbakso.spring_tutorial.entity.postgres;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    public String toString() {
        return "Customer ID: " + this.getId() + " - " +
                "FirstName: " + this.getFirstName() + " - " +
                "LastName: " + this.getLastName() + "\n";
    }
}
