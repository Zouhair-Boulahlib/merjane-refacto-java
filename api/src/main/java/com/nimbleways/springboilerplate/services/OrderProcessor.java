package com.nimbleways.springboilerplate.services;

import com.nimbleways.springboilerplate.dto.product.ProcessOrderResponse;
import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.services.implementations.NotificationService;

public interface OrderProcessor {
    ProcessOrderResponse process(Product product);

    default void notifyDelay(NotificationService notificationService, Product product) {
        notificationService.sendDelayNotification(product.getLeadTime(), product.getName());
    }
}
