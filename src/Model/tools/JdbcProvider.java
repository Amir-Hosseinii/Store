package Model.tools;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcProvider {
    private static JdbcProvider instance;
    private static BasicDataSource dataSource = new BasicDataSource();
    private JdbcProvider() {}


    public static JdbcProvider getInstance() {
        if (instance == null) {
            instance = new JdbcProvider();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("javase");
        dataSource.setPassword("java123");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
        return dataSource.getConnection();
    }
}
