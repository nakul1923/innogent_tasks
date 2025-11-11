// src/features/orders/orderSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../services/api.js"; // ensure you create api.js later

// placeholder - you'll implement thunks later
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
  extraReducers: (builder) => {
    // add thunks handlers here later
  },
});

export const { addOrderLocal, clearOrders } = ordersSlice.actions;
export default ordersSlice.reducer;