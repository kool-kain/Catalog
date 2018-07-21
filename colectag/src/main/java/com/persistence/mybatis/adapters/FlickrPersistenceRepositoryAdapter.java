package com.persistence.mybatis.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.domain.colectag.pojo.PhotoDto;
import com.domain.colectag.repositories.FlickrPersistenceRepository;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.persistence.mybatis.daos.FlickrDao;

@Repository
public class FlickrPersistenceRepositoryAdapter implements FlickrPersistenceRepository{

	@Autowired
	private FlickrDao flickrDao;
	
	public List<PhotoDto> getPhotograhs(List<String> listID) {		
		return flickrDao.getPhotographs(listID);
	}
	
	public void putPhotographs(PhotoList<Photo> listPhoto, String tag) {		
		flickrDao.putPhotographs(listPhoto.stream()
				.map(photo -> PhotoFlickrConverterToPhotoDto(photo, tag)).collect(Collectors.toList()));
	}

	private PhotoDto PhotoFlickrConverterToPhotoDto(Photo photo, String tag) {
		PhotoDto photoDto = new PhotoDto();
		
		photoDto.setId(photo.getId());
		photoDto.setSecret(photo.getSecret());
		photoDto.setServer(photo.getServer());
		photoDto.setTag(tag);
		photoDto.setTitle(photo.getTitle());
		
		return photoDto;
	}

}
