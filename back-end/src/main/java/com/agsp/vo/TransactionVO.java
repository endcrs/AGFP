package com.agsp.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.agsp.enumerator.CategoriaEnum;
import com.agsp.enumerator.StatusEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionVO implements Serializable {
	
	private static final long serialVersionUID = -4081569234866318693L;
	
	private Long id;
	
	@NotNull(message = "O preenchimento do campo ID conta é obrigatório")
	private Long idConta;
	
	@NotNull(message = "O preenchimento do campo categoria é obrigatório")
	private CategoriaEnum categoria;
	
	private StatusEnum status;
	
	@NotNull(message = "O preenchimento do campo valor é obrigatório")
	@Positive(message = "Valor deve ser um valor positivo")
	private BigDecimal valor;

}
