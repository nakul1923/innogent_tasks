import { configureStore } from "@reduxjs/toolkit";
import cartReducer from "../features/cart/cartSlice.js";
import ordersReducer from "../features/orders/ordersSlice.js";
import productsReducer from "../features/products/productsSlice.js"
function loadState(){

    try {
        const serialized = localStorage.getItem("cart_state");
        if(!serialized) return undefined;
        return {cart: JSON.parse(serialized)};
    } catch (error) {
        
        console.warn("Failed to load state from local storage", error);
        return undefined;
    }
}

const preloadedState = loadState();

export const store = configureStore({

    reducer:{
        
        cart: cartReducer,
        orders:ordersReducer,
        products: productsReducer,
    },
    preloadedState
})

store.subscribe(()=>{

    try {
        const state = store.getState();
        const toSave = state.cart;
        localStorage.setItem("cart_state",JSON.stringify(toSave));
    } catch (error) {
        
        console.warn("Failed to save state to local storage", error)
    }
})