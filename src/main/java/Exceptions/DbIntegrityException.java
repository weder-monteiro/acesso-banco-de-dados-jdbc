package Exceptions;

public class DbIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DbIntegrityException(String mensagem) {
		super(mensagem);
	}
}
