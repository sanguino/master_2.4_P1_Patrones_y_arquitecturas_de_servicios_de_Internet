package es.sanguino.ecommerce.infrastructure.repository;

import es.sanguino.ecommerce.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

}
