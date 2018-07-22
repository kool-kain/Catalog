package com.persistence.mybatis.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.domain.colectag.pojo.PhotoDto;
import com.domain.colectag.repositories.FlickrPersistenceRepository;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.persistence.mybatis.daos.FlickrDao;

@Repository
public class FlickrPersistenceRepositoryAdapter implements FlickrPersistenceRepository{

	private static final int MAX_TITLE = 300;
	
	@Autowired
	private FlickrDao flickrDao;
	
	public List<PhotoDto> getPhotograhs(List<String> listID) {		
		return flickrDao.getPhotographs(listID);
	}
	
	public void putPhotographs(PhotoList<Photo> listPhoto, String tag) {		
		flickrDao.putPhotographs(listPhoto.stream()
				.map(photo -> PhotoFlickrConverterToPhotoDto(photo, tag))
				.limit(listPhoto.getPerPage()).collect(Collectors.toList()));
	}

	private PhotoDto PhotoFlickrConverterToPhotoDto(Photo photo, String tag) {
		PhotoDto photoDto = new PhotoDto();
		
		photoDto.setId(photo.getId());
		photoDto.setSecret(photo.getSecret());
		photoDto.setServer(photo.getServer());
		photoDto.setTag(tag);
		photoDto.setTitle(StringUtils.substring(photo.getTitle(), 0, MAX_TITLE));
		
		return photoDto;
	}

}
