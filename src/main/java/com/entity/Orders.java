package com.entity;

import jakarta.persistence.*;	
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY for MySQL auto-increment
	private Long id;
	
	@Column(name = "userId", nullable = false)
	private Long userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp created;
	
	@Column(nullable = false)
	private Double amount;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrdersDetails> orderDetails;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", userId=" + userId + ", created=" + created + ", amount=" + amount + "]";
	}
}
