package com.app.persistence.data.reader.loader.repository.db.connection;

import org.jdbi.v3.core.Jdbi;

import java.util.Properties;

public class CarDbConnectionImpl implements DbConnection{
    @Override
    public Jdbi create(Properties properties) {
        return Jdbi.create(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );
    }
}
