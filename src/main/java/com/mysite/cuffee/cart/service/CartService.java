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

    public void removeCartItem(long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 아이템을 찾을 수 없습니다: " + id);
        }
        cartItemRepository.deleteById(id);
    }

    public void increaseItemQuantity(long id) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("장바구니에 해당 상품이 없습니다. ID: " + id));
        item.setQty(item.getQty() + 1);
    }

    public void decreaseItemQuantity(long id) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("장바구니에 해당 상품이 없습니다. ID: " + id));
        if (item.getQty() > 1) {
            item.setQty(item.getQty() - 1);
        } else {
            cartItemRepository.delete(item);
        }
    }

}
