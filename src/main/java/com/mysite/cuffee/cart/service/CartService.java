package com.mysite.cuffee.cart.service;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.entity.CartItem;
import com.mysite.cuffee.cart.repository.CartItemRepository;
import com.mysite.cuffee.cart.repository.CartRepository;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import com.mysite.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CoffeeRepository coffeeRepository;

    public Cart createCart() {
        Cart newCart = new Cart();
        return cartRepository.save(newCart);
    }

    public CartItem CreateItem(Cart cart, Coffee coffee) {
        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setProductId(coffee.getCoffeeId());
        newItem.setProductName(coffee.getName());
        newItem.setUnitPrice(coffee.getPrice());
        newItem.setQty(1);
        cart.getItems().add(newItem);
        return newItem;
    }

    public int totalPrice(Cart cart) {
        return cart.totalPrice();
    }

    public Optional<Cart> findByCartId(Long cartId) {
        return cartRepository.findById(cartId);
    }

    public List<CartItem> getCartItems(Cart cart) {
        return cart.getItems();
    }

    // 장바구니에 상품 추가 (체크표시)
    public void addCartItem(long cartId, long productId) {
        cartItemRepository.findByCartIdAndProductId(cartId, productId).ifPresent(item -> {
            throw new IllegalStateException("이미 장바구니에 담긴 상품입니다.");
        });

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니가 존재하지 않습니다. ID: " + cartId));

        Coffee coffee = coffeeRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 커피가 존재하지 않습니다. ID: " + productId));

        CartItem newItem = CreateItem(cart, coffee);

        cartItemRepository.save(newItem);
    }

    // 장바구니 아이템 삭제 (체크표시 해제)
    public void removeCartItem(long cartId, long productId) {
        CartItem item = cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템을 찾을 수 없습니다. ID: " + productId));

        cartItemRepository.delete(item);
    }

    // 장바구니 아이템 수량 증가
    public void increaseItemQuantity(long productId) {
        CartItem item = cartItemRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니에 해당 상품이 없습니다. ID: " + productId));
        item.setQty(item.getQty() + 1);
    }

    // 장바구니 아이템 수량 감소
    public void decreaseItemQuantity(long productId) {
        CartItem item = cartItemRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니에 해당 상품이 없습니다. ID: " + productId));
        if (item.getQty() > 1) {
            item.setQty(item.getQty() - 1);
        } else {
            cartItemRepository.delete(item);
        }
    }

}
