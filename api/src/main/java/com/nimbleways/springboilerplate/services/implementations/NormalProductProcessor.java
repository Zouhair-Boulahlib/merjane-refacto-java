package com.nimbleways.springboilerplate.services.implementations;

import com.nimbleways.springboilerplate.dto.product.ProcessOrderResponse;
import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.repositories.ProductRepository;
import com.nimbleways.springboilerplate.services.OrderProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("NORMAL")
@RequiredArgsConstructor
public class NormalProductProcessor implements OrderProcessor {
    private final ProductRepository productRepository;

    private final NotificationService notificationService;
    @Override
    public ProcessOrderResponse process(Product product) {
        if (product.getAvailable() > 0) {
            product.setAvailable(product.getAvailable() - 1);
            productRepository.save(product);
        } else {
            if (product.getLeadTime() > 0) {
                this.notifyDelay(notificationService, product);
            }
        }
        return new ProcessOrderResponse(product.getId());
    }
}
