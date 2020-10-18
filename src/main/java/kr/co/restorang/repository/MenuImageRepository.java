package kr.co.restorang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.restorang.entity.MenuImageEntity;


public interface MenuImageRepository extends JpaRepository<MenuImageEntity, String>{
	Optional<MenuImageEntity> findById(String id);
}
