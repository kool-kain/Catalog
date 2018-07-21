package com.controller.services.impl;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import com.controller.services.FlickrControllerService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.collections.Collection;
import com.flickr4java.flickr.test.TestInterface;

@Service
public class FlickrControllerServiceImpl implements FlickrControllerService{
	private final static Logger LOGGER = Logger.getLogger(FlickrControllerServiceImpl.class);
	private final static String SECRET = "cbdfff3ca21a65d0";
	private final static String API_KEY = "4af67866a51e5eec6d30de49e7ea3388";
	
	public List<String> getFromAPI(String tag) {
		Flickr flickrAPI = new Flickr(API_KEY, SECRET, new REST());
		TestInterface testInterface = flickrAPI.getTestInterface();
		try {
			testInterface.echo(Collections.EMPTY_MAP);
			System.out.println("he pasado la comunicacion");
		} catch (FlickrException e) {
			LOGGER.error("There was an error with Flickr API");
			e.printStackTrace();
		}
		//flickrAPI.getPhotosInterface().search(params, perPage, page);
		
		return null;
	}

	public int persistData(List<String> listData) {
		// TODO Auto-generated method stub
		return 0;
	}

}
