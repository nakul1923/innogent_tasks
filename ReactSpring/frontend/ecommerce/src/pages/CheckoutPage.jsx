import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  selectCartItems,
  selectCartTotal,
  clearCart,
  setPromo,
  clearPromo,
} from "../features/cart/cartSlice.js";
import api from "../services/api.js";

export default function CheckoutPage() {
  const dispatch = useDispatch();
  const cartItems = useSelector(selectCartItems);
  const total = useSelector(selectCartTotal);
  const [address, setAddress] = useState("");
  const [promo, setPromoCode] = useState("");
  const [discount, setDiscount] = useState(0);
  const [loading, setLoading] = useState(false);
  const [orderPlaced, setOrderPlaced] = useState(false);

    const handleValidatePromo = async () => {
  try {
    // Validate promo from backend
    const { data } = await api.get(`/promocode/validate/${promo}`);

    if (!data || data.discountPercentage === undefined || data.discountPercentage === null) {
      throw new Error("Invalid promo data");
    }

    // Example: data.discountPercentage = 10
    const discountPercent = data.discountPercentage;

    setDiscount(discountPercent);

    if (!discountPercent) throw new Error("Invalid promo data");
    // Calculate discount in rupees based on total amount in cart
    const discountAmount = (total * discountPercent) / 100;

    // Dispatch promo to Redux (for reference)
    dispatch(setPromo({
      code: promo,
      discountPercent,
      discountAmount
    }));

    alert(`Promo applied! You got ${discountPercent}% off (₹${discountAmount.toFixed(2)})`);
  } catch (err) {
    alert("❌ Invalid or expired promo code");
    setDiscount(0);
    dispatch(clearPromo());
  }
};

const handlePlaceOrder = async () => {
  if (!address) return alert("Please enter delivery address!");

  setLoading(true);
  try {
    const finalAmount = total - (total * discount) / 100;

    const orderData = {
      totalAmount: finalAmount,
      address,
      items: cartItems.map((item) => ({
        productId: item.id,
        quantity: item.quantity,
        price: item.price,
      })),
      promoCode: promo || null,
    };

    await api.post("/orders/", orderData);

    setOrderPlaced(true);
    dispatch(clearCart());
    dispatch(clearPromo());
  } catch (err) {
    alert("❌ Order placement failed!");
  } finally {
    setLoading(false);
  }
};

  if (orderPlaced)
    return (
      <div style={{ textAlign: "center", marginTop: "100px" }}>
        <h2>🎉 Order Placed Successfully!</h2>
        <p>Thank you for shopping with us.</p>
      </div>
    );

  return (
    <div style={{ padding: "30px", maxWidth: "600px", margin: "auto" }}>
      <h2>Checkout</h2>

      <div style={{ marginTop: "20px" }}>
        <label>Delivery Address:</label>
        <textarea
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          rows={3}
          style={{ width: "100%", marginTop: "8px" }}
        />
      </div>

      <div style={{ marginTop: "20px" }}>
        <label>Promo Code:</label>
        <div style={{ display: "flex", gap: "10px", marginTop: "8px" }}>
          <input
            type="text"
            value={promo}
            onChange={(e) => setPromoCode(e.target.value)}
            placeholder="Enter promo code"
            style={{ flex: 1 }}
          />
          <button onClick={handleValidatePromo}>Apply</button>
        </div>
      </div>

      <div style={{ marginTop: "20px" }}>
        <p>Subtotal: ₹{total.toFixed(2)}</p>
        {discount > 0 && (
          <p style={{ color: "green" }}>
            Discount ({discount}%): -₹{((total * discount) / 100).toFixed(2)}
          </p>
        )}
        <h3>Total Payable: ₹{(total - (total * discount) / 100).toFixed(2)}</h3>
      </div>

      <button
        onClick={handlePlaceOrder}
        style={{
          background: "green",
          color: "white",
          padding: "10px 20px",
          border: "none",
          borderRadius: "8px",
          cursor: "pointer",
          marginTop: "20px",
          width: "100%",
        }}
        disabled={loading}
      >
        {loading ? "Placing Order..." : "Place Order"}
      </button>
    </div>
  );
}
