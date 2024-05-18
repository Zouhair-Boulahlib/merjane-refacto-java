package com.nimbleways.springboilerplate.services.implementations;

import com.nimbleways.springboilerplate.dto.product.ProcessOrderResponse;
import com.nimbleways.springboilerplate.entities.Product;
import com.nimbleways.springboilerplate.repositories.ProductRepository;
import com.nimbleways.springboilerplate.services.OrderProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("FLASHSALE")
@RequiredArgsConstructor
public class FlashSaleProductOrderProcessor implements OrderProcessor {

    private final ProductRepository productRepository;

    @Override
    public ProcessOrderResponse process(Product product) {
        if (product.getAvailable() > 0
                && (LocalDate.now().isAfter(product.getFlashSaleStartDate()) && LocalDate.now().isBefore(product.getFlashSaleEndDate()))
                && product.getFlashSaleSoldQuantity() < product.getFlashSaleMaxQuantity()
        ) {
            product.setAvailable(product.getAvailable() - 1);
            product.setFlashSaleSoldQuantity(product.getFlashSaleSoldQuantity() + 1);
            productRepository.save(product);
        }
        return new ProcessOrderResponse(product.getId());
    }
}
