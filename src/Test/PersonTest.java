package Test;

import Model.da.PersonDa;
import Model.entity.Person;
import Model.entity.Role;

public class PersonTest {
    public static void main(String[] args) throws Exception {
       Person person=
               Person.builder()
                       .FirstName("Mahoor")
                       .LastName("Shams")
                       .nationalID("1111111111")
                       .phoneNumber("09121212121")
                       .role(Role.Manager)
                       .userName("MahoorShams")
                       .password("123432123")
                       .status(true)
                       .accessLevel("0101")
                       .build();

       PersonDa personDa = new PersonDa();
       personDa.save(person);
    }
}
