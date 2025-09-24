package com.mysite.cuffee.cart.service;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.entity.CartItem;
import com.mysite.cuffee.cart.repository.CartItemRepository;
import com.mysite.cuffee.cart.repository.CartRepository;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private CoffeeRepository coffeeRepository;

    public Cart createCart(){
        Cart newCart = new Cart();
        return cartRepository.save(newCart);
    }

    public Cart getCartByCartIdOrNull(int cartId){
        return cartRepository.findById(cartId).orElse(null);
    }

    public CartItem getOrCreateItem(Cart cart, Coffee coffee) {
        return cartItemRepository.findByCartIdAndProductId(cart.getCartId(), coffee.getId())
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProductId(coffee.getId());
                    newItem.setProductName(coffee.getName());
                    newItem.setUnitPrice((int) Math.round(coffee.getPrice()));
                    newItem.setQty(0);
                    cart.getItems().add(newItem);
                    return newItem;
                });
    }

    public Cart deleteCartItemByCartItemId(Cart cart, int cartItemId){
        cartItemRepository.findByCartIdAndProductId(cart.getCartId(), cartItemId)
                .ifPresent(cartItem -> cart.getItems().remove(cartItem));
        return cart;
    }

    public int totalAmount(Cart cart){
        return cart.totalAmount();
    }

}
