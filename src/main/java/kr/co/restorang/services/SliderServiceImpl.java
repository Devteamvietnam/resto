package kr.co.restorang.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.restorang.entity.SliderEntity;
import kr.co.restorang.repository.SliderRepository;

@Service
public class SliderServiceImpl implements SliderService {
	
	@Autowired
	SliderRepository sliderReponsitory;
	
	@Override
	public List<SliderEntity> getSlider() {
		// TODO Auto-generated method stub
		return sliderReponsitory.findAll();
	}

	@Override
	public Optional<SliderEntity> getById(String id) {
		// TODO Auto-generated method stub
		return sliderReponsitory.findById(id);
	}

	@Override
	public SliderEntity save(SliderEntity slider) {
		// TODO Auto-generated method stub
		return sliderReponsitory.save(slider);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		sliderReponsitory.deleteById(id);
	}

}
