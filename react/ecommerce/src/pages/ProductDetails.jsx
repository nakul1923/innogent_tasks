import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import apiService from "../services/api";

function ProductDetails() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    fetchProduct();
  }, []);

  const fetchProduct = async () => {
    setProduct(await apiService.getProductById(id));
  };

  if (!product) return <h2>Loading...</h2>;

  return (
    <div style={{ padding: "20px" }}>
      <h2>{product.title}</h2>
      <img
        src={product.image}
        alt={product.title}
        style={{ width: "200px", height: "200px", objectFit: "contain" }}
      />
      <p><b>Category:</b> {product.category}</p>
      <p><b>Price:</b> ₹{product.price}</p>
      <p><b>Description:</b> {product.description}</p>
      <p><b>Ratings:</b> {product.rating.rate} ({product.rating.count})</p>
    </div>
  );
}

export default ProductDetails;
