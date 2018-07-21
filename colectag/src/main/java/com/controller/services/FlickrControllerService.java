package com.controller.services;

/**
 * Search photos by tag in Flickr with its API and then persist data retrieved
 * @author Dani
 *
 */
public interface FlickrControllerService {
	Integer searchByTagAndPersist(String tag);
}
