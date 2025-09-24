package com.mysite.cuffee.cart.controller;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.entity.CartItem;
import com.mysite.cuffee.cart.service.CartService;
import com.mysite.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffee")
public class CartController {

    private final CartService cartService;

    record NewCartResBody(
            int CartId
    ) {}

    @PostMapping("/carts")
    public RsData<NewCartResBody> createCart() {
        Cart newCart = cartService.createCart();
        int cartId = newCart.getCartId();
        return new RsData<>(
                "201-1",
                "새로운 장바구니가 생성되었습니다.",
                    new NewCartResBody(
                            cartId
                    )
        );
    }

    record GetCartItemReqBody(
            int CartId
    ) {
    }

    record GetCartItemResBody(
            List<CartItem> cartItems
    ) {
    }

    @GetMapping("/summary")
    public RsData<GetCartItemResBody> getCartItem(
            @RequestBody GetCartItemReqBody reqBody
    ) {
        Cart cart = cartService.findByCartId(reqBody.CartId()).get();
        List<CartItem> cartItems = cartService.getCartItems(cart);
        return new RsData<>(
                "201-1",
                "장바구니 아이템 입니다.",
                new GetCartItemResBody(
                        cartItems
                )
        );
    }

}
