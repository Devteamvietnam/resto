package kr.co.restorang.repository.menu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.restorang.entity.menu.MenuImageEntity;


public interface MenuImageRepository extends JpaRepository<MenuImageEntity, String>{
	Optional<MenuImageEntity> findById(String id);
}
