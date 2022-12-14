package org.bls.helper.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
public class BLSUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "boolean default false")
    private boolean state;
    @Column(columnDefinition = "boolean default false")
    private boolean isAdmin;




}
