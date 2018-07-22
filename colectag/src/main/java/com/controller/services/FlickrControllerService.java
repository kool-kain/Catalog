package com.controller.services;

/**
 * Search photos by tag in Flickr with its API and then persist data retrieved. 
 * Returns number of pics retrieved and persistend in DB
 * @author Dani
 *
 */
public interface FlickrControllerService {
	Integer searchByTagAndPersist(String tag);
}
