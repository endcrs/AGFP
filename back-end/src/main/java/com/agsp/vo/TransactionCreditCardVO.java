package com.agsp.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.agsp.enumerator.CategoriaEnum;
import com.agsp.enumerator.StatusEnum;
import com.agsp.enumerator.TipoTransacaoEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionCreditCardVO implements Serializable {
	
	private static final long serialVersionUID = -4081569234866318693L;
	
	private Long id;
	
	@NotNull(message = "O preenchimento do campo ID cartão é obrigatório")
	private Long idCartao;
	
	@NotNull(message = "O preenchimento do campo categoria é obrigatório")
	private CategoriaEnum categoria;
	
	private TipoTransacaoEnum tipo;
	
	@NotNull(message = "O preenchimento do título é obrigatório")
	private String titulo;
	
	private StatusEnum status;
	
	@NotNull(message = "O preenchimento do campo valor é obrigatório")
	@Positive(message = "Valor deve ser um valor positivo")
	private BigDecimal valor;
	
	@NotNull(message = "O preenchimento do campo a n~umero parcelas é obrigatório")
	private Integer numeroParcelas;
	
}
