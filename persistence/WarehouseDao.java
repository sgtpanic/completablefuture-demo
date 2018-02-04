package com.influentialcode.persistence;

/**
 * Data access object for interacting with warehouse persistence storage.
 */
public interface WarehouseDao {

    /**
     * Given a product ID, get the quantity on hand.
     *
     * @param productId ID of product to query
     * @return quantity of supplied product
     */
    int getQuantity(final int productId);
}
