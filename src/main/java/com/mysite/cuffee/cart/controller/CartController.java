package com.mysite.cuffee.cart.controller;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.service.CartService;
import com.mysite.global.exception.ServiceException;
import com.mysite.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffee")
public class CartController {

    private final CartService cartService;

    record NewCartResBody(Long cartId) {
    }

    @PostMapping("/carts")
    public RsData<NewCartResBody> createCart() {
        Cart newCart = cartService.createCart();
        Long cartId = newCart.getId();
        return new RsData<>(
                "201-1",
                "새로운 장바구니가 생성되었습니다.",
                new NewCartResBody(
                        cartId
                )
        );
    }

    record AddToCartReqBody(long cartId, long productId) {
    }
    @PostMapping("/carts/items")
    public RsData<String> addCartItem(
            @RequestBody AddToCartReqBody reqBody
    ) {
        cartService.addCartItem(reqBody.cartId(), reqBody.productId());

        return new RsData<>(
                "201-2",
                "상품이 장바구니에 추가되었습니다."
        );
    }
    @DeleteMapping("/carts/{cartId}/items/{id}")
    public RsData<String> removeCartItem(
            @PathVariable("cartId") long cartId,
            @PathVariable("id") long productId
    ) {
        cartService.removeCartItem(cartId, productId);
        return new RsData<>(
                "200-1",
                "상품이 삭제되었습니다. ID: " + productId
        );
    }

    @PostMapping("/carts/{cartId}/items/{id}/increase")
    public RsData<String> increaseItemQty(
            @PathVariable("cartId") long cartId,
            @PathVariable("id") long productId
    ) {
        cartService.increaseItemQuantity(cartId, productId);
        return new RsData<>(
                "200-2",
                "상품 수량이 1 증가되었습니다. ID: " + productId
        );
    }

    @PostMapping("/carts/{cartId}/items/{id}/decrease")
    public RsData<String> decreaseQty(
            @PathVariable("cartId") long cartId,
            @PathVariable("id") long productId
    ) {
        cartService.decreaseItemQuantity(cartId, productId);
        return new RsData<>(
                "200-3",
                "상품 수량이 1 감소되었습니다. ID: " + productId
        );
    }

    record ItemLine(Long itemId, Long productId, String name, int unitPrice, int qty, int lineTotal) {
    }

    record GetCartSummaryResBody(List<ItemLine> items, int totalAmount) {
    }

    @GetMapping("/carts/{cartId}/summary")
    @Transactional(readOnly = true)
    public RsData<GetCartSummaryResBody> getCartItem(
            @PathVariable Long cartId
    ) {
        Cart cart = cartService.findByCartId(cartId)
                .orElseThrow(() -> new ServiceException("404-1", "장바구니가 없습니다."));

        List<ItemLine> lines = cartService.getCartItems(cart).stream()
                .map(i -> new ItemLine(
                        i.getId(),
                        i.getProductId(),
                        i.getProductName(),
                        i.getUnitPrice(),
                        i.getQty(),
                        i.getUnitPrice() * i.getQty()
                ))
                .toList();

        return new RsData<>(
                "200-1",
                "장바구니 요약 입니다.",
                new GetCartSummaryResBody(
                        lines,
                        cart.totalPrice()
                )
        );
    }
}
