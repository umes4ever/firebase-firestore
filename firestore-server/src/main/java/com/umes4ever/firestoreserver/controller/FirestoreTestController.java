package com.umes4ever.firestoreserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umes4ever.firestoreserver.model.Product;
import com.umes4ever.firestoreserver.service.FirestoreServiceInterface;

@RestController
@RequestMapping("/test")
public class FirestoreTestController {

	@Autowired
	private FirestoreServiceInterface firestoreServiceInterface;

	@PostMapping("/firestore")
	public ResponseEntity<String> addTestDataToFirestore() {
		firestoreServiceInterface.addTestDataToFirestore();
		return ResponseEntity.ok("Added !");
	}

	@DeleteMapping("/firestore")
	public ResponseEntity<String> removeRecentlyAddedDataFromFirestore() {
		firestoreServiceInterface.removeRecentTestDataFromFirestore();
		return ResponseEntity.ok("Removed !");
	}

	@GetMapping("/firestore/all")
	public ResponseEntity<List<Product>> getAllTestDataFromFirestore() {
		return ResponseEntity.ok(firestoreServiceInterface.getAllTestDataFromFirestore());
	}

	@GetMapping("/firestore")
	public ResponseEntity<Product> getAllTestDataFromFirestore(@RequestParam("id") String id) {
		return ResponseEntity.ok(firestoreServiceInterface.getTestDataFromFirestoreById(id));
	}

	@PutMapping("/firestore")
	public ResponseEntity<String> updateRecentTestDataInFirestore() {
		firestoreServiceInterface.updateRecentTestDataFromFirestore();
		return ResponseEntity.ok("Updated !");
	}
}
