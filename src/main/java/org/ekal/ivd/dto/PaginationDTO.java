/**
 * 24-Mar-2024
 * @Author LALIT
 * 
 */
package org.ekal.ivd.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * 
 */

@Data
@JsonSerialize
@JsonAutoDetect
@JsonInclude(Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PaginationDTO<T> {
	long totalElements;

	int totalPages;

	int currentPage;

	List<T> data;

	public PaginationDTO(Page<T> page) {

		if (page.hasContent()) {

			this.totalElements = page.getTotalElements();
			this.totalPages = page.getTotalPages();
			this.currentPage = page.getNumber();
			this.data = page.getContent();
		}
	}

	public PaginationDTO(Page<T> page, List<T> data) {

		if (page.hasContent()) {

			this.totalElements = page.getTotalElements();
			this.totalPages = page.getTotalPages();
			this.currentPage = page.getNumber();
			this.data = data;
		}
	}
}
