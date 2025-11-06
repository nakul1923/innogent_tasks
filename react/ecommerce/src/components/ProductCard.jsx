import { useNavigate } from "react-router-dom";
import "../style/style.css"
function ProductCard({ product }) {
  const navigate = useNavigate();

  return (
    <div
      onClick={() => navigate(`/product/${product.id}`)}
      className="productCardContainer"
    >
      <img
        src={product.image}
        alt={product.title}
        className="productCardImage"
      />
      <h4>{product.title.slice(0, 25)}...</h4>
      <p>₹{product.price}</p>
      <p>Ratings: {product.rating.rate}/5  ({product.rating.count})</p>
    </div>
  );
}

export default ProductCard;
