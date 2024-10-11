package com.agsp.vo;

import lombok.Builder;

@Builder
public record AccountOwnerVO(
		
		Long id,
		String nome
		
		) {

}
