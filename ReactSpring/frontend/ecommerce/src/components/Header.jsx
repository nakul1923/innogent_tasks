import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { selectCartItems } from "../features/cart/cartSlice";

export default function Header() {
  const cartItems = useSelector(selectCartItems);
  return (
    <header
      style={{
        display: "flex",
        justifyContent: "space-between",
        padding: "15px 30px",
        background: "#222",
        color: "white",
        width: "100%",
        position: "fixed",
        zIndex: "1",
      }}
    >
      <Link to="/" style={{ color: "white", textDecoration: "none" }}>
        🛍️ ProductMania
      </Link>

      <Link
        to="/orders"
        style={{ marginLeft: "1150px", color: "white", textDecoration: "none" }}
      >
        My Orders
      </Link>

      <Link
        to="/cart"
        className="relative flex items-center gap-1 text-white font-medium hover:text-yellow-300 transition-all no-underline"
        style={{ textDecoration: "none" }}
      >
        🛒 Cart
        <span className="ml-1 bg-red-600 text-xs px-2 py-0.5 rounded-full text-white font-semibold">
          {cartItems.length <= 9 ? cartItems.length : "9+"}
        </span>
      </Link>
    </header>
  );
}
