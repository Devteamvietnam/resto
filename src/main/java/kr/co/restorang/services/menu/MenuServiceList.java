package kr.co.restorang.services.menu;

import java.util.List;
import java.util.Optional;

import kr.co.restorang.entity.menu.ArImageEntity;
import kr.co.restorang.entity.menu.ArMenuEntity;
import kr.co.restorang.entity.menu.ArVideoEntity;
import kr.co.restorang.entity.menu.MenuImageEntity;

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
// AR VIDEO
	List<ArVideoEntity> loadAllArVideo();
	Optional <ArVideoEntity> getArVideo( String id);
	ArVideoEntity saveArVideo(ArVideoEntity imgVi);
	void deleteArvideo(String id);
	
}