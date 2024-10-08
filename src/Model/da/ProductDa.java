package Model.da;


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

public class ProductDa implements DataAccess<Product , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public void save(Product product) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT PRODUCT_SEQ.nextval AS NEXT_ID FROM dual"
        );
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        product.setID(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PRODUCT_TBL VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, product.getID());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setString(3, product.getBrand());
        preparedStatement.setDouble(4, product.getPrice());
        preparedStatement.setString(5, product.getBarcode());
        preparedStatement.execute();

        log.info("product saved");
    }

    @Override
    public void edit(Product product) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE PRODUCT_TBL SET NAME=? , BRAND=? , PRICE=? , BARCODE=? WHERE ID=?"
        );
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getBrand());
        preparedStatement.setDouble(3, product.getPrice());
        preparedStatement.setString(4, product.getBarcode());
        preparedStatement.setInt(5, product.getID());
        preparedStatement.execute();

        log.info("product edited");
    }

    @Override
    public void remove(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PRODUCT_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        log.info("product removed");
    }

    @Override
    public List<Product> findAll() throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> productList = new ArrayList<>();

        while (resultSet.next()){
            Product product=
                    Product
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .Name(resultSet.getString("NAME"))
                            .brand(resultSet.getString("BRAND"))
                            .price(resultSet.getDouble("PRICE"))
                            .barcode(resultSet.getString("BARCODE"))
                            .build();
            productList.add(product);
        }
        log.info("product list loaded");
        return productList;
    }

    @Override
    public Product findById(Integer id) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = null;
        if(resultSet.next()){
            product=
                    Product
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .Name(resultSet.getString("NAME"))
                            .brand(resultSet.getString("BRAND"))
                            .price(resultSet.getDouble("PRICE"))
                            .barcode(resultSet.getString("BARCODE"))
                            .build();
        }
        log.info("product found by id");
        return product;
    }

    public List<Product> findByBrand(String brand) throws Exception{
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL WHERE BRAND=?"
        );
        preparedStatement.setString(1, brand);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            Product product=
                    Product
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .Name(resultSet.getString("NAME"))
                            .brand(resultSet.getString("BRAND"))
                            .price(resultSet.getDouble("PRICE"))
                            .barcode(resultSet.getString("BARCODE"))
                            .build();
            productList.add(product);
        }
        log.info("product found by brand");
        return productList;
    }


    public List<Product> findByProductName(String name) throws Exception{
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL WHERE NAME=?"
        );
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            Product product=
                    Product
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .Name(resultSet.getString("NAME"))
                            .brand(resultSet.getString("BRAND"))
                            .price(resultSet.getDouble("PRICE"))
                            .barcode(resultSet.getString("BARCODE"))
                            .build();
            productList.add(product);
        }
        log.info("product found by name");
        return productList;
    }

    public Product findByBarcode(Integer barcode) throws Exception {
        connection = JdbcProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL WHERE BARCODE=?"
        );
        preparedStatement.setInt(1, barcode);
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = null;
        if(resultSet.next()){
            product=
                    Product
                            .builder()
                            .ID(resultSet.getInt("ID"))
                            .Name(resultSet.getString("NAME"))
                            .brand(resultSet.getString("BRAND"))
                            .price(resultSet.getDouble("PRICE"))
                            .barcode(resultSet.getString("BARCODE"))
                            .build();
        }
        log.info("product found by barcode");
        return product;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
