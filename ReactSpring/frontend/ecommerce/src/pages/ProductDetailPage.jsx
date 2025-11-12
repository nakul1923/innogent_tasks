import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { addToCart } from "../features/cart/cartSlice.js";
import {
  fetchProducts,
  selectAllProducts,
} from "../features/products/productsSlice.js";

export default function ProductDetailPage() {
  const { id } = useParams();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const products = useSelector(selectAllProducts);
  const [product, setProduct] = useState(null);

  useEffect(() => {
    if (products.length === 0) {
      dispatch(fetchProducts());
    }
  }, [dispatch, products]);

  useEffect(() => {
    const found = products.find((p) => String(p.id) === id);
    setProduct(found);
  }, [products, id]);

  if (!product)
    return (
      <div className="flex justify-center items-center min-h-[60vh] text-gray-600 text-lg">
        Loading product details...
      </div>
    );

  const handleAddToCart = () => {
    dispatch(addToCart(product));
  };

  return (
    <div className="max-w-6xl mx-auto px-6 py-10 font-inter">
      <button
        onClick={() => navigate(-1)}
        className="mb-6 text-blue-600 hover:text-blue-800 transition"
      >
        ← Back to Products
      </button>

      <div className="grid md:grid-cols-2 gap-10 items-center bg-white shadow-lg rounded-2xl p-6 border border-gray-100">
        {/* Product Image */}
        <div className="flex justify-center">
          <img
            src={product.imageUrl}
            alt={product.name}
            className="w-80 h-80 object-contain rounded-lg shadow-sm"
          />
        </div>

        {/* Product Info */}
        <div>
          <h1 className="text-3xl font-bold text-gray-800 mb-4">
            {product.name}
          </h1>
          <p className="text-gray-600 mb-3 text-sm uppercase tracking-wide">
            Category: <span className="font-medium">{product.category}</span>
          </p>
          <p className="text-gray-700 mb-6">{product.description}</p>

          <div className="flex items-center justify-between mb-6">
            <p className="text-2xl font-semibold text-green-600">
              ₹{product.price.toFixed(2)}
            </p>
          </div>

          <button
            onClick={handleAddToCart}
            className="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 rounded-xl transition"
          >
            🛒 Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
}
