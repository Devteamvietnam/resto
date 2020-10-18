package kr.co.restorang.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.restorang.entity.ArImageEntity;
import kr.co.restorang.entity.MenuImageEntity;
import kr.co.restorang.repository.ArImageRepository;
import kr.co.restorang.repository.MenuImageRepository;

@Service
public class MenuServiceListImpl implements MenuServiceList{

	@Autowired
	ArImageRepository arimageRepository;
	
	@Autowired
	MenuImageRepository menuimageRepository;
	@Override
	public List<ArImageEntity> loadAllArImage() {
		return arimageRepository.findAll();
	}

	@Override
	public Optional<ArImageEntity> getArImage(String id) {
		return arimageRepository.findById(id);
	}


	@Override
	public ArImageEntity saveArImage(ArImageEntity arimage) {
		return arimageRepository.save(arimage);
	}
	@Override
	public void delete(String id) {
		arimageRepository.deleteById(id);
		
	}

	@Override
	public MenuImageEntity saveImage(MenuImageEntity img) {
		return menuimageRepository.save(img);
	}

	@Override
	public Optional<MenuImageEntity> findImageById(String id) {
		return menuimageRepository.findById(id);
	}



}
