package com.example.takehome.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TakeHomeUtility {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static ObjectMapper getObjectmapper() {
		return objectMapper;
	}
	
	public static String readFileAsString(InputStream inputStream) throws IOException 
	{
		byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
		String data = new String(bdata, StandardCharsets.UTF_8);
		return data;
	}
	
}
