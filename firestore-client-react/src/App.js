import React, { useState, useEffect } from "react";
import "./App.css";

import firestore from "./firestore";
import { collection, orderBy, query, onSnapshot } from "firebase/firestore";

function App() {
  const [productData, setProductData] = useState([]);

  useEffect(() => {
    const q = query(
      collection(firestore, "products"),
      orderBy("createdTimestamp", "desc")
    );
    onSnapshot(q, (querySnapshot) => {
      const products = [];
      querySnapshot.forEach((doc) => {
        products.push(doc.data());
      });
      setProductData(products);
    });
  }, []);

  return (
    <div>
      <ul>
        {productData.map((data) => {
          return (
            <li key={data.name}>
              <span>{data.name}</span>
            </li>
          );
        })}
      </ul>
    </div>
  );
}

export default App;
