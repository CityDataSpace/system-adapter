package de.fraunhofer.dataspaces.iese.systemadapter.model.mysql;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import de.fraunhofer.dataspaces.iese.systemadapter.encryption.AesEncryptionValues;

@Entity(name="payloads")
@Table(schema="systemadapter")
public class Payload {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="headerId")
	private UUID headerId;
	
	
	@Column(name="data", length=10000)
	@Convert(converter = AesEncryptionValues.class)
	private String data;

	public Payload() {
		
	}

	public Payload(int id, UUID headerId, String data) {
		this.id = id;
		this.headerId = headerId;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UUID getHeaderId() {
		return headerId;
	}

	public void setHeaderId(UUID headerId) {
		this.headerId = headerId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Payload [id=" + id + ", headerId=" + headerId.toString() + ", data=" + data + "]";
	}
	
}
