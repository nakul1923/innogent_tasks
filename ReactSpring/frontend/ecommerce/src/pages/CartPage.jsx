import { useDispatch, useSelector } from "react-redux";
import {
  selectCartItems,
  selectCartTotal,
  removeFromCart,
  increaseQty,
  decreaseQty,
  clearCart,
} from "../features/cart/cartSlice";
import { Link } from "react-router-dom";

export default function CartPage() {
  const dispatch = useDispatch();
  const cartItems = useSelector(selectCartItems);
  const total = useSelector(selectCartTotal);

  if (cartItems.length === 0)
    return (
      <div className="flex flex-col items-center justify-center h-[70vh] text-center">
        <h2 className="text-2xl font-semibold text-gray-700">
          🛒 Your cart is empty
        </h2>
        <Link
          to="/"
          className="mt-4 bg-blue-600 text-white px-5 py-2 rounded-lg hover:bg-blue-700 transition-all"
        >
          Continue Shopping
        </Link>
      </div>
    );

  return (
    <div className="max-w-5xl mx-auto px-5 py-10">
      <h2 className="text-3xl font-bold text-gray-800 mb-8 text-center">
        Shopping Cart
      </h2>

      <div className="flex flex-col gap-6">
        {cartItems.map((item) => (
          <div
            key={item.id}
            className="flex flex-col sm:flex-row items-center justify-between border border-gray-200 rounded-xl p-4 shadow-sm hover:shadow-md transition-all bg-white"
          >
            {/* Product Info */}
            <div className="flex items-center gap-5 w-full sm:w-1/3">
              <img
                src={item.image}
                alt={item.title}
                className="w-20 h-20 object-contain rounded-md"
              />
              <div>
                <h4 className="font-semibold text-gray-800">{item.title}</h4>
                <p className="text-green-600 font-medium">₹{item.price}</p>
              </div>
            </div>

            {/* Quantity Controls */}
            <div className="flex items-center gap-3 mt-3 sm:mt-0">
              <button
                onClick={() => dispatch(decreaseQty(item.id))}
                className="bg-gray-200 hover:bg-gray-300 text-lg px-3 py-1 rounded-full"
              >
                −
              </button>
              <span className="font-semibold text-gray-700">
                {item.quantity}
              </span>
              <button
                onClick={() => dispatch(increaseQty(item.id))}
                className="bg-gray-200 hover:bg-gray-300 text-lg px-3 py-1 rounded-full"
              >
                +
              </button>
            </div>

            {/* Item Total */}
            <div className="font-semibold text-gray-800">
              ₹{(item.price * item.quantity).toFixed(2)}
            </div>

            {/* Remove Button */}
            <button
              onClick={() => dispatch(removeFromCart(item.id))}
              className="text-red-500 hover:text-red-600 text-xl mt-3 sm:mt-0"
            >
              ❌
            </button>
          </div>
        ))}
      </div>

      {/* Summary Section */}
      <div className="flex flex-col sm:flex-row justify-between items-center mt-10 border-t border-gray-300 pt-6">
        <div className="text-xl font-semibold text-gray-800 mb-4 sm:mb-0">
          Total: <span className="text-green-600">₹{total}</span>
        </div>

        <div className="flex gap-3">
          <Link to="/checkout">
            <button className="bg-green-600 hover:bg-green-700 text-white px-6 py-2 rounded-lg font-medium transition-all">
              Proceed to Checkout
            </button>
          </Link>

          <button
            onClick={() => dispatch(clearCart())}
            className="bg-gray-400 hover:bg-gray-500 text-white px-6 py-2 rounded-lg font-medium transition-all"
          >
            Clear Cart
          </button>
        </div>
      </div>
    </div>
  );
}
