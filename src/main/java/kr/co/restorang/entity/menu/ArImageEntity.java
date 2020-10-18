package kr.co.restorang.entity.menu;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ar_image")
public class ArImageEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	private String title;
	private String content;
	
	@Column(name="created_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime createDate;
	
	@EqualsAndHashCode.Exclude
	@OneToOne
	@JoinColumn(name="img_id")
	private MenuImageEntity img;
}