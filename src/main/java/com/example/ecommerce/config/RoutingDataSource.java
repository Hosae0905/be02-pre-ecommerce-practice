package com.example.ecommerce.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        boolean result = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if (result == true) {
            return "slave";
        } else {
            return "master";
        }
    }
}
