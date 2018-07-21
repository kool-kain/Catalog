package com.persistence.mybatis.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.domain.colectag.pojo.Photo;
import com.domain.colectag.repositories.FlickrPersistenceRepository;
import com.persistence.mybatis.daos.FlickrDao;

@Repository
public class FlickrPersistenceRepositoryAdapter implements FlickrPersistenceRepository{

	@Autowired
	private FlickrDao flickrDao;
	
	public List<Photo> getPhotograhs(List<Long> listID) {		
		return flickrDao.getPhotographs(listID);
	}
	
	public void putPhotographs(List<Photo> listPhoto) {
		flickrDao.putPhotographs(listPhoto);
	}



}
