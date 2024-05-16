package com.agsp.vo;

import java.math.BigDecimal;
import java.time.LocalDate;


import com.agsp.enumerator.CategoriaEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransacaoVO{
		
		private Long id;
		
		@NotBlank(message = "O prenchimento do campo título é obrigatório")
		private String titulo;
		
		@Positive(message = "O valor deve ser positivo")
		@NotNull(message = "O prenchimento do campo valor é obrigatório")
		private BigDecimal valor;
		
		@NotBlank(message = "O preenchimento do campo número do cartão é obrigatório")
		@Size(max = 16, min = 16,  message = "O campo numero deve ter 16 caracteres. Exemplo 0000.0000.0000.0000")
		private String numeroCartao;
		
		@NotNull(message = "O preenchimento do campo data transação é obrigatório")
		@PastOrPresent(message = "A data da transação não pode ser data futura")
		private LocalDate dataTransacao;
		
		@NotNull(message = "O preenchimento do campo categoria é obrigatório")
		private CategoriaEnum categoria;

}
