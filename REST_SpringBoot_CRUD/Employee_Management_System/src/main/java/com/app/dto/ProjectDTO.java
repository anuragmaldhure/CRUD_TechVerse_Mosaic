package com.app.dto;

import java.time.LocalDate;

import com.app.entities.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ProjectDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	private String title;

	private LocalDate startDate;

	private LocalDate endDate;

	private ProjectStatus status;

}
