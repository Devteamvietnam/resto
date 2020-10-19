package kr.co.restorang.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.restorang.entity.menu.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
