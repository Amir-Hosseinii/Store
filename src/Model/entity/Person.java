package Model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

public class Person {
    private int ID;
    private String FirstName;
    private String LastName;
    private String nationalID;
    private String phoneNumber;
    private Role role;
    private boolean status; //todo:validation??
    private String userName;
    private String password;
    private String accessLevel; //todo:validation??
}
