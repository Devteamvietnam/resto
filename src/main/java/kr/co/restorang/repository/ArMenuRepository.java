package kr.co.restorang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.restorang.entity.ArMenuEntity;


@Repository
public interface ArMenuRepository extends JpaRepository<ArMenuEntity, String> {
	public Optional<ArMenuEntity> findById(String id);
}
