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
		List<ArImageEntity> listArImage = menuService.loadAllArImage();
		
		return new ResponseEntity<List<ArImageEntity>>(listArImage, HttpStatus.OK);
		
	}
	@PutMapping(value="/arimage/update/{arimageId}")
	public ResponseEntity<?> updateArImage(@PathVariable("arimageId") String arimageId, @RequestParam(name="data") String arimageData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException{
		logger.info("Update Arimage by id");
		ArImageEntity arimageResquest = mapper.readValue(arimageData, ArImageEntity.class);
		final ArImageEntity AEn = menuService.getArImage(arimageId).get();
		AEn.setTitle(arimageResquest.getTitle());
		AEn.setContent(arimageResquest.getContent());
		if(file != null) {
			saveArImage(file, AEn);
		}
		menuService.saveArImage(AEn);
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
		public ResponseEntity<?> InsertArImage(@RequestParam(name="data") String arimageData, @RequestParam(name = "file", required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
			logger.info("arimage insert");
			ArImageEntity arimageRequest = mapper.readValue(arimageData, ArImageEntity.class);
				
			if(file != null) {
				saveArImage(file,arimageRequest);
			}
			menuService.saveArImage(arimageRequest);
			return new ResponseEntity<ArImageEntity>(arimageRequest, HttpStatus.OK);
		}
		@DeleteMapping(value ="/delete/{id}")
		public ResponseEntity<?> deleteTeam(@PathVariable String id){
			logger.info("delete Arimage");
			menuService.delete(id);
			
			return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
			
		}
}
