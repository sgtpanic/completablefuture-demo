package com.influentialcode.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PurchasesDaoImpl implements PurchasesDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchasesDaoImpl.class);

    @Override
    public void addPurchase(int customerId, int total) {
        LOGGER.info("Creating order for customer {} of cost {}.", customerId, total);
        LOGGER.info("Order has been placed.");
    }
}
