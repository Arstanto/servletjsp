package com.mitrais.jee.dao;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DataSourceFactory {
    private final DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

    private DataSourceFactory() throws IOException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("database.properties");
        Properties properties = new Properties();
        try {
            assert inputStream != null;
            properties.load(inputStream);
            mysqlDataSource.setDatabaseName(properties.getProperty("database"));
            mysqlDataSource.setServerName(properties.getProperty("serverName"));
            mysqlDataSource.setPort(Integer.parseInt(properties.getProperty("port")));
            mysqlDataSource.setUser(properties.getProperty("user"));
            mysqlDataSource.setPassword(properties.getProperty("password"));
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File database.properties Not Found", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO Error", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Failed to close streams", e);
                }
            }
        }
        this.dataSource = mysqlDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper {
        private static DataSourceFactory INSTANCE = null;

        static {
            try {
                INSTANCE = new DataSourceFactory();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
