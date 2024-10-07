package Test;

import Model.da.PersonDa;
import Model.entity.Person;

public class PersonTest {
    public static void main(String[] args) {
        Person person = Person
                .builder()
                .FirstName("ali")
                .LastName("alipour")
                .build();
        System.out.println(person);

        PersonDa personDa = new PersonDa();

//        PersonBl.save(person);
    }
}
