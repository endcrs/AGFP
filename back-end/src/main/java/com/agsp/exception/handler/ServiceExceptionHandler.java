package com.agsp.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.agsp.exception.DadosJaCadastradosException;
import com.agsp.exception.FiltrosInvalidosException;
import com.agsp.exception.MsgException;
import com.agsp.exception.NaoEncontradoException;
import com.agsp.vo.ExceptionResponseVO;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String HEADER_MESSAGE = "mensagem";

	private static final String TITLE_ERRO_REGRA_NEGOCIO = "Regra de negócio";
	private static final String TITLE_PARAMETROS_INVALIDOS = "Parâmetros inválidos";
	private static final String TITLE_DADOS_JA_CADASTRADOS = "Dados já cadastrados";
	private static final String TITLE_ERRO_SERVIDOR = "Erro no servidor";
//	private static final String TITLE_NAO_AUTORIZADO = "Não autorizado";
	private static final String TITLE_NAO_ENCONTRADO = "Registro não encontrado";
//	private static final String TITLE_DADOS_INVALIDOS = "Dados inválidos";

	private static final String TYPE_VALIDACAO_REGRA_NEGOCIO = "Validação de regras de negócio";
	private static final String TYPE_VALIDACAO_PARAMETROS = "Validação de Parâmetros";
	private static final String TYPE_DADOS_JA_CADASTRADOS = "Dados já cadastrados";
	private static final String TYPE_ERRO_INESPERADO = "Erro inesperado";
//	private static final String TYPE_NAO_AUTORIZADO = "Não autorizado";
	private static final String TYPE_NAO_ENCONTRADO = "Registro não encontrado";
//	private static final String TYPE_DADOS_INVALIDOS= "Um ou mais campos estão inválidos";


	@ExceptionHandler(MsgException.class)
	public ResponseEntity<Object> handleDadosJaCadastradosException(MsgException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_ERRO_REGRA_NEGOCIO,
				TYPE_VALIDACAO_REGRA_NEGOCIO, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}

//	@ExceptionHandler(ParametroInvalidoException.class)
//	public ResponseEntity<Object> handleParametroInvalidoException(ParametroInvalidoException e, ServletWebRequest request) {
//		logger.warn(e.getMessage());
//
//		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_PARAMETROS_INVALIDOS,
//				TYPE_VALIDACAO_PARAMETROS, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());
//
//		HttpHeaders header = new HttpHeaders();
//		header.add(HEADER_MESSAGE, e.getMessage());
//
//		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
//	}

	@ExceptionHandler(DadosJaCadastradosException.class)
	public ResponseEntity<Object> handleDadosJaCadastradosException(DadosJaCadastradosException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_DADOS_JA_CADASTRADOS,
				TYPE_DADOS_JA_CADASTRADOS, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<Object> handleNaoEncontradoException(NaoEncontradoException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_NAO_ENCONTRADO,
				TYPE_NAO_ENCONTRADO, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, NOT_FOUND, request);
	}

//	@ExceptionHandler(NaoAutorizadoException.class)
//	public ResponseEntity<Object> handleNaoAutorizadoException(NaoAutorizadoException e, ServletWebRequest request) {
//		logger.warn(e.getMessage());
//
//		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_NAO_AUTORIZADO,
//				TYPE_NAO_AUTORIZADO, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());
//
//		HttpHeaders header = new HttpHeaders();
//		header.add(HEADER_MESSAGE, e.getMessage());
//
//		return handleExceptionInternal(e, bodyExceptionResponse, header, UNAUTHORIZED, request);
//	}

	@ExceptionHandler(FiltrosInvalidosException.class)
	public ResponseEntity<Object> handleFiltrosInvalidosException(FiltrosInvalidosException e, ServletWebRequest request) {
		logger.warn(e.getMessage());

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_PARAMETROS_INVALIDOS,
				TYPE_VALIDACAO_PARAMETROS, Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, BAD_REQUEST, request);
	}

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
//		logger.warn(e.getMessage());
//
//		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_PARAMETROS_INVALIDOS,
//				TYPE_VALIDACAO_PARAMETROS, e.getAllErrors().stream()
//				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()),
//				request.getDescription(false).replace("uri=", ""));
//
//		HttpHeaders header = new HttpHeaders();
//		header.add(HEADER_MESSAGE, e.getObjectName());
//
//		return handleExceptionInternal(e, bodyExceptionResponse, header, HttpStatus.BAD_REQUEST, request);
//	}

//	@Override
//	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
//			WebRequest request) {
//
//		return handleValidationInternal(ex, headers, request, ex.getBindingResult());
//	}

//	private ResponseEntity<Object> handleValidationInternal(BindException e, HttpHeaders header,
//			WebRequest request, BindingResult bindingResult) {
//
//		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_DADOS_INVALIDOS,
//				TYPE_DADOS_INVALIDOS, bindingResult.getAllErrors().stream()
//				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()),
//				request.getDescription(false).replace("uri=", ""));
//
//		return handleExceptionInternal(e, bodyExceptionResponse, header, HttpStatus.BAD_REQUEST, request);
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaughtException(Exception e, ServletWebRequest request) {
		logger.error(e.getMessage(), e);

		ExceptionResponseVO bodyExceptionResponse = criarExceptionResponse(TITLE_ERRO_SERVIDOR, TYPE_ERRO_INESPERADO,
				Arrays.asList(e.getMessage()), request.getRequest().getRequestURI());

		HttpHeaders header = new HttpHeaders();
		header.add(HEADER_MESSAGE, e.getMessage());

		return handleExceptionInternal(e, bodyExceptionResponse, header, INTERNAL_SERVER_ERROR, request);
	}

	private ExceptionResponseVO criarExceptionResponse(String title, String type, List<String> detail, String instance) {
		return ExceptionResponseVO.builder()
				.detail(detail)
				.instance(instance)
				.title(title)
				.type(type)
				.build();

	}


}
