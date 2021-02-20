package es.sanguino.ecommerce.infrastructure.repository;

import es.sanguino.ecommerce.infrastructure.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {

}
