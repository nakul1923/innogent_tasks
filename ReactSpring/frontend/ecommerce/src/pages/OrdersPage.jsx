import React, { useEffect, useState } from "react";
import api from "../services/api.js";
import Header from "../components/Header.jsx";

export default function OrdersPage() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    api
      .get("/orders/")
      .then((res) => setOrders(res.data))
      .catch(() => alert("Failed to load orders"));
  }, []);

  return (
    <>
      <Header />
      <div style={{ padding: "20px" }}>
        <h2>My Orders</h2>
        {orders.length === 0 && <p>No orders yet.</p>}
        {orders.map((order) => (
          <div
            key={order.id}
            style={{
              border: "1px solid #ccc",
              padding: "10px",
              margin: "10px 0",
            }}
          >
            <p>
              <strong>Order ID:</strong> {order.orderId}
            </p>
            <p>
              <strong>Status:</strong> {order.status}
            </p>
            <p>
              <strong>Total:</strong> ₹{order.totalAmount}
            </p>
            <p>
              <strong>Address:</strong> {order.address}
            </p>
            <p>
              <strong>Date:</strong>{" "}
              {new Date(order.orderDate).toLocaleString()}
            </p>
            <ul>
              {order.items.map((item) => (
                <li key={item.id}>
                  {item.productName} x {item.quantity} = ₹
                  {item.price * item.quantity}
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>
    </>
  );
}
