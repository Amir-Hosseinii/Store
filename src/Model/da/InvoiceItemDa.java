package Model.da;

import Model.entity.InvoiceItem;
import Model.entity.Product;
import Model.tools.JdbcProvider;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Log4j
public class InvoiceItemDa implements DataAccess<InvoiceItem , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public void save(InvoiceItem invoiceItem) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT INVOICE_ITEM_SEQ.nextval AS NEXT_ID FROM dual"
        );
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        invoiceItem.setID(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO INVOICE_ITEM_TBL VALUES (?,?)"
        );
        preparedStatement.setInt(1, invoiceItem.getID());
        preparedStatement.setInt(2, invoiceItem.getCount());
        preparedStatement.execute();

        log.info("invoiceItem saved");
    }

    @Override
    public void edit(InvoiceItem invoiceItem) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE INVOICE_ITEM_TBL SET COUNT=?  WHERE ID=?"
        );
        preparedStatement.setInt(1, invoiceItem.getCount());
        preparedStatement.setInt(2, invoiceItem.getID());
        preparedStatement.execute();

        log.info("invoiceItem edited");
    }

    @Override
    public void remove(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM INVOICE_ITEM_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        log.info("invoiceItem removed");
    }

    @Override
    public List<InvoiceItem> findAll() throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM INVOICE_ITEM_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<InvoiceItem> invoiceItemList = new ArrayList<>();

        while (resultSet.next()){
            InvoiceItem invoiceItem=
                    InvoiceItem
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .count(resultSet.getInt("COUNT"))
                            .build();
            invoiceItemList.add(invoiceItem);
        }
        log.info("invoiceItem list loaded");
        return invoiceItemList;
    }

    @Override
    public InvoiceItem findById(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM INVOICE_ITEM_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        InvoiceItem invoiceItem = null;
        if(resultSet.next()){
            invoiceItem=
                    InvoiceItem
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .count(resultSet.getInt("COUNT"))
                            .build();
        }
        log.info("invoiceItem found by id");
        return invoiceItem;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
