package com.example.ecommerce.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    public static final String MASTER_DATASOURCE = "masterDataSource";
    public static final String SLAVE_DATASOURCE = "slaveDataSource";

    @Bean(MASTER_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.master.hikari")
    public DataSource masterDataSource() {
        DataSource result = DataSourceBuilder.create().type(HikariDataSource.class).build();
        return result;
    }

    @Bean(SLAVE_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.slave.hikari")
    public DataSource slaveDataSource() {
        DataSource result = DataSourceBuilder.create().type(HikariDataSource.class).build();
        return result;
    }

    @Bean
    @Primary
    @DependsOn({MASTER_DATASOURCE, SLAVE_DATASOURCE})     // 지정한 빈이 만들어져야 시작해라??
    public DataSource routingDataSource(
            @Qualifier(MASTER_DATASOURCE) DataSource master,
            @Qualifier(SLAVE_DATASOURCE) DataSource slave
    ) {

        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>() {
            {
                put("master", master);
                put("slave", slave);
            }
        };

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource());

        return routingDataSource;
    }

    @Bean
    @DependsOn({"routingDataSource"})
    public LazyConnectionDataSourceProxy dataSource(DataSource dataSource) {
        LazyConnectionDataSourceProxy result = new LazyConnectionDataSourceProxy(dataSource);
        return result;
    }
}
