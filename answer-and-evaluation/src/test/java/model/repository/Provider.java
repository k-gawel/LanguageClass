package model.repository;

import io.vavr.Tuple2;
import org.jooq.meta.derby.sys.Sys;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.UUID;

public final class Provider {

    public static String mainUrl = "jdbc:postgresql://localhost:5432/language_study_material";



    public static DataSource getDataSource() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
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
