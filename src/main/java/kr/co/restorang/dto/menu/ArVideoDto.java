package kr.co.restorang.dto.menu;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArVideoDto {

	private String id;
	private String title;
	private String link;
	private String content;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private String createdDate;
	
}
