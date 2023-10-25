package com.app.persistence.data.reader.loader.repository.db.connection;

import org.jdbi.v3.core.Jdbi;

import java.util.Properties;

public interface DbConnection {
    Jdbi create(Properties properties);
}
