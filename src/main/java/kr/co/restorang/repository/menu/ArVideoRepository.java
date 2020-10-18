package kr.co.restorang.repository.menu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.restorang.entity.menu.ArVideoEntity;


@Repository
public interface ArVideoRepository extends JpaRepository<ArVideoEntity, String> {
	public Optional<ArVideoEntity> findById(String id);
}
