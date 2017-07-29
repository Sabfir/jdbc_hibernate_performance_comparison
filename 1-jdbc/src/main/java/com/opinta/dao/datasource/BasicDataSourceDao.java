package com.opinta.dao.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

import static com.opinta.entity.Constant.DB_URL;
import static com.opinta.entity.Constant.JDBC_DRIVER;
import static com.opinta.entity.Constant.PASSWORD;
import static com.opinta.entity.Constant.USER;

public class BasicDataSourceDao {
    private static BasicDataSource bdSource = new BasicDataSource();
    static {
        bdSource.setMaxActive(10);
        bdSource.setMaxIdle(10);

        bdSource.setDriverClassName(JDBC_DRIVER);
        bdSource.setUrl(DB_URL);
        bdSource.setUsername(USER);
        bdSource.setPassword(PASSWORD);
    }

    public static Connection getConnection() {
        try {
            return bdSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
