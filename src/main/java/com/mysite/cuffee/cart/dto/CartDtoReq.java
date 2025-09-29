package com.mysite.cuffee.cart.dto;


public class CartDtoReq {
    public record NewCartResBody(
            Long cartId
    ) {}

    public record AddToCartReqBody(
            long cartId,
            long productId
    ) {}
}
