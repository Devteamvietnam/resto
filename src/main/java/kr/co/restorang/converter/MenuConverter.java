package kr.co.restorang.converter;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.restorang.dto.menu.ArImageDto;
import kr.co.restorang.dto.menu.ArMenuDto;
import kr.co.restorang.dto.menu.ArVideoDto;
import kr.co.restorang.entity.menu.ArImageEntity;
import kr.co.restorang.entity.menu.ArMenuEntity;
import kr.co.restorang.entity.menu.ArVideoEntity;

public class MenuConverter extends BaseConverter {

	private static MenuConverter instance;
	
	public static MenuConverter getInstance() {
		if(instance == null) {
			instance = new MenuConverter();
		}
		return instance;
	}
	private MenuConverter() {};
	
	// ARImage
	public ArImageDto arImageEntityToDto (ArImageEntity arimageEntity) {
		ArImageDto arimageDto = mapper.map(arimageEntity, ArImageDto.class);
		return arimageDto;
	}
	public List<ArImageDto> arimageEntityToDtoList(List<ArImageEntity> list) {
		return list.stream().map(b -> { return arImageEntityToDto(b); }).collect(Collectors.toList());
	}
	
	public ArImageEntity arimageDtoToEntity(ArImageDto arimageDto) {
		ArImageEntity aEn = mapper.map(arimageDto, ArImageEntity.class);
		return aEn;
	}
	//End ARIMAGE
	
	// ARMENU
	public ArMenuDto arMenuEntityToDto (ArMenuEntity armenuEntity) {
		ArMenuDto armenuDto = mapper.map(armenuEntity, ArMenuDto.class);
		return armenuDto;
	}
	public List<ArMenuDto> armenuEntityToDtoList(List<ArMenuEntity> list) {
		return list.stream().map(b -> { return arMenuEntityToDto(b); }).collect(Collectors.toList());
	}
	
	public ArMenuEntity armenuDtoToEntity(ArMenuDto armenuDto) {
		ArMenuEntity mEn = mapper.map(armenuDto, ArMenuEntity.class);
		return mEn;
	}
	//END ARMENU
	
	// AR VIDEO
	public ArVideoDto arVideoEntityToDto (ArVideoEntity arvideoEntity) {
		ArVideoDto arvideoDto = mapper.map(arvideoEntity, ArVideoDto.class);
		return arvideoDto;
	}
	public List<ArVideoDto> arvideoEntityToDtoList(List<ArVideoEntity> list) {
		return list.stream().map(b -> { return arVideoEntityToDto(b); }).collect(Collectors.toList());
	}
	
	public ArVideoEntity arvideoDtoToEntity(ArVideoDto arvideoDto) {
		ArVideoEntity vEn = mapper.map(arvideoDto, ArVideoEntity.class);
		return vEn;
	}
	// END AR VIDEO
}
