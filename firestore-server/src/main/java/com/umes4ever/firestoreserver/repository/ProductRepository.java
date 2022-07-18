package com.umes4ever.firestoreserver.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import org.springframework.stereotype.Repository;

import com.umes4ever.firestoreserver.model.Product;

@Repository
public interface ProductRepository extends FirestoreReactiveRepository<Product> {
}
