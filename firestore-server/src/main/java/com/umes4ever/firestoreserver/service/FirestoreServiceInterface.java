package com.umes4ever.firestoreserver.service;

import java.util.List;

import com.umes4ever.firestoreserver.model.Product;

public interface FirestoreServiceInterface {
	void addTestDataToFirestore();
	void removeRecentTestDataFromFirestore();
	Product getTestDataFromFirestoreById(String id);
	List<Product> getAllTestDataFromFirestore();
	void updateRecentTestDataFromFirestore();
}
