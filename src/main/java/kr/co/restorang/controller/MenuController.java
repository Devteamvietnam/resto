package kr.co.restorang.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
import kr.co.restorang.dto.ArImageDto;
import kr.co.restorang.entity.ArImageEntity;
import kr.co.restorang.entity.MenuImageEntity;
import kr.co.restorang.exceptionhandler.WebappException;
import kr.co.restorang.services.MenuServiceList;


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
	
	@GetMapping(value="/arimage/list")
	public ResponseEntity<?> loadAllTeam() throws WebappException {
		logger.info("Load All ArImage");
		List <ArImageDto> arimageList = MenuConverter.getInstance().arimageEntityToDtoList(menuService.loadAllArImage());
		return new ResponseEntity<List<ArImageDto>>(arimageList, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/arimage/{arimageId}")
	public ResponseEntity<?> getArimageById(@PathVariable String arimageId) {
		logger.info("Loading Arimage by Id: ", arimageId);
		ArImageDto arimageDto = MenuConverter.getInstance().arImageEntityToDto(menuService.getArImage(arimageId).get());
		return new ResponseEntity<ArImageDto>(arimageDto, HttpStatus.OK);
	}
	
	@PutMapping(value="/arimage/update/{arimageId}")
	public ResponseEntity<?> updateArImage(@PathVariable("arimageId") String arimageId, @RequestParam(name="data") String arimageData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException{
		logger.info("Update Arimage by id");
		ArImageDto arimageResquest = mapper.readValue(arimageData, ArImageDto.class);
		final ArImageEntity AEn = menuService.getArImage(arimageId).get();
		AEn.setTitle(arimageResquest.getTitle());
		AEn.setContent(arimageResquest.getContent());
		if(file != null) {
			saveArImage(file, AEn);
		}
		menuService.saveArImage(AEn);
		ArImageDto arimage = MenuConverter.getInstance().arImageEntityToDto(AEn);
		return new ResponseEntity<ArImageEntity>(AEn, HttpStatus.OK);
	
	}
	//update image
		public void saveArImage(MultipartFile file, ArImageEntity AEn) {
			String name = StringUtils.cleanPath(file.getOriginalFilename());
			if(name.contains("..")) {
				logger.error("error for path of file");
			}
			
			try {
				MenuImageEntity imgArimage;
				if( AEn.getImg() == null) {
					MenuImageEntity imgEn= new MenuImageEntity();
					imgEn.setFileName(name);
					imgEn.setFileType(file.getContentType());
					imgEn.setData(file.getBytes());
					imgArimage = menuService.saveImage(imgEn);
					AEn.setImg(imgArimage);
				}
				else {
					imgArimage = AEn.getImg(); 
					imgArimage.setFileName(name);
					imgArimage.setFileType(file.getContentType());
					imgArimage.setData(file.getBytes());
					imgArimage = menuService.saveImage(imgArimage);
					AEn.setImg(imgArimage);
				}
			} 
			catch (IOException e) {
				logger.error(e.toString());
			}
		}
		@PostMapping(value = "/arimage/insert")
		public ResponseEntity<?> InsertArImage(@RequestParam(name="data") String arimageData, 
				@RequestParam(name = "file", required = false) MultipartFile file) 
						throws JsonMappingException, JsonProcessingException {
			logger.info("arimage insert");
			ArImageDto arimageRequest = mapper.readValue(arimageData, ArImageDto.class);
			ArImageEntity aEn = MenuConverter.getInstance().arimageDtoToEntity(arimageRequest);
			if(file != null) {
				saveArImage(file,aEn);
			}
			menuService.saveArImage(aEn);
			ArImageDto arimage = MenuConverter.getInstance().arImageEntityToDto(aEn);
			return new ResponseEntity<ArImageDto>(arimage, HttpStatus.OK);
		}
		@DeleteMapping(value ="/arimage/delete/{id}")
		public ResponseEntity<?> deleteTeam(@PathVariable String id){
			logger.info("delete Arimage");
			menuService.delete(id);
			
			return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
			
		}
}
