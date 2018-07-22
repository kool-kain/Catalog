package com.controller.services.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controller.services.FlickrControllerService;
import com.domain.colectag.repositories.FlickrPersistenceRepository;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;

@Service
public class FlickrControllerServiceImpl implements FlickrControllerService{
	private final static Logger LOGGER = Logger.getLogger(FlickrControllerServiceImpl.class);
	private final static String SECRET = "cbdfff3ca21a65d0";
	private final static String API_KEY = "4af67866a51e5eec6d30de49e7ea3388";
	private final static Integer RESULTS_PER_PAGE = 150;
	private final static Integer DESIRED_PAGES = 1;
	private final static String COMMA = ",";
	
	@Autowired
	private FlickrPersistenceRepository flickrPersistenceRepository;
	
	@Override
	public Integer searchByTagAndPersist(String tag) {
		if(null == tag || tag.isEmpty()) {
			return 0;
		}
		
		String validateTag = validateTag(tag);
		PhotoList<Photo> listPhotoFlickr =  getFromAPI(validateTag);
		persistData(listPhotoFlickr, validateTag);
		return listPhotoFlickr.size();
	}
	
	private PhotoList<Photo> getFromAPI(String tag) {
		Flickr flickrAPI = new Flickr(API_KEY, SECRET, new REST());
		PhotosInterface phototInterface = flickrAPI.getPhotosInterface();
		SearchParameters params = new SearchParameters();
		params.setTags(new String[]{tag});
		
		PhotoList<Photo> photoList = new PhotoList<>();
		try {
			photoList = phototInterface.search(params, RESULTS_PER_PAGE, DESIRED_PAGES);
		} catch (FlickrException e) {
			LOGGER.error("Error trying to search in Flickr API");
			e.printStackTrace();
		}
		
		return photoList;
	}

	private String validateTag(String tag) {
		if(StringUtils.containsWhitespace(tag)) {
			String validateTag = StringUtils.EMPTY;
			for(String subTag : StringUtils.split(tag, StringUtils.SPACE)){
				validateTag += subTag + COMMA;
			}
			return StringUtils.substring(validateTag, 0, validateTag.length() -1);
		}
		return tag;
	}

	private void persistData(PhotoList<Photo> listData, String tag) {
		if(!listData.isEmpty()) {
			flickrPersistenceRepository.putPhotographs(listData, tag);
		}
	}
}
