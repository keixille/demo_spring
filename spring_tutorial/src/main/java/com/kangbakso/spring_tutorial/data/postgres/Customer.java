package com.kangbakso.spring_tutorial.data.postgres;


import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customer_id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return "Customer First Name: " + this.firstName + "\n" +
                "Customer Last Name: " + this.lastName;
    }
}
