package com.influentialcode.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.influentialcode.persistence.*;

public class StoreModule extends AbstractModule {

    protected void configure() {
        bind(PurchasesDao.class).to(PurchasesDaoImpl.class);
    }

    @Provides
    protected CatalogDao provideCatalogDao() {
        return new CatalogDaoImpl(5);
    }

    @Provides
    protected WarehouseDao provideWarehouseDao() {
        return new WarehouseDaoImpl(5);
    }

}
