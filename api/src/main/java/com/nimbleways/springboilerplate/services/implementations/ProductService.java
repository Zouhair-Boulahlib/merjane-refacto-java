package com.nimbleways.springboilerplate.services.implementations;

import java.time.LocalDate;
import java.util.UUID;

import com.nimbleways.springboilerplate.dto.product.ProcessOrderResponse;
import com.nimbleways.springboilerplate.repositories.OrderRepository;
import com.nimbleways.springboilerplate.services.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.nimbleways.springboilerplate.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final OrderRepository orderRepository;

    private final OrderProductService orderProductService;

    public ProcessOrderResponse doOrder(Long orderID){
        return orderRepository.findById(orderID).map(order -> {
                 order.getItems().forEach(orderProductService::makeOrder);
                return new ProcessOrderResponse(orderID);
                })
                .orElseThrow(()-> new IllegalArgumentException("Product Not Found"));

    }

}