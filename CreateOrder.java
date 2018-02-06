package com.influentialcode;

import com.google.inject.ImplementedBy;
import com.influentialcode.persistence.CatalogDao;
import com.influentialcode.persistence.PurchasesDao;
import com.influentialcode.persistence.WarehouseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ImplementedBy(CreateOrderImpl.class)
public abstract class CreateOrder {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderImplAsync.class);
    protected static final int quantityDesired = 3;
    protected static final int productId = 105;
    protected static final int customerId = 115;

    protected CatalogDao catalogDao;
    protected PurchasesDao purchaseDao;
    protected WarehouseDao warehouseDao;

    public CreateOrder(CatalogDao catalogDao, PurchasesDao purchaseDao, WarehouseDao warehouseDao) {
        this.catalogDao = catalogDao;
        this.purchaseDao = purchaseDao;
        this.warehouseDao = warehouseDao;
    }

    /**
     * Create a product order using parameters already in the class.
     */
    abstract void createOrder();
}
