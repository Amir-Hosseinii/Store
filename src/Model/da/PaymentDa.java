package Model.da;

import Model.entity.Payment;
import Model.entity.Product;
import Model.tools.JdbcProvider;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j

public class PaymentDa implements DataAccess<Payment , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;


    @Override
    public void save(Payment payment) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT PAYMENT_SEQ.nextval AS NEXT_ID FROM dual"
        );
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        payment.setID(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PAYMENT_TBL VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, payment.getID());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(payment.getPaymentDateTime()));
        preparedStatement.setDouble(3, payment.getAmount());
        preparedStatement.execute();

        log.info("payment saved");

    }

    @Override
    public void edit(Payment payment) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE PAYMENT_TBL SET PAYMENT_TIME=? , AMOUNT=?  WHERE ID=?"
        );
        preparedStatement.setTimestamp(1, Timestamp.valueOf(payment.getPaymentDateTime()));
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setInt(3, payment.getID());
        preparedStatement.execute();

        log.info("payment edited");
    }

    @Override
    public void remove(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PAYMENT_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        log.info("payment removed");
    }

    @Override
    public List<Payment> findAll() throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PAYMENT_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()){
            Payment payment=
                    Payment
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .paymentDateTime(resultSet.getTimestamp("PAYMENT_DATE_TIME").toLocalDateTime())
                            .amount(resultSet.getDouble("AMOUNT"))
                            .build();
            paymentList.add(payment);
        }
        log.info("payment list loaded");
        return paymentList;
    }

    @Override
    public Payment findById(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PAYMENT_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if(resultSet.next()){
            payment=
                    Payment
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .paymentDateTime(resultSet.getTimestamp("PAYMENT_DATE_TIME").toLocalDateTime())
                            .amount(resultSet.getDouble("AMOUNT"))
                            .build();
        }
        log.info("payment found by id");
        return payment;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
