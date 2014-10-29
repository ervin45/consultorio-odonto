package br.com.cocodonto.framework.dao;

public class ConnectionFailureDaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConnectionFailureDaoException() {
		super();
	}

	public ConnectionFailureDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionFailureDaoException(String message) {
		super(message);
	}

	public ConnectionFailureDaoException(Throwable cause) {
		super(cause);
	}
	
	
	

}
