package kr.co.restorang.repository.menu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.restorang.entity.menu.ArImageEntity;


@Repository
public interface ArImageRepository extends JpaRepository<ArImageEntity, String> {
	public Optional<ArImageEntity> findById(String id);
}
