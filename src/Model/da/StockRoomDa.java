package Model.da;

import Model.entity.Product;
import Model.entity.StockRoom;
import Model.tools.JdbcProvider;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j
public class StockRoomDa implements DataAccess<StockRoom , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;



    @Override
    public void save(StockRoom stockRoom) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT STOCK_ROOM_SEQ.nextval AS NEXT_ID FROM dual"
        );
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        stockRoom.setID(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO STOCK_ROOM_TBL VALUES (?,?)"
        );
        preparedStatement.setInt(1, stockRoom.getID());
        preparedStatement.setInt(2, stockRoom.getCount());
        preparedStatement.execute();

        log.info("stockRoom saved");
    }

    @Override
    public void edit(StockRoom stockRoom) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE STOCK_ROOM_TBL SET COUNT=?  WHERE ID=?"
        );
        preparedStatement.setInt(1, stockRoom.getCount());
        preparedStatement.setInt(2, stockRoom.getID());
        preparedStatement.execute();

        log.info("stockRoom edited");
    }

    @Override
    public void remove(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM STOCK_ROOM_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        log.info("stockRoom removed");
    }

    @Override
    public List<StockRoom> findAll() throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM STOCK_ROOM_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<StockRoom> stockRoomList = new ArrayList<>();

        while (resultSet.next()){
            StockRoom stockRoom=
                    StockRoom
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .count(resultSet.getInt("COUNT"))
                            .build();
            stockRoomList.add(stockRoom);
        }
        log.info("stockRoom list loaded");
        return stockRoomList;
    }

    @Override
    public StockRoom findById(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM STOCK_ROOM_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        StockRoom stockRoom= null;
        if(resultSet.next()){
            stockRoom=
                    StockRoom
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .count(resultSet.getInt("COUNT"))
                            .build();
        }
        log.info("stockRoom found by id");
        return stockRoom;
    }



    //todo: how to write based on findByCountLessThan???
//    findByCountLessThan()




    //todo: how to write based on findAvailable???
//    findAvailable()



    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
