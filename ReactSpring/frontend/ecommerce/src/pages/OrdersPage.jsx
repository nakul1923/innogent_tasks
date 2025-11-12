import { useEffect, useState } from "react";
import api from "../services/api.js";

export default function OrdersPage() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    api
      .get("/orders/")
      .then((res) => {
        const sortedOrders = [...res.data].sort(
          (a, b) => new Date(b.orderDate) - new Date(a.orderDate)
        );
        setOrders(sortedOrders);
      })
      .catch(() => alert("Failed to load orders"));
  }, []);

  return (
    <div className="max-w-5xl mx-auto px-4 py-10 font-inter">
      <h2 className="text-3xl font-bold text-center mb-10 text-gray-800">
        My Orders
      </h2>

      {orders.length === 0 ? (
        <p className="text-center text-gray-600 text-lg">
          You haven’t placed any orders yet.
        </p>
      ) : (
        <div className="space-y-6">
          {orders.map((order) => (
            <div
              key={order.id}
              className="bg-white rounded-2xl shadow-md border border-gray-100 p-6 transition hover:shadow-lg"
            >
              {/* Header */}
              <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-4">
                <p className="font-semibold text-gray-800 text-lg">
                  🧾 Order ID:{" "}
                  <span className="text-gray-600">{order.orderId}</span>
                </p>
                <span
                  className={`mt-2 sm:mt-0 px-3 py-1 text-sm font-semibold rounded-lg ${
                    order.status === "DELIVERED"
                      ? "bg-green-100 text-green-800"
                      : order.status === "PENDING"
                      ? "bg-yellow-100 text-yellow-800"
                      : "bg-red-100 text-red-800"
                  }`}
                >
                  {order.status}
                </span>
              </div>

              {/* Order Info */}
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-2 text-gray-700 mb-4">
                <p>
                  <strong>Total:</strong> ₹{order.totalAmount}
                </p>
                <p>
                  <strong>Date:</strong>{" "}
                  {new Date(order.orderDate).toLocaleString()}
                </p>
                <p className="sm:col-span-2">
                  <strong>Address:</strong> {order.address}
                </p>
              </div>

              {/* Ordered Items */}
              <div className="bg-gray-50 rounded-xl p-4">
                <h4 className="font-semibold text-gray-800 mb-3 flex items-center gap-1">
                  🛍️ Ordered Items
                </h4>
                <ul className="divide-y divide-gray-200">
                  {order.items.map((item) => (
                    <li
                      key={item.id}
                      className="flex justify-between py-2 text-gray-700"
                    >
                      <span>
                        {item.productName} × {item.quantity}
                      </span>
                      <span>₹{(item.price * item.quantity).toFixed(2)}</span>
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
