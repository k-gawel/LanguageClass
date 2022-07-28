package model.repository;

import org.jooq.meta.derby.sys.Sys;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class AbstractRepositoryTest {

    protected DataSource dataSource;

    public void init() throws SQLException, ClassNotFoundException {
        this.dataSource = Provider.getDataSource();
        assertNotNull(this.dataSource);
        createData(dataSource);
    }

    abstract protected void createData(DataSource dataSource) throws SQLException;

}
