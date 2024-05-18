package com.nimbleways.springboilerplate.services.implementations;

import com.nimbleways.springboilerplate.dto.product.ProcessOrderResponse;
import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.repositories.ProductRepository;
import com.nimbleways.springboilerplate.services.OrderProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("SEASONAL")
@RequiredArgsConstructor
public class SeasonalProductOrderProcessor implements OrderProcessor {

    private final ProductRepository productRepository;

    private final NotificationService notificationService;

    @Override
    public ProcessOrderResponse process(Product product) {
        if (LocalDate.now().plusDays(product.getLeadTime()).isAfter(product.getSeasonEndDate())) {
            notificationService.sendOutOfStockNotification(product.getName());
            product.setAvailable(0);
            productRepository.save(product);
        } else if (product.getSeasonStartDate().isAfter(LocalDate.now())) {
            notificationService.sendOutOfStockNotification(product.getName());
        } else {
            notifyDelay(notificationService, product);
        }
        return new ProcessOrderResponse(product.getId());

    }

}
