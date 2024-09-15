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
    private int personID;
    private String personFirstName;
    private String personLastName;
    private String phoneNumber;
    private String nationalCode;
    private Role role;
    private boolean status;
    private String userName;
    private String password;
}
