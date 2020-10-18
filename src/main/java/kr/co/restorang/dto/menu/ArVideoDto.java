package kr.co.restorang.dto.menu;

import java.time.LocalDateTime;

import javax.persistence.Column;

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

	@Column(name="created_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime createDate;
	
}
