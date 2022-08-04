package model.repository;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public final class Provider {

    public static String mainUrl = "jdbc:postgresql://localhost:5432/language_study_material";



    public static DataSource getDataSource() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(getUrl("language_study_material"));
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

    private static String getUrl(String dbName) {
        return "jdbc:postgresql://localhost:5432/" + dbName;
    }

}
