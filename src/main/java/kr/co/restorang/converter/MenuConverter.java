package kr.co.restorang.converter;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.restorang.dto.ArImageDto;
import kr.co.restorang.entity.ArImageEntity;

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
	//End
}
