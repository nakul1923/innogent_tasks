import { useDispatch } from "react-redux";
import { addToCart } from "../features/cart/cartSlice";

export default function ProductCard({ product }) {
  const dispatch = useDispatch();

  const handleAdd = () => {
    dispatch(addToCart({
      productId: product.id,
      id: product.id,
      title: product.name,
      price: product.price,
      image: product.imageUrl
    }));
  };

  return (
    <div style={{ border: "1px solid #ccc", padding: 10, width: 200 }}>
      <img src={product.imageUrl} alt={product.name} style={{ height: 100, objectFit: "contain" }} />
      <h4>{product.name}</h4>
      <p>₹{product.price}</p>
      <button onClick={handleAdd}>Add to cart</button>
    </div>
  );
}
