package edu.kh.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Spring EL이 DTO 객체를 출력할 때 getter가 필요함 (필수!)
// -> ${Student.name} == ${Student.getName}

@Data				// Getter + Setter + ToString
@NoArgsConstructor	// 기본 생성자
@AllArgsConstructor // 모든필드 초기화용 매개변수 생성자
public class Student {

	private String studentNo; // 학생 번호
	private String name;	  // 이름
	private int age;		  // 나이
	
}
