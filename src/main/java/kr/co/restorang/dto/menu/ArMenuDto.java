package kr.co.restorang.dto.menu;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import kr.co.restorang.entity.menu.MenuImageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArMenuDto {

	private String id;
	private String title;
	private String category;
	private String content;
	private int price;
	
	@Column(name="created_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime createDate;
	
	private MenuImageEntity img;
}
