package br.com.school.hexagono.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter 
@ToString 
public class BusinessException extends RuntimeException{

	public enum Error {
		ERR001("Maximum students reached."),
		ERR002("Maximum courses per student.");
		private String message;
		private String property;
		
		public String getMessage() {
			return message;
		}
		public String getProperty() {
			return property;
		}
		private Error(String message, String property) {
			this.message = message;
			this.property = property; 
		}
		private Error(String message) {
			this.message = message;
			this.property = "noProperty";
		}

		public void setMessage(String formatMessage) {
			this.message = formatMessage;
		}
	}
	
	private static final long serialVersionUID = 7345591794246155851L;
	private Date timestamp;
	private List<Error> errors = new ArrayList<>();
	
	public BusinessException(Error error) {
		super(error.message);
		this.errors.add(error);
		this.timestamp = new Date();
	}
}

