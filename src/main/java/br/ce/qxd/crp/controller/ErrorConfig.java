package br.ce.qxd.crp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorConfig implements ErrorController{

	private static final String PATH = "/error";
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}
	
	
	 @RequestMapping(PATH)
	    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
	         
	        ModelAndView errorPage = new ModelAndView("errorPage");
	        String errorMsg = "";
	        String errorCode = "";
	        String errorHead = "sdfsdfsd";
	        int httpErrorCode = getErrorCode(httpRequest);
	 
	        switch (httpErrorCode) {
	            case 400: {
	            	errorCode = "Erro 400";
	                errorHead = "Requisição inválida";
	                errorMsg = "O pedido não pode ser entregue devido à sintaxe incorreta.";
	                break;
	            }
	            case 401: {
	                errorCode = "Erro 401";
	                errorHead = "Não Autorizado";
	                errorMsg = "Você não tem autorização para acessar essa página.";
	                break;
	            }
	            case 404: {
	                errorCode = "Erro 404";
	                errorHead = "Recurso não encontrado";
	                errorMsg = "A página que você solicitou não foi encontrada ou não existe.";
	                break;
	            }
	            case 500: {
	                errorCode = "Erro 500";
	                errorHead = "Erro interno do servidor";
	                errorMsg = "O servidor encontrou um erro interno e não pode completar sua requisição.";
	                break;
	            }
	        }
	        errorPage.addObject("errorMsg", errorMsg);
	        errorPage.addObject("errorHead", errorHead);
	        errorPage.addObject("errorCode", errorCode);
	        return errorPage;
	    }
	     
	    private int getErrorCode(HttpServletRequest httpRequest) {
	        return (Integer) httpRequest
	          .getAttribute("javax.servlet.error.status_code");
	    }
}
