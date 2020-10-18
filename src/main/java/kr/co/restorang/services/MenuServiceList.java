package kr.co.restorang.services;

import java.util.List;
import java.util.Optional;

import kr.co.restorang.entity.ArImageEntity;
import kr.co.restorang.entity.ArMenuEntity;
import kr.co.restorang.entity.MenuImageEntity;

public interface MenuServiceList {
 // AR IMAGE
	List<ArImageEntity> loadAllArImage();
	Optional <ArImageEntity> getArImage( String id);
	ArImageEntity saveArImage(ArImageEntity imgEn);
// Chung
	MenuImageEntity saveImage(MenuImageEntity img);
	Optional<MenuImageEntity> findImageById(String id);
	void delete(String id);
// AR Menu
	List<ArMenuEntity> loadAllArMenu();
	Optional <ArMenuEntity> getArMenu( String id);
	ArMenuEntity saveArMenu(ArMenuEntity imgMe);
}
