package br.ce.qxd.crp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = ErrorMvcAutoConfiguration.class)
public class App {
	
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//	 
//	    return new EmbeddedServletContainerCustomizer() {
//	        @Override
//	        public void customize(ConfigurableEmbeddedServletContainer container) {
//	 
////	            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "401");
//	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
////	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "500");
//	 
////	            container.addErrorPages(error401Page, error404Page, error500Page);
//	            container.addErrorPages(error404Page);
//	        }
//	    };
//	}
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
	
	
}
