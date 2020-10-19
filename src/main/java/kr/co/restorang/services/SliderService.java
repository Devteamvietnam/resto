package kr.co.restorang.services;

import java.util.List;
import java.util.Optional;

import kr.co.restorang.entity.SliderEntity;

public interface SliderService {
	List<SliderEntity> getSlider();
	Optional<SliderEntity> getById(String id);
	SliderEntity save(SliderEntity slider);
	void delete(String id);

}
