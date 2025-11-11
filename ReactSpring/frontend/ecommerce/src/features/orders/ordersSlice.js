import { createSlice } from "@reduxjs/toolkit";


const initialState = {
  list: [],
  loading: false,
  error: null,
};

const ordersSlice = createSlice({
  name: "orders",
  initialState,
  reducers: {
    addOrderLocal: (state, action) => {
      state.list.push(action.payload);
    },
    clearOrders: (state) => {
      state.list = [];
    },
  },
});

export const { addOrderLocal, clearOrders } = ordersSlice.actions;
export default ordersSlice.reducer;