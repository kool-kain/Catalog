package com.domain.colectag.repositories;

import java.util.List;

import com.domain.colectag.pojo.PhotoDto;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

/**
 * This Repository allows to insert/retrieve list of photographs from FLICKR table
 * @author Dani
 *
 */
public interface FlickrPersistenceRepository {
	List<PhotoDto> getPhotograhs(List<String> listID);
	void putPhotographs(PhotoList<Photo> listPhoto, String tag);
}
