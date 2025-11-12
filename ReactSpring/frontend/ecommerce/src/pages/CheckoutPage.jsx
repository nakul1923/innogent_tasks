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

      if (
        !data ||
        data.discountPercentage === undefined ||
        data.discountPercentage === null
      ) {
        throw new Error("Invalid promo data");
      }

      // Example: data.discountPercentage = 10
      const discountPercent = data.discountPercentage;

      setDiscount(discountPercent);

      if (!discountPercent) throw new Error("Invalid promo data");
      // Calculate discount in rupees based on total amount in cart
      const discountAmount = (total * discountPercent) / 100;

      // Dispatch promo to Redux (for reference)
      dispatch(
        setPromo({
          code: promo,
          discountPercent,
          discountAmount,
        })
      );

      alert(
        `Promo applied! You got ${discountPercent}% off (₹${discountAmount.toFixed(
          2
        )})`
      );
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
      <div className="flex flex-col items-center justify-center min-h-[70vh] text-center">
        <h2 className="text-3xl font-bold text-green-600 mb-3">
          🎉 Order Placed Successfully!
        </h2>
        <p className="text-gray-600 text-lg">Thank you for shopping with us.</p>
      </div>
    );

  // ✅ Checkout page layout
  return (
    <div className="max-w-lg mx-auto p-8 bg-white rounded-2xl shadow-lg mt-10 border border-gray-100">
      <h2 className="text-3xl font-bold text-center text-gray-800 mb-8">
        Checkout
      </h2>

      {/* Address Section */}
      <div className="mb-6">
        <label className="block text-gray-700 font-medium mb-2">
          Delivery Address
        </label>
        <textarea
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          rows={3}
          className="w-full border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-green-500 focus:outline-none resize-none"
          placeholder="Enter your complete address..."
        />
      </div>

      {/* Promo Code Section */}
      <div className="mb-6">
        <label className="block text-gray-700 font-medium mb-2">
          Promo Code
        </label>
        <div className="flex gap-3">
          <input
            type="text"
            value={promo}
            onChange={(e) => setPromoCode(e.target.value)}
            placeholder="Enter promo code"
            className="flex-1 border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-400 focus:outline-none"
          />
          <button
            onClick={handleValidatePromo}
            className="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-5 py-2 rounded-lg transition-all"
          >
            Apply
          </button>
        </div>
      </div>

      {/* Summary Section */}
      <div className="bg-gray-50 rounded-lg p-5 mb-6 border border-gray-200">
        <p className="flex justify-between text-gray-700 mb-2">
          <span>Subtotal:</span> <span>₹{total.toFixed(2)}</span>
        </p>
        {discount > 0 && (
          <p className="flex justify-between text-green-600 mb-2">
            <span>Discount ({discount}%):</span>{" "}
            <span>-₹{((total * discount) / 100).toFixed(2)}</span>
          </p>
        )}
        <hr className="my-3" />
        <h3 className="flex justify-between text-xl font-bold text-gray-800">
          <span>Total Payable:</span>{" "}
          <span>₹{(total - (total * discount) / 100).toFixed(2)}</span>
        </h3>
      </div>

      {/* Place Order Button */}
      <button
        onClick={handlePlaceOrder}
        disabled={loading}
        className={`w-full py-3 text-lg font-semibold rounded-lg transition-all ${
          loading
            ? "bg-gray-400 cursor-not-allowed text-white"
            : "bg-green-600 hover:bg-green-700 text-white"
        }`}
      >
        {loading ? "Placing Order..." : "Place Order"}
      </button>
    </div>
  );
}
