package Model.da;

import Model.entity.Bank;
import Model.tools.JdbcProvider;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;




@Log4j
public class BankDa {
    private Connection connection;
    private PreparedStatement preparedStatement;


    public void save(Bank bank) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement=connection.prepareStatement(
                "INSERT INTO BANK_TBL VALUES (?,?,?,?)"
        );
        preparedStatement.setInt(1, bank.getId());
        preparedStatement.setString(2, bank.getName());
        preparedStatement.setString(3, bank.getAccountNumber());
        preparedStatement.setDouble(4, bank.getAmount());
        preparedStatement.execute();

        log.info("bank saved");
    }
}
