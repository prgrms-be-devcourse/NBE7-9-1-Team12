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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private CoffeeRepository coffeeRepository;

    public Cart createCart() {
        Cart newCart = new Cart();
        return cartRepository.save(newCart);
    }

    public CartItem CreateItem(Cart cart, Coffee coffee) {
        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setProductId(coffee.getId());
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
}
