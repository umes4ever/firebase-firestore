package com.umes4ever.firestoreserver.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.umes4ever.firestoreserver.model.Product;
import com.umes4ever.firestoreserver.repository.ProductRepository;
import com.umes4ever.firestoreserver.service.FirestoreServiceInterface;
import com.umes4ever.firestoreserver.utils.DataGenerator;

import reactor.core.publisher.Flux;

@Service
public class FirestoreServiceImpl implements FirestoreServiceInterface {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Firestore firestore;

	@Override
	public void addTestDataToFirestore() {
		UUID uuid = UUID.randomUUID();
		long currentTimestamp = Instant.now().toEpochMilli();
		Product product = new Product(uuid.toString(), DataGenerator.getRandomString(10),
				DataGenerator.getRandomNumber(5), currentTimestamp, currentTimestamp);
		productRepository.save(product).block();
	}

	@Override
	public void removeRecentTestDataFromFirestore() {
		ApiFuture<QuerySnapshot> future = firestore.collection("products")
				.orderBy("createdTimestamp", Direction.DESCENDING).get();
		try {
			List<QueryDocumentSnapshot> documents = future.get().getDocuments();
			DocumentReference docRef = documents.get(0).getReference();
			docRef.delete().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product getTestDataFromFirestoreById(String id) {
		return productRepository.findById(id).block();
	}

	@Override
	public List<Product> getAllTestDataFromFirestore() {
		Flux<Product> productFlux = productRepository.findAll();
		return productFlux.collectList().block();
	}

	@Override
	public void updateRecentTestDataFromFirestore() {
		ApiFuture<QuerySnapshot> future = firestore.collection("products")
				.orderBy("createdTimestamp", Direction.DESCENDING).get();
		try {
			List<QueryDocumentSnapshot> documents = future.get().getDocuments();
			DocumentReference docRef = documents.get(0).getReference();

			long currentTimestamp = Instant.now().toEpochMilli();
			docRef.update("name", DataGenerator.getRandomString(10), "price", DataGenerator.getRandomNumber(5),
					"updatedTimestamp", currentTimestamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
