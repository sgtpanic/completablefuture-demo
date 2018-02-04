package com.influentialcode.persistence;

/**
 * Data access object for interacting with the product catalog persistent storage.
 */
public interface CatalogDao {

    /**
     * Given a product ID, retrieve the price of the item.
     *
     * @param productId ID of product to retrieve
     * @return price of item
     */
    int getPrice(final int productId);
}
