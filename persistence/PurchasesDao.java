package com.influentialcode.persistence;

/**
 * Data access object for interacting with purchases persistent storage.
 */
public interface PurchasesDao {

    /**
     * Add a purchase.
     *
     * @param customerId purchaser's customer ID
     * @param total amount of transaction
     */
    void addPurchase(final int customerId, final int total);
}
