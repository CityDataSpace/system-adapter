package de.fraunhofer.dataspaces.iese.systemadapter.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fraunhofer.dataspaces.iese.systemadapter.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
