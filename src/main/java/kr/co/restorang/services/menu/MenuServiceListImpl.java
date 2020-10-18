package kr.co.restorang.services.menu;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.restorang.entity.menu.ArImageEntity;
import kr.co.restorang.entity.menu.ArMenuEntity;
import kr.co.restorang.entity.menu.ArVideoEntity;
import kr.co.restorang.entity.menu.MenuImageEntity;
import kr.co.restorang.repository.menu.ArImageRepository;
import kr.co.restorang.repository.menu.ArMenuRepository;
import kr.co.restorang.repository.menu.ArVideoRepository;
import kr.co.restorang.repository.menu.MenuImageRepository;

@Service
public class MenuServiceListImpl implements MenuServiceList{

	@Autowired
	ArImageRepository arimageRepository;
	
	@Autowired
	ArMenuRepository armenuRepository;
	
	@Autowired
	ArVideoRepository arvideoRepository;
	
	@Autowired
	MenuImageRepository menuimageRepository;
	
//AR IMAGE
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
//END AR IMAGE
  
// AR MENU

	@Override
	public List<ArMenuEntity> loadAllArMenu() {
		return armenuRepository.findAll();
	}

	@Override
	public Optional<ArMenuEntity> getArMenu(String id) {
		return armenuRepository.findById(id);
	}

	@Override
	public ArMenuEntity saveArMenu(ArMenuEntity imgMe) {
		return armenuRepository.save(imgMe);
	}

// END AR MENU
	

// AR VIDEO
		@Override
		public List<ArVideoEntity> loadAllArVideo() {
			return arvideoRepository.findAll();
		}

		@Override
		public Optional<ArVideoEntity> getArVideo(String id) {
			return arvideoRepository.findById(id);
		}

		@Override
		public ArVideoEntity saveArVideo(ArVideoEntity imgVi) {
			return arvideoRepository.save(imgVi);
		}
		
		@Override
		public void deleteArvideo(String id) {
			arvideoRepository.deleteById(id);
			
		}
		
// END AR VIDEO
		
// Chung
		@Override
		public MenuImageEntity saveImage(MenuImageEntity img) {
			return menuimageRepository.save(img);
		}

		@Override
		public Optional<MenuImageEntity> findImageById(String id) {
			return menuimageRepository.findById(id);
		}
		@Override
		public void delete(String id) {
			armenuRepository.deleteById(id);
			
		}
// END chung

}
