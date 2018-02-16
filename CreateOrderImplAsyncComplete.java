package com.influentialcode;

import com.google.inject.Inject;
import com.influentialcode.exceptions.QuantityUnavailableException;
import com.influentialcode.persistence.CatalogDao;
import com.influentialcode.persistence.PurchasesDao;
import com.influentialcode.persistence.WarehouseDao;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public class CreateOrderImplAsyncComplete extends CreateOrder {
    @Inject
    public CreateOrderImplAsyncComplete(CatalogDao catalogDao, PurchasesDao purchaseDao, WarehouseDao warehouseDao) {
        super(catalogDao, purchaseDao, warehouseDao);
    }

    @Override
    public void createOrder() {
        LOGGER.info("Beginning to create order synchronously");
        long startTime = Instant.now().getEpochSecond();

        LOGGER.info("Beginning to query warehouse");
        CompletableFuture<Integer> futureQuantity = CompletableFuture.supplyAsync(() ->
                warehouseDao.getQuantity(PRODUCT_ID));
        LOGGER.info("Finished querying warehouse.");


        LOGGER.info("Beginning to query catalog for price.");
        CompletableFuture<Integer> futurePrice = CompletableFuture.supplyAsync(() -> catalogDao.getPrice(PRODUCT_ID));
        LOGGER.info("Finished querying catalog.");


        LOGGER.info("Adding purchase order.");
        CompletableFuture<Integer> futureOrder = futureQuantity.thenCombine(futurePrice,
                (quantity, price) -> {
                    if (quantity < QUANTITY_DESIRED) {
                        LOGGER.error("There isn't enough quantity available");
                        throw new QuantityUnavailableException();
                    }

                    return price * quantity;
                });

        while (!futureOrder.isDone()) {
            // wait
        }
        LOGGER.info("Purchase order created.");

        long endTime = Instant.now().getEpochSecond();
        LOGGER.info("Total processing time: {}", endTime - startTime);
    }
}
