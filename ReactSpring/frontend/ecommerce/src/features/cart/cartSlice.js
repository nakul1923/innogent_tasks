import { createSlice } from "@reduxjs/toolkit";

const initialState = {

    items:[],
    promoCode: null,
    discount: 0, 
}

// const calcTotals = (items) =>{

//     const totalQuantity = items.reduce((s, it)=> s + it.quantity, 0);
//     const totalAmount = items.reduce((s, it)=> s + it.quantity * it.price, 0);

//     return {totalQuantity,totalAmount};
// };

const cartSlice = createSlice({

    name:"cart",
    initialState,
    reducers:{
        addToCart: (state,action)=>{

            const item = action.payload;
            const existing = state.items.find((i) => i.id === item.id);
            if(existing){

                existing.quantity += 1;
                
            } else{

                state.items.push({ ...item, quantity: 1});
            }
        },

    removeFromCart: (state, action) => {
      // payload: productId
      state.items = state.items.filter((it) => it.productId !== action.payload);
    },
    increaseQty: (state, action) => {
      const item = state.items.find((it) => it.productId === action.payload);
      if (item) item.quantity += 1;
    },
    decreaseQty: (state, action) => {
      const item = state.items.find((it) => it.productId === action.payload);
      if (item && item.quantity > 1) item.quantity -= 1;
    },
    clearCart: (state) => {
      state.items = [];
    },
    setCart: (state, action) => {
      // useful for initializing from server or replacing
      state.items = action.payload || [];
    },
    setPromo: (state, action) => {
    const { code, discount } = action.payload;
    state.promoCode = code;
    state.discount = discount;
    },
    clearPromo: (state) => {
    state.promoCode = null;
    state.discount = 0;
    },
    }
});

export const {addToCart,removeFromCart,increaseQty,decreaseQty,clearCart,setCart,setPromo,clearPromo} = cartSlice.actions;

export default cartSlice.reducer;
export const selectCartItems = (state) => state.cart.items;
export const selectCartTotal = (state) =>
  state.cart.items.reduce((sum, it) => sum + it.price * it.quantity, 0);
// export const selectCartTotal = (state) => calcTotals(state.cart.items);