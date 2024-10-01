package Model.da;

import Model.entity.StockRoom;

import java.util.Collections;
import java.util.List;

public class StockRoomDa implements DataAccess<StockRoom , Integer>{
    @Override
    public void save(StockRoom stockRoom) throws Exception {

    }

    @Override
    public void edit(StockRoom stockRoom) throws Exception {

    }

    @Override
    public void remove(Integer id) throws Exception {

    }

    @Override
    public List<StockRoom> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public StockRoom findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
