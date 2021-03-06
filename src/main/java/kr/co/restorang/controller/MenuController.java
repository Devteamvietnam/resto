package kr.co.restorang.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.restorang.converter.MenuConverter;
import kr.co.restorang.dto.menu.ArImageDto;
import kr.co.restorang.dto.menu.ArMenuDto;
import kr.co.restorang.dto.menu.ArVideoDto;
import kr.co.restorang.entity.menu.ArImageEntity;
import kr.co.restorang.entity.menu.ArMenuEntity;
import kr.co.restorang.entity.menu.ArVideoEntity;
import kr.co.restorang.entity.menu.MenuImageEntity;
import kr.co.restorang.entity.menu.ProductEntity;
import kr.co.restorang.entity.menu.SliderEntity;
import kr.co.restorang.exceptionhandler.WebappException;
import kr.co.restorang.services.menu.MenuServiceList;

@CrossOrigin(origins = "*", maxAge = 3600)
@Service
@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

	public static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	MenuServiceList menuService;
	@Autowired
	ObjectMapper mapper;

// ------------------------Begin Slider|| Product-List--------------------------//

//--------------Slider-Menu------------//

//	Load slider
	@GetMapping(value = "/slider")
	public ResponseEntity<?> getAllSlider() {
		logger.info("load slider");
		List<SliderEntity> listslider = menuService.getSlider();
		return new ResponseEntity<List<SliderEntity>>(listslider, HttpStatus.OK);
	}

// Insert slider
	@PostMapping(value = "/insertslider")
	public ResponseEntity<?> insertSlider(@RequestParam("data") String sliderData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("insert slider");
		SliderEntity sliderrequest = mapper.readValue(sliderData, SliderEntity.class);
		if (file != null) {
			saveImgSlider(file, sliderrequest);
		}
		menuService.saveSlider(sliderrequest);
		return new ResponseEntity<SliderEntity>(sliderrequest, HttpStatus.OK);
	}

//	update slider
	@PutMapping(value = "/updateslider/{sliId}")
	public ResponseEntity<?> updateSldier(@PathVariable("sliId") String sliId, @RequestParam("data") String sliderData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("update slider");
		SliderEntity sliderrequest = mapper.readValue(sliderData, SliderEntity.class);
		final SliderEntity sli = menuService.getSliderById(sliId).get();
		sli.setTitle(sliderrequest.getTitle());
		sli.setContent(sliderrequest.getContent());
		sli.setCreatedatetime(sliderrequest.getCreatedatetime());
		if (file != null) {
			saveImgSlider(file, sli);
		}
		menuService.saveSlider(sli);
		return new ResponseEntity<SliderEntity>(sli, HttpStatus.OK);
	}

//	delete slider
	@DeleteMapping(value = "/deleteslider/{sliId}")
	public ResponseEntity<?> deleteSlider(@PathVariable String sliId) {
		logger.info("delete services");
		menuService.deleteSlider(sliId);
		return new ResponseEntity<String>("delete successfull", HttpStatus.OK);
	}

	
//--------------Product-List------------//

//	load product
	@GetMapping(value = "/product")
	public ResponseEntity<?> getAllProduct() {
		logger.info("load product");
		List<ProductEntity> listproduct = menuService.getProduct();
		return new ResponseEntity<List<ProductEntity>>(listproduct, HttpStatus.OK);
	}

//  insert product
	@PostMapping("/insertproduct")
	public ResponseEntity<?> insertProduct(@RequestParam("data") String productData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("insert product");
		ProductEntity productrequest = mapper.readValue(productData, ProductEntity.class);
		if (file != null) {
			saveImgProduct(file, productrequest);
		}
		menuService.saveProduct(productrequest);
		return new ResponseEntity<ProductEntity>(productrequest, HttpStatus.OK);
	}

//	update product
	@PutMapping(value = "/updateproduct/{proId}")
	public ResponseEntity<?> updateProduct(@PathVariable("proId") String proId,
			@RequestParam("data") String productData, @RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("update product");
		ProductEntity productrequset = mapper.readValue(productData, ProductEntity.class);
		final ProductEntity pro = menuService.geProducttById(proId).get();
		pro.setTitle(productrequset.getTitle());
		pro.setCategory(productrequset.getCategory());
		pro.setContent(productrequset.getContent());
		pro.setPrice(productrequset.getPrice());
		pro.setCreatedatetime(productrequset.getCreatedatetime());
		if (file != null) {
			saveImgProduct(file, pro);
		}
		menuService.saveProduct(pro);
		return new ResponseEntity<ProductEntity>(pro, HttpStatus.OK);
	}

// Delete Product
	@DeleteMapping(value = "/deleteproduct/{proId}")
	public ResponseEntity<?> DeleteProduct(@PathVariable("proId") String proId) {
		logger.info("delete product");
		menuService.deleteProduct(proId);
		return new ResponseEntity<String>("delete successfull", HttpStatus.OK);
	}

//------ save image slider--------//
	public void saveImgSlider(MultipartFile file, SliderEntity AEn) {
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}

		try {
			MenuImageEntity imgArimage;
			if (AEn.getImg() == null) {
				MenuImageEntity imgEn = new MenuImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgArimage = menuService.saveImage(imgEn);
				AEn.setImg(imgArimage);
			} else {
				imgArimage = AEn.getImg();
				imgArimage.setFileName(name);
				imgArimage.setFileType(file.getContentType());
				imgArimage.setData(file.getBytes());
				imgArimage = menuService.saveImage(imgArimage);
				AEn.setImg(imgArimage);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

//------ save image Product-menu --------//
	public void saveImgProduct(MultipartFile file, ProductEntity AEn) {
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}

		try {
			MenuImageEntity imgArimage;
			if (AEn.getImg() == null) {
				MenuImageEntity imgEn = new MenuImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgArimage = menuService.saveImage(imgEn);
				AEn.setImg(imgArimage);
			} else {
				imgArimage = AEn.getImg();
				imgArimage.setFileName(name);
				imgArimage.setFileType(file.getContentType());
				imgArimage.setData(file.getBytes());
				imgArimage = menuService.saveImage(imgArimage);
				AEn.setImg(imgArimage);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

//	-----------------------End Slider || Product-List--------------------------//

	// AR IMAGE
	@GetMapping(value = "/arimage/list")
	public ResponseEntity<?> loadAllArImage() throws WebappException {
		logger.info("Load All ArImage");
		List<ArImageDto> arimageList = MenuConverter.getInstance().arimageEntityToDtoList(menuService.loadAllArImage());
		return new ResponseEntity<List<ArImageDto>>(arimageList, HttpStatus.OK);

	}

	@GetMapping(value = "/arimage/{arimageId}")
	public ResponseEntity<?> getArimageById(@PathVariable String arimageId) {
		logger.info("Loading Arimage by Id: ", arimageId);
		ArImageDto arimageDto = MenuConverter.getInstance().arImageEntityToDto(menuService.getArImage(arimageId).get());
		return new ResponseEntity<ArImageDto>(arimageDto, HttpStatus.OK);
	}

	@PutMapping(value = "/arimage/update/{arimageId}")
	public ResponseEntity<?> updateArImage(@PathVariable("arimageId") String arimageId,
			@RequestParam(name = "data") String arimageData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("Update Arimage by id");
		ArImageDto arimageResquest = mapper.readValue(arimageData, ArImageDto.class);
		final ArImageEntity AEn = menuService.getArImage(arimageId).get();
		AEn.setTitle(arimageResquest.getTitle());
		AEn.setContent(arimageResquest.getContent());
		AEn.setCreatedDate(arimageResquest.getCreatedDate());
		if (file != null) {
			saveArImage(file, AEn);
		}
		menuService.saveArImage(AEn);
		ArImageDto arimage = MenuConverter.getInstance().arImageEntityToDto(AEn);
		return new ResponseEntity<ArImageEntity>(AEn, HttpStatus.OK);

	}

	// update image
	public void saveArImage(MultipartFile file, ArImageEntity AEn) {
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}

		try {
			MenuImageEntity imgArimage;
			if (AEn.getImg() == null) {
				MenuImageEntity imgEn = new MenuImageEntity();
				imgEn.setFileName(name);
				imgEn.setFileType(file.getContentType());
				imgEn.setData(file.getBytes());
				imgArimage = menuService.saveImage(imgEn);
				AEn.setImg(imgArimage);
			} else {
				imgArimage = AEn.getImg();
				imgArimage.setFileName(name);
				imgArimage.setFileType(file.getContentType());
				imgArimage.setData(file.getBytes());
				imgArimage = menuService.saveImage(imgArimage);
				AEn.setImg(imgArimage);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	@PostMapping(value = "/arimage/insert")
	public ResponseEntity<?> InsertArImage(@RequestParam(name = "data") String arimageData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("arimage insert");
		ArImageDto arimageRequest = mapper.readValue(arimageData, ArImageDto.class);
		ArImageEntity aEn = MenuConverter.getInstance().arimageDtoToEntity(arimageRequest);
		if (file != null) {
			saveArImage(file, aEn);
		}
		menuService.saveArImage(aEn);
		ArImageDto arimage = MenuConverter.getInstance().arImageEntityToDto(aEn);
		return new ResponseEntity<ArImageDto>(arimage, HttpStatus.OK);
	}

	@DeleteMapping(value = "/arimage/delete/{id}")
	public ResponseEntity<?> deleteArimage(@PathVariable String id) {
		logger.info("delete Arimage");
		menuService.deleteArImage(id);

		return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);

	}
	// END AR IMAGE

	// AR Menu
	@GetMapping(value = "/armenu/list")
	public ResponseEntity<?> loadAllArMenu() throws WebappException {
		logger.info("Load All ArImage");
		List<ArMenuDto> armenuList = MenuConverter.getInstance().armenuEntityToDtoList(menuService.loadAllArMenu());
		return new ResponseEntity<List<ArMenuDto>>(armenuList, HttpStatus.OK);

	}

	@GetMapping(value = "/armenu/{armenuId}")
	public ResponseEntity<?> getArmenuById(@PathVariable String armenuId) {
		logger.info("Loading Armenu by Id: ", armenuId);
		ArMenuDto armenuDto = MenuConverter.getInstance().arMenuEntityToDto(menuService.getArMenu(armenuId).get());
		return new ResponseEntity<ArMenuDto>(armenuDto, HttpStatus.OK);
	}

	@PutMapping(value = "/armenu/update/{armenuId}")
	public ResponseEntity<?> updateArMenu(@PathVariable("armenuId") String armenuId,
			@RequestParam(name = "data") String armenuData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("Update Armenu by id");
		ArMenuDto armenuResquest = mapper.readValue(armenuData, ArMenuDto.class);
		final ArMenuEntity aME = menuService.getArMenu(armenuId).get();
		aME.setTitle(armenuResquest.getTitle());
		aME.setContent(armenuResquest.getContent());
		aME.setCategory(armenuResquest.getCategory());
		aME.setPrice(armenuResquest.getPrice());
		if (file != null) {
			saveArMenu(file, aME);
		}
		menuService.saveArMenu(aME);
		ArMenuDto arimage = MenuConverter.getInstance().arMenuEntityToDto(aME);
		return new ResponseEntity<ArMenuEntity>(aME, HttpStatus.OK);

	}

	// update image
	public void saveArMenu(MultipartFile file, ArMenuEntity aME) {
		String name = StringUtils.cleanPath(file.getOriginalFilename());
		if (name.contains("..")) {
			logger.error("error for path of file");
		}

		try {
			MenuImageEntity imgArmenu;
			if (aME.getImg() == null) {
				MenuImageEntity imgMe = new MenuImageEntity();
				imgMe.setFileName(name);
				imgMe.setFileType(file.getContentType());
				imgMe.setData(file.getBytes());
				imgArmenu = menuService.saveImage(imgMe);
				aME.setImg(imgArmenu);
			} else {
				imgArmenu = aME.getImg();
				imgArmenu.setFileName(name);
				imgArmenu.setFileType(file.getContentType());
				imgArmenu.setData(file.getBytes());
				imgArmenu = menuService.saveImage(imgArmenu);
				aME.setImg(imgArmenu);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	@PostMapping(value = "/armenu/insert")
	public ResponseEntity<?> InsertArMenu(@RequestParam(name = "data") String armenuData,
			@RequestParam(name = "file", required = false) MultipartFile file)
			throws JsonMappingException, JsonProcessingException {
		logger.info("armenu insert");
		ArMenuDto armenuRequest = mapper.readValue(armenuData, ArMenuDto.class);
		ArMenuEntity MEn = MenuConverter.getInstance().armenuDtoToEntity(armenuRequest);
		if (file != null) {
			saveArMenu(file, MEn);
		}
		menuService.saveArMenu(MEn);
		ArMenuDto armenu = MenuConverter.getInstance().arMenuEntityToDto(MEn);
		return new ResponseEntity<ArMenuDto>(armenu, HttpStatus.OK);
	}

	@DeleteMapping(value = "/armenu/delete/{id}")
	public ResponseEntity<?> deleteArmenu(@PathVariable String id) {
		logger.info("delete Armenu");
		menuService.deleteArMenu(id);

		return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);

	}
	// END AR MENU

	// AR VIDEO
	@GetMapping(value = "/arvideo/list")
	public ResponseEntity<?> loadAllArVideo() throws WebappException {
		logger.info("Load All ArVideo");
		List<ArVideoDto> arvideoList = MenuConverter.getInstance().arvideoEntityToDtoList(menuService.loadAllArVideo());
		return new ResponseEntity<List<ArVideoDto>>(arvideoList, HttpStatus.OK);

	}

	@GetMapping(value = "/arvideo/{arvideoId}")
	public ResponseEntity<?> getArvideoById(@PathVariable String arvideoId) {
		logger.info("Loading Arvideo by Id: ", arvideoId);
		ArVideoDto arvideoDto = MenuConverter.getInstance().arVideoEntityToDto(menuService.getArVideo(arvideoId).get());
		return new ResponseEntity<ArVideoDto>(arvideoDto, HttpStatus.OK);
	}

	@PutMapping(value = "/arvideo/update/{arvideoId}")
	public ResponseEntity<?> updateArVideo(@PathVariable("arvideoId") String arvideoId,
			@RequestParam(name = "data") String arvideoData) throws JsonMappingException, JsonProcessingException {
		logger.info("Update Arvideo by id");
		ArVideoDto arvideoResquest = mapper.readValue(arvideoData, ArVideoDto.class);
		final ArVideoEntity vME = menuService.getArVideo(arvideoId).get();
		vME.setTitle(arvideoResquest.getTitle());
		vME.setContent(arvideoResquest.getContent());
		vME.setLink(arvideoResquest.getLink());
		menuService.saveArVideo(vME);
		ArVideoDto arvideo = MenuConverter.getInstance().arVideoEntityToDto(vME);
		return new ResponseEntity<ArVideoEntity>(vME, HttpStatus.OK);

	}

	@PostMapping(value = "/arvideo/insert")
	public ResponseEntity<?> InsertArMenu(@RequestParam(name = "data") String arvideoData)
			throws JsonMappingException, JsonProcessingException {
		logger.info("arvideo insert");
		ArVideoDto arvideoRequest = mapper.readValue(arvideoData, ArVideoDto.class);
		ArVideoEntity VEn = MenuConverter.getInstance().arvideoDtoToEntity(arvideoRequest);
		menuService.saveArVideo(VEn);
		ArVideoDto arvideo = MenuConverter.getInstance().arVideoEntityToDto(VEn);
		return new ResponseEntity<ArVideoDto>(arvideo, HttpStatus.OK);
	}

	@DeleteMapping(value = "/arvideo/delete/{id}")
	public ResponseEntity<?> deleteArVideo(@PathVariable String id) {
		logger.info("delete Arvideo");
		menuService.deleteArvideo(id);
		return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);

	}

// END AR VIDEO
	
//	Load type image
	@GetMapping(value = "/viewJPG/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPGImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {
		
		logger.info("get JPG Image");
		MenuImageEntity img = menuService.findImageById(fileId).get();
		InputStream inputStream =  new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	
	@GetMapping(value = "/viewJPEG/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getJPEGImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {
		
		logger.info("get JPEG Image");
		MenuImageEntity img = menuService.findImageById(fileId).get();
		InputStream inputStream =  new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	
	@GetMapping(value = "/viewPNG/{fileId}", produces = MediaType.IMAGE_PNG_VALUE)
	public void getPNGImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {
		
		logger.info("get PNG Image");
		MenuImageEntity img = menuService.findImageById(fileId).get();
		InputStream inputStream =  new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
	
	@GetMapping(value = "/viewGIF/{fileId}", produces = MediaType.IMAGE_GIF_VALUE)
	public void getGIFImage(HttpServletResponse response, @PathVariable String fileId) throws IOException {
		
		logger.info("get GIF Image");
		MenuImageEntity img = menuService.findImageById(fileId).get();
		InputStream inputStream =  new ByteArrayInputStream(img.getData());
		response.setContentType(MediaType.IMAGE_GIF_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
	}
}
