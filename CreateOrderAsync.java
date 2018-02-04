package com.influentialcode;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.influentialcode.exceptions.QuantityUnavailableException;
import com.influentialcode.guice.StoreModule;
import com.influentialcode.persistence.CatalogDao;
import com.influentialcode.persistence.PurchasesDao;
import com.influentialcode.persistence.WarehouseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public class CreateOrderAsync {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderAsync.class);
    private static final Injector injector = Guice.createInjector(new StoreModule());
    private static final CatalogDao catalogDao = injector.getInstance(CatalogDao.class);
    private static final PurchasesDao purchaseDao = injector.getInstance(PurchasesDao.class);
    private static final WarehouseDao warehouseDao = injector.getInstance(WarehouseDao.class);

    public static void main(String[] args) throws InterruptedException {
        int quantityDesired = 3;
        int productId = 105;
        int customerId = 115;

        long startTime = Instant.now().getEpochSecond();
        CompletableFuture<Integer> futurePrice = CompletableFuture.supplyAsync(() -> catalogDao.getPrice(productId));
        CompletableFuture.supplyAsync(() -> warehouseDao.getQuantity(productId))
                .thenCombine(futurePrice, (quantityAvailable, price) -> {
                    if (quantityAvailable < quantityDesired) {
                        throw new QuantityUnavailableException();
                    }

                    return quantityDesired * price;
                })
                .whenComplete((price, throwable) -> {
                    if (throwable != null) {
                        LOGGER.error("Unable to complete purchase");
                    } else {
                        purchaseDao.addPurchase(customerId, price);
                        LOGGER.info("Total time: {} seconds", Instant.now().getEpochSecond() - startTime);
                    }
                });

        Thread.sleep(10000);
    }
}
