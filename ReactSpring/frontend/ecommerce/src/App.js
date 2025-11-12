import './App.css';
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom';
import ProductsPage from './pages/ProductPage.jsx';
import CartPage from './pages/CartPage.jsx';
import CheckoutPage from './pages/CheckoutPage.jsx';
import OrdersPage from './pages/OrdersPage.jsx';
import Header from './components/Header.jsx';
import ProductDetailPage from './pages/ProductDetailPage.jsx';

function App() {
  return (
    <BrowserRouter>
      <Header/>
      <div style={{paddingTop:"40px"}}>
      <Routes>
        <Route path="/" element={<ProductsPage />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/checkout" element={<CheckoutPage />} />
        <Route path="/orders" element={<OrdersPage />} />
        <Route path="/product/:id" element={<ProductDetailPage />} />
      </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
