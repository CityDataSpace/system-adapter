package de.fraunhofer.dataspaces.iese.systemadapter.model.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="payloads")
@Table(schema="systemadapter")
public class Payload {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private int headerId;
	
	@Column(name="data")
	private String data;

	public Payload() {
		
	}

	public Payload(int id, int headerId, String data) {
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

	public int getHeaderId() {
		return headerId;
	}

	public void setHeaderId(int headerId) {
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
		return "Payload [id=" + id + ", headerId=" + headerId + ", data=" + data + "]";
	}
	
}
