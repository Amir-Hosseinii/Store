package Model.da;

import Model.entity.*;
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

public class InvoiceDa implements DataAccess<Invoice , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;



    @Override
    public void save(Invoice invoice) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT INVOICE_SEQ.nextval AS NEXT_ID FROM dual"
        );
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        invoice.setID(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO INVOICE_TBL VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, invoice.getID());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(invoice.getInvoiceDateTime()));
        preparedStatement.setDouble(3,invoice.getTotalAmount());
        preparedStatement.setString(4,invoice.getInvoiceType().name());
        preparedStatement.setString(5,invoice.getShoppingType().name());
        preparedStatement.execute();

        log.info("invoice saved");
    }

    @Override
    public void edit(Invoice invoice) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE INVOICE_TBL SET INVOICE_TIME=? , TOTAL_AMOUNT=? , INVOICE_TYPE=? , SHOPPING_TYPE=? WHERE ID=?"
        );
        preparedStatement.setTimestamp(1, Timestamp.valueOf(invoice.getInvoiceDateTime()));
        preparedStatement.setDouble(2,invoice.getTotalAmount());
        preparedStatement.setString(3,invoice.getInvoiceType().name());
        preparedStatement.setString(4,invoice.getShoppingType().name());
        preparedStatement.setInt(5, invoice.getID());
        preparedStatement.execute();

        log.info("invoice edited");
    }

    @Override
    public void remove(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM INVOICE_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1 , id);
        preparedStatement.execute();

        log.info("invoice removed");
    }

    @Override
    public List<Invoice> findAll() throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM INVOICE_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Invoice> invoiceList = new ArrayList<>();

        while (resultSet.next()){
            Invoice invoice=
                    Invoice
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .invoiceDateTime(resultSet.getTimestamp("INVOICE_DATE_TIME").toLocalDateTime())
                            .totalAmount(resultSet.getDouble("TOTAL_AMOUNT"))
                            .invoiceType(InvoiceType.valueOf(resultSet.getString("INVOICE_TYPE")))
                            .shoppingType(ShoppingType.valueOf(resultSet.getString("SHOPPING_TYPE")))
                            .build();
            invoiceList.add(invoice);
        }
        log.info("invoice list loaded");
        return invoiceList;
    }

    @Override
    public Invoice findById(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM INVOICE_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Invoice invoice = null;
        if(resultSet.next()){
            invoice=
                    Invoice
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .invoiceDateTime(resultSet.getTimestamp("INVOICE_DATE_TIME").toLocalDateTime())
                            .totalAmount(resultSet.getDouble("TOTAL_AMOUNT"))
                            .invoiceType(InvoiceType.valueOf(resultSet.getString("INVOICE_TYPE")))
                            .shoppingType(ShoppingType.valueOf(resultSet.getString("SHOPPING_TYPE")))
                            .build();
        }
        log.info("invoice found by id");
        return invoice;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
