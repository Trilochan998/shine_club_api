package com.shineclub.api.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shineclub.api.dto.ResponseStructure;

@ControllerAdvice
public class ShineCludExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(CustomLoginException.class)
	public ResponseEntity<ResponseStructure<String>> customeLoginException( CustomLoginException e){
		  		ResponseStructure<String> structure=new ResponseStructure<String>();
		  		structure.setBody(e.getMessage());
		  		structure.setMessage("Invalid Member");
		  		structure.setCode(HttpStatus.NOT_FOUND.value());
		  		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseStructure<String>> maxUploadSizeException(){
  		ResponseStructure<String> structure=new ResponseStructure<String>();
  		structure.setBody("");
  		structure.setMessage("image size exceeded");
  		structure.setCode(HttpStatus.NOT_FOUND.value());
  		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> CustomeSQLIntegrityConstraintViolationException(){
  		ResponseStructure<String> structure=new ResponseStructure<String>();
  		structure.setBody("");
  		structure.setMessage("Already used this number");
  		structure.setCode(HttpStatus.OK.value());
  		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
}
}
