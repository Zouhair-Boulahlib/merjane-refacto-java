package com.nimbleways.springboilerplate.services.implementations;

import com.nimbleways.springboilerplate.dto.product.ProcessOrderResponse;
import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.repositories.OrderRepository;
import com.nimbleways.springboilerplate.services.OrderProcessor;
import com.nimbleways.springboilerplate.services.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {

    private final Map<String, OrderProcessor> orderProcessors;

    @Override
    public ProcessOrderResponse makeOrder(Product product) {
        var productType =  product.getType();
        if(productType == null) throw new IllegalStateException("Product type not Specified");
        var orderProcessor = orderProcessors.get(productType);
        return orderProcessor.process(product);
    }
}
