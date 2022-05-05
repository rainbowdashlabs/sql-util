package de.chojo.sqlutil.jdbc;

import de.chojo.sqlutil.databases.SqlType;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MariaDbJdbcTest {

    MariaDbJdbc jdbc;

    @BeforeEach
    void setUp() {
        jdbc = SqlType.MARIADB.jdbcBuilder();
    }




}
