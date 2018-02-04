package com.influentialcode.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class CatalogDaoImpl implements CatalogDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogDaoImpl.class);
    private final int delay;

    public CatalogDaoImpl(final int delay) {
        this.delay = delay;
    }

    @Override
    public int getPrice(int productId) {
        long startTime = Instant.now().getEpochSecond();
        LOGGER.info("Querying for price of item {}.", productId);

        try {
            Thread.sleep(delay * 1000);
        } catch (InterruptedException e) {
            // nothing for now
        }

        long endTime = Instant.now().getEpochSecond();
        LOGGER.info("Retrieved price for item {}.", productId);
        LOGGER.info("Took {} seconds to get price of item {}.", endTime - startTime, productId);

        return 5;
    }
}
