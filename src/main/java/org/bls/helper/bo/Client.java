package org.bls.helper.bo;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import java.util.Date;

@Setter
@Getter
public class Client {


    private int id;
    private String lastName;
    private String firstName;
    private String birthDate;
    private String passportNumber;
    private String issueDate;
    private String expiryDate;
    private String passportPlace;
    private String email;
    private String password;


}
