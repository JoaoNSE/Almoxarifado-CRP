package br.ce.qxd.crp.exception;

/**
 * Exception que ocorre quando um usuárioq que deseja cadastrar-se já existe.
 * 
 * @author nobre
 *
 */
public class UserAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistsException() {
		super("Nome de usuário já está em uso.");
	}

}
