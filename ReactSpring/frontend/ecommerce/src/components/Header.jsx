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
      }}
    >
      <Link to="/" style={{ color: "white", textDecoration: "none" }}>
        🛍️ MyShop
      </Link>

      <Link to="/orders" style={{ marginLeft:"1150px", color: "white", textDecoration: "none" }}>My Orders</Link>

      <Link to="/cart" style={{ color: "white", textDecoration: "none" }}>
        Cart ({cartItems.length})
      </Link>
    </header>
  );
}
