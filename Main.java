package com.influentialcode;

import com.google.inject.Guice;
import com.influentialcode.guice.StoreModule;

public class Main {

    public static void main(String[] args) {
        CreateOrder createOrder = Guice.createInjector(new StoreModule()).getInstance(CreateOrderImpl.class);
        createOrder.createOrder();
    }

}
