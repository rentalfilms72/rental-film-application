package com.rentalfilm.msaclientui.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		
		final ErrorDecoder defaultErrorDecoder = new Default();

		
		if(response.status() == 404) {
			throw new ProductNotFoundException("Product not found");
		}
		return defaultErrorDecoder.decode(methodKey, response);
	}

}
