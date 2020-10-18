package kr.co.restorang.converter;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.restorang.dto.ArImageDto;
import kr.co.restorang.dto.ArMenuDto;
import kr.co.restorang.entity.ArImageEntity;
import kr.co.restorang.entity.ArMenuEntity;

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
}
