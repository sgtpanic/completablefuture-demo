package com.influentialcode;

import com.google.inject.Inject;
import com.influentialcode.exceptions.QuantityUnavailableException;
import com.influentialcode.persistence.CatalogDao;
import com.influentialcode.persistence.PurchasesDao;
import com.influentialcode.persistence.WarehouseDao;

import java.time.Instant;

public class CreateOrderImpl extends CreateOrder {

    @Inject
    public CreateOrderImpl(CatalogDao catalogDao, PurchasesDao purchaseDao, WarehouseDao warehouseDao) {
        super(catalogDao, purchaseDao, warehouseDao);
    }

    @Override
    public void createOrder() {
        LOGGER.info("Beginning to create order synchronously");

        long startTime = Instant.now().getEpochSecond();

        LOGGER.info("Beginning to query warehouse");
        int quantityAvailable = warehouseDao.getQuantity(PRODUCT_ID);
        LOGGER.info("Finished querying warehouse.");

        if (quantityAvailable < QUANTITY_DESIRED) {
            LOGGER.error("There isn't enough quantity available");
            throw new QuantityUnavailableException();
        }

        LOGGER.info("Beginning to query catalog for price.");
        int itemPrice = catalogDao.getPrice(PRODUCT_ID);
        LOGGER.info("Finished querying catalog.");

        int totalPrice = itemPrice * quantityAvailable;

        LOGGER.info("Adding purchase order.");
        purchaseDao.addPurchase(CUSTOMER_ID, totalPrice);
        LOGGER.info("Purchase order created.");

        long endTime = Instant.now().getEpochSecond();
        LOGGER.info("Total processing time: {}", endTime - startTime);
    }
}
