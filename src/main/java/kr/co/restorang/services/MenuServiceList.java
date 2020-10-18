package kr.co.restorang.services;

import java.util.List;
import java.util.Optional;

import kr.co.restorang.entity.ArImageEntity;
import kr.co.restorang.entity.MenuImageEntity;

public interface MenuServiceList {
 // AR IMAGE
	List<ArImageEntity> loadAllArImage();
	Optional <ArImageEntity> getArImage( String id);
	ArImageEntity saveArImage(ArImageEntity imgEn);
	void delete(String id);
	MenuImageEntity saveImage(MenuImageEntity img);
	
	Optional<MenuImageEntity> findImageById(String id);
}
