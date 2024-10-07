package Model.da;

import Model.entity.DeliverStatus;
import Model.entity.Delivery;
import Model.tools.JdbcProvider;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Log4j

public class DeliveryDa implements DataAccess<Delivery , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;


    @Override
    public void save(Delivery delivery) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT DELIVERY_SEQ.nextval AS NEXT_ID FROM dual"
        );
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        delivery.setID(resultSet.getInt("NEXT_ID"));

        preparedStatement=connection.prepareStatement(
                "INSERT INTO DELIVERY_TBL VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, delivery.getID());
        preparedStatement.setString(2, delivery.getAddress());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(delivery.getSendDateTime()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(delivery.getDeliveredDateTime()));
        preparedStatement.setString(5, delivery.getReceiver());
        preparedStatement.setString(6, delivery.getDeliverStatus().name());
        preparedStatement.execute();

        log.info("delivery saved");
    }

    @Override
    public void edit(Delivery delivery) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE DELIVERY_TBL SET ADDRESS=? , SEND_TIME=?, DELIVERED_TIME=? , RECEIVER=? , DELIVER_STATUS=? WHERE ID=?"
        );
        preparedStatement.setString(1, delivery.getAddress());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(delivery.getSendDateTime()));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(delivery.getDeliveredDateTime()));
        preparedStatement.setString(4, delivery.getReceiver());
        preparedStatement.setString(5, delivery.getDeliverStatus().name());
        preparedStatement.setInt(6, delivery.getID());
        preparedStatement.execute();

        log.info("delivery edited");

    }

    @Override
    public void remove(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM DELIVERY_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        log.info("delivery removed");
    }

    @Override
    public List<Delivery> findAll() throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM DELIVERY_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Delivery> deliveryList = new ArrayList<>();

        while (resultSet.next()){
            Delivery delivery=
                    Delivery
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .address(resultSet.getString("ADDRESS"))
                            .sendDateTime(resultSet.getTimestamp("SEND_DATE_TIME").toLocalDateTime())
                            .deliveredDateTime(resultSet.getTimestamp("DELIVERED_DATE_TIME").toLocalDateTime())
                            .receiver(resultSet.getString("RECEIVER"))
                            .deliverStatus(DeliverStatus.valueOf(resultSet.getString("DELIVER_STATUS")))
                            .build();
            deliveryList.add(delivery);
        }
        log.info("delivery list loaded");
        return deliveryList;
    }

    @Override
    public Delivery findById(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM DELIVER_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Delivery delivery = null;
        if(resultSet.next()){
            delivery=
                    Delivery
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .address(resultSet.getString("ADDRESS"))
                            .sendDateTime(resultSet.getTimestamp("SEND_DATE_TIME").toLocalDateTime())
                            .deliveredDateTime(resultSet.getTimestamp("DELIVERED_DATE_TIME").toLocalDateTime())
                            .receiver(resultSet.getString("RECEIVER"))
                            .deliverStatus(DeliverStatus.valueOf(resultSet.getString("DELIVER_STATUS")))
                            .build();
        }
        log.info("delivery found by id");
        return delivery;
    }


    //todo: how to write based on paymentId???
//    @Override
//    public Delivery findByPaymentId(Integer id) throws Exception {
//        connection = JdbcProvider.getInstance().getConnection();
//        preparedStatement = connection.prepareStatement()
//    }




    public List<Delivery> findByStatus(String deliverStatus) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM DELIVERY_TBL WHERE DELIVER_STATUS=?"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Delivery> deliveryList = new ArrayList<>();

        while (resultSet.next()){
            Delivery delivery=
                    Delivery
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .address(resultSet.getString("ADDRESS"))
                            .sendDateTime(resultSet.getTimestamp("SEND_DATE_TIME").toLocalDateTime())
                            .deliveredDateTime(resultSet.getTimestamp("DELIVERED_DATE_TIME").toLocalDateTime())
                            .receiver(resultSet.getString("RECEIVER"))
                            .deliverStatus(DeliverStatus.valueOf(resultSet.getString("DELIVER_STATUS")))
                            .build();
            deliveryList.add(delivery);
        }
        log.info("delivery list found by status");
        return deliveryList;
    }



    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
