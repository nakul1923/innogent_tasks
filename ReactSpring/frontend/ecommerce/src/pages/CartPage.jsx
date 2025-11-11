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
import Header from "../components/Header";

export default function CartPage() {
  const dispatch = useDispatch();
  const cartItems = useSelector(selectCartItems);
  const total = useSelector(selectCartTotal);

  if (cartItems.length === 0)
    return( 
      <>
      <Header/>
    <h2 style={{ textAlign: "center" }}>🛒 Your cart is empty</h2>;
      </>)
  return (
    <>
    <Header/>
    <div style={{ padding: "30px" }}>
      <h2>Shopping Cart</h2>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "15px",
          marginTop: "20px",
        }}
      >
        {cartItems.map((item) => (
          <div
            key={item.id}
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
              borderBottom: "1px solid #ccc",
              paddingBottom: "10px",
            }}
          >
            <div style={{ display: "flex", alignItems: "center", gap: "15px" }}>
              <img
                src={item.image}
                alt={item.title}
                width="60"
                height="60"
                style={{ objectFit: "contain" }}
              />
              <div>
                <h4>{item.title}</h4>
                <p>₹{item.price}</p>
              </div>
            </div>

            <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>
              <button onClick={() => dispatch(decreaseQty(item.id))}>-</button>
              <span>{item.quantity}</span>
              <button onClick={() => dispatch(increaseQty(item.id))}>+</button>
            </div>

            <div>
              <p>₹{(item.price * item.quantity).toFixed(2)}</p>
            </div>

            <button onClick={() => dispatch(removeFromCart(item.id))}>
              ❌
            </button>
          </div>
        ))}
      </div>

      <div
        style={{
          textAlign: "right",
          marginTop: "30px",
          fontSize: "18px",
          fontWeight: "bold",
        }}
      >
        Total: ₹{total}
      </div>

      <div style={{ textAlign: "right", marginTop: "20px" }}>
        <Link to="/checkout">
          <button
            style={{
              background: "green",
              color: "white",
              padding: "10px 20px",
              border: "none",
              borderRadius: "8px",
              cursor: "pointer",
            }}
          >
            Proceed to Checkout
          </button>
        </Link>

        <button
          style={{
            background: "gray",
            color: "white",
            padding: "10px 20px",
            border: "none",
            borderRadius: "8px",
            marginLeft: "10px",
          }}
          onClick={() => dispatch(clearCart())}
        >
          Clear Cart
        </button>
      </div>
    </div>
    </>
  );
}
