package com.umes4ever.firestoreserver.model;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collectionName = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@DocumentId
	private String id;
	
	private String name;
	private int price;
	
	private long createdTimestamp;
	private long updatedTimestamp;
	
	public Product(String name, int price, long createdTimestamp, long updatedTimestamp) {
		this.name = name;
		this.price = price;
		this.createdTimestamp = createdTimestamp;
		this.updatedTimestamp = updatedTimestamp;
	}
}
