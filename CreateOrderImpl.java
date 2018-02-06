package com.influentialcode;

import com.google.inject.Inject;
import com.influentialcode.persistence.CatalogDao;
import com.influentialcode.persistence.PurchasesDao;
import com.influentialcode.persistence.WarehouseDao;

public class CreateOrderImpl extends CreateOrder {

    @Inject
    public CreateOrderImpl(CatalogDao catalogDao, PurchasesDao purchaseDao, WarehouseDao warehouseDao) {
        super(catalogDao, purchaseDao, warehouseDao);
    }

    @Override
    public void createOrder() {
        LOGGER.info("Beginning to create order synchronously");

    }
}
