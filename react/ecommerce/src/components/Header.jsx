import { ShoppingCart, Bell, User } from "lucide-react";
import "../style/style.css"
function Header() {
  return (
    <header className="header">
      <h2>🛍️ ProductMania</h2>
      <div className="iconContainer">
        <Bell />
        <ShoppingCart />
        <User />
      </div>
    </header>
  );
}

export default Header;
