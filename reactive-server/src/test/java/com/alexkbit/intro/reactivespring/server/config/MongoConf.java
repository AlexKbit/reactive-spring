package com.alexkbit.intro.reactivespring.server.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;

/**
 * Configuration embedded mongodb.
 */
@Configuration
public class MongoConf extends AbstractMongoConfiguration {

    private static final String DB_NAME = "test";

    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    public MongoClient mongoClient() {
        try {
            return new EmbeddedMongoBuilder()
                    .version("2.6.2")
                    .bindIp("127.0.0.1")
                    .port(27017)
                    .build();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}