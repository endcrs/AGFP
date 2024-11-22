package com.agsp.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.agsp.enumerator.StatusEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionCurrentAccountResponseVO implements Serializable {
	
	private static final long serialVersionUID = -4081569234866318693L;
	
	private Long id;
	private StatusEnum status;
	private String titulo;
	private BigDecimal valor;
	private DominioVO tipo;
	private ZonedDateTime dataTransacao;
	private DominioVO categoria;
	private AccountVO conta;
	private String transacao;
	private Long contaId;
	private Long cartaoId;
}
