package model.repository;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public final class Provider {

    public static DataSource getDataSource() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/test_database");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

}
