import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import api from "../../services/api";

export const fetchProducts = createAsyncThunk("products/fetch", async () => {
  const res = await api.get("/products/"); // if using backend
  // or use: const res = await fetch("https://fakestoreapi.com/products");
  // return await res.json();
  console.log(res.data);
  
  return res.data;
});

const productsSlice = createSlice({
  name: "products",
  initialState: {
    all: [],
    loading: false,
    error: null,
    categories: [],
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchProducts.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchProducts.fulfilled, (state, action) => {
        state.loading = false;
        state.all = action.payload;

        // extract unique categories
        const cats = [...new Set(action.payload.map((p) => p.category))];
        state.categories = cats;
      })
      .addCase(fetchProducts.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message;
      });
  },
});

export default productsSlice.reducer;

export const selectAllProducts = (state) => state.products.all;
export const selectCategories = (state) => state.products.categories;
export const selectProductLoading = (state) => state.products.loading;
