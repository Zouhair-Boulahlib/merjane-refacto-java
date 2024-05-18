package com.nimbleways.springboilerplate.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ProductTypes {
    NORMAL("NORMAL"),
    SEASONAL("SEASONAL"),
    EXPIRABLE("EXPIRABLE"),
    FLASHSALE("FLASHSALE");

    private final String productType;

    public boolean isEqualTo(String productType){
        return this.productType.equalsIgnoreCase(productType);
    }

    public static ProductTypes of(String productType){
        return Arrays.stream(ProductTypes.values()).filter(t -> t.isEqualTo(productType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product Type Not supported"));
    }
}
