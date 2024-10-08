package Model.da;

import Model.entity.Person;
import Model.entity.Role;
import Model.tools.JdbcProvider;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//@Log4j

public class PersonDa implements DataAccess<Person , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;


    public Integer getNextId() throws SQLException {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement("SELECT PERSON_SEQ.NEXTVAL AS NEXT_ID FROM DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXT_ID");
    }

    @Override
    public void save(Person person) throws Exception {
       person.setID(getNextId());


        preparedStatement=connection.prepareStatement(
                "INSERT INTO PERSON_TBL(ID ,FIRST_NAME ,LAST_NAME,NATIONALID,PHONE_NUMBER,ROLE,USERNAME,PASSWORD , STATUS , ACCESS_LEVEL) VALUES (?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getID());
        preparedStatement.setString(2, person.getFirstName());
        preparedStatement.setString(3, person.getLastName());
        preparedStatement.setString(4, person.getNationalID());
        preparedStatement.setString(5, person.getPhoneNumber());
        preparedStatement.setString(6, person.getRole().name());
        preparedStatement.setString(7,person.getUserName());
        preparedStatement.setString(8,person.getPassword());
        preparedStatement.setBoolean(9,person.isStatus());
        preparedStatement.setString(10,person.getAccessLevel());
        preparedStatement.execute();

    }

    @Override
    public void edit(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON_TBL SET FIRST_NAME=? ,LAST_NAME=? ,NATIONALID=? ,  PHONE_NUMBER=? , ROLE=? ,USERNAME=?,PASSWORD=? , STATUS=? , ACCESS_LEVEL=? WHERE ID=? "
        );
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3, person.getNationalID());
        preparedStatement.setString(4, person.getPhoneNumber());
        preparedStatement.setString(5, person.getRole().name());
        preparedStatement.setString(6,person.getUserName());
        preparedStatement.setString(7,person.getPassword());
        preparedStatement.setBoolean(8,person.isStatus());
        preparedStatement.setString(9,person.getAccessLevel());
        preparedStatement.setInt(10, person.getID());
        preparedStatement.execute();

    }

    @Override
    public void remove(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "DELETE FROM PERSON_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<Person> findAll() throws Exception {
        List<Person> personList = new ArrayList<>();

        preparedStatement=connection.prepareStatement(
                "SELECT * FROM PERSON_TBL ORDER BY ID"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = new Person();
            person = Person
                    .builder()
                    .ID(resultSet.getInt("ID"))
                    .FirstName(resultSet.getString("FIRST_NAME"))
                    .LastName(resultSet.getString("LAST_NAME"))
                    .nationalID(resultSet.getString("NATIONALID"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .role(Role.valueOf(resultSet.getString("ROLE")))
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .status(resultSet.getBoolean("STATUS"))
                    .accessLevel(resultSet.getString("ACCESS_LEVEL"))
                    .build();

            personList.add(person);

        }
        return personList;
    }

    @Override
    public Person findById(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON_TBL WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .ID(resultSet.getInt("ID"))
                    .FirstName(resultSet.getString("FIRST_NAME"))
                    .LastName(resultSet.getString("LAST_NAME"))
                    .nationalID(resultSet.getString("NATIONALID"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .role(Role.valueOf(resultSet.getString("ROLE")))
                    .userName(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .status(resultSet.getBoolean("STATUS"))
                    .accessLevel(resultSet.getString("ACCESS_LEVEL"))
                    .build();
        }
        return person;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
