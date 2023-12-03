package com.example.BookManagement;


import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class BookManagementApplication {

	public static void main(String[] args) {
		String BASE_URI = "http://localhost:8080/";
		ResourceConfig resourceConfig = new ResourceConfig(BookResource.class,AuthorResource.class);
		GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
		System.out.println("Server started at: " + BASE_URI);
	}

}
