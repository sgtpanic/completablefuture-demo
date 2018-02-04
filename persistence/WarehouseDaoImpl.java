package com.influentialcode.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class WarehouseDaoImpl implements WarehouseDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseDaoImpl.class);
    private final int delay;

    public WarehouseDaoImpl(final int delay) {
        this.delay = delay;
    }

    @Override
    public int getQuantity(int productId) {
        long startTime = Instant.now().getEpochSecond();
        LOGGER.info("Querying the quantity of item {}.", productId);

        try {
            Thread.sleep(delay * 1000);
        } catch (InterruptedException e) {
            // nothing for now
        }

        long endTime = Instant.now().getEpochSecond();
        LOGGER.info("Retrieved quantity for item {}.", productId);
        LOGGER.info("Took {} seconds to query for item {}.", endTime - startTime, productId);

        return 10;
    }
}
