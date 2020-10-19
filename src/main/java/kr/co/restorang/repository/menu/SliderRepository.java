package kr.co.restorang.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.restorang.entity.menu.SliderEntity;

@Repository
public interface SliderRepository extends JpaRepository<SliderEntity, String> {

}
