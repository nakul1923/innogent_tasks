import { useDispatch } from "react-redux";
import { addToCart } from "../features/cart/cartSlice";
import { Link } from "react-router-dom";

export default function ProductCard({ product }) {
  const dispatch = useDispatch();

  const handleAdd = () => {
    dispatch(
      addToCart({
        productId: product.id,
        id: product.id,
        title: product.name,
        price: product.price,
        image: product.imageUrl,
      })
    );
  };

  return (
    <div
      className="bg-white border border-gray-200 rounded-2xl shadow-sm hover:shadow-xl hover:-translate-y-2 transform transition-all duration-300 p-4 flex flex-col items-center text-center cursor-pointer"
      style={{ width: "230px" }}
    >
      <Link
        to={`/product/${product.id}`}
        style={{ textDecoration: "none", color: "black" }}
      >
        {/* Product Image */}
        <div className="h-40 flex items-center justify-center mb-3">
          <img
            src={product.imageUrl}
            alt={product.name}
            className="h-full object-contain transition-transform duration-300 hover:scale-105"
          />
        </div>

        {/* Product Name */}
        <h4 className="font-semibold text-gray-800 text-sm mb-1 truncate">
          {product.name}
        </h4>

        {/* Product Price */}
        <p className="text-green-600 font-bold text-lg mb-3">
          ₹{product.price.toFixed(2)}
        </p>
      </Link>
      {/* Add to Cart Button */}

      <button
        onClick={handleAdd}
        className="bg-blue-600 hover:bg-blue-700 text-white text-sm font-medium py-2 px-4 rounded-full transition-all duration-200 w-full shadow-sm hover:shadow-md"
      >
        🛒 Add to Cart
      </button>
    </div>
  );
}
