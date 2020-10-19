package kr.co.restorang.services.menu;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.restorang.entity.menu.ArImageEntity;
import kr.co.restorang.entity.menu.ArMenuEntity;
import kr.co.restorang.entity.menu.ArVideoEntity;
import kr.co.restorang.entity.menu.MenuImageEntity;
import kr.co.restorang.entity.menu.ProductEntity;
import kr.co.restorang.entity.menu.SliderEntity;
import kr.co.restorang.repository.menu.ArImageRepository;
import kr.co.restorang.repository.menu.ArMenuRepository;
import kr.co.restorang.repository.menu.ArVideoRepository;
import kr.co.restorang.repository.menu.MenuImageRepository;
import kr.co.restorang.repository.menu.ProductRepository;
import kr.co.restorang.repository.menu.SliderRepository;

@Service
public class MenuServiceListImpl implements MenuServiceList{
	
//	---Begin slider and product---//
	@Autowired
	SliderRepository sliderRepository;
	@Autowired
	ProductRepository productRepository;
//	---End slider and product---//
	
	@Autowired
	ArImageRepository arimageRepository;
	
	@Autowired
	ArMenuRepository armenuRepository;
	
	@Autowired
	ArVideoRepository arvideoRepository;
	
	@Autowired
	MenuImageRepository menuimageRepository;

//----------------Begin slider and product----------------//
   //slider
	@Override
	public List<SliderEntity> getSlider() {
		// TODO Auto-generated method stub
		return sliderRepository.findAll();
	}

	@Override
	public Optional<SliderEntity> getSliderById(String id) {
		// TODO Auto-generated method stub
		return sliderRepository.findById(id);
	}

	@Override
	public SliderEntity saveSlider(SliderEntity slider) {
		// TODO Auto-generated method stub
		return sliderRepository.save(slider);
	} 
	
	@Override
	public void deleteSlider(String id) {
		// TODO Auto-generated method stub
		sliderRepository.deleteById(id);
	}
	
   //product
	@Override
	public List<ProductEntity> getProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Optional<ProductEntity> geProducttById(String id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id);
	}

	@Override
	public ProductEntity saveProduct(ProductEntity product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}
	@Override
	public void deleteProduct(String id) {
		productRepository.deleteById(id);
		
	}
//---------------End Slider and Product--------------//
	

	
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
