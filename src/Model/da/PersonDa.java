package Model.da;


import Model.entity.Person;
import Model.tools.JdbcProvider;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;


@Log4j

public class PersonDa implements DataAccess<Person , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;


    @Override
    public void save(Person person) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT PERSON_SEQ.nextval AS NEXT_ID FROM dual"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        person.setID(resultSet.getInt("NEXT_ID"));

        preparedStatement=connection.prepareStatement(
                "INSERT INTO PERSON_TBL VALUES (?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getID());
        preparedStatement.setString(2, person.getFirstName());
        preparedStatement.setString(3, person.getLastName());
        preparedStatement.setString(4, person.getNationalID());
        preparedStatement.setString(5, person.getPhoneNumber());
        preparedStatement.setString(6, person.getRole().name());
    }

    @Override
    public void edit(Person person) throws Exception {

    }

    @Override
    public void remove(Integer id) throws Exception {

    }

    @Override
    public List<Person> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Person findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
