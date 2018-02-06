package com.influentialcode;

import com.google.inject.Inject;
import com.influentialcode.persistence.CatalogDao;
import com.influentialcode.persistence.PurchasesDao;
import com.influentialcode.persistence.WarehouseDao;

public class CreateOrderImplAsync extends CreateOrder {
    private CatalogDao catalogDao;
    private PurchasesDao purchaseDao;
    private WarehouseDao warehouseDao;

    @Inject
    public CreateOrderImplAsync(CatalogDao catalogDao, PurchasesDao purchaseDao, WarehouseDao warehouseDao) {
        super(catalogDao, purchaseDao, warehouseDao);
    }

    @Override
    public void createOrder() {

    }
}
