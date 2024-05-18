package com.nimbleways.springboilerplate.services;

import com.nimbleways.springboilerplate.dto.product.ProcessOrderResponse;
import com.nimbleways.springboilerplate.entities.Product;

public interface OrderProductService {

    ProcessOrderResponse makeOrder(Product product);
}
