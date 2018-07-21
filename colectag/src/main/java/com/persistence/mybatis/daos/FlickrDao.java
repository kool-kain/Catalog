package com.persistence.mybatis.daos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.domain.colectag.pojo.PhotoDto;


@Mapper
public interface FlickrDao {
	public List<PhotoDto> getPhotographs(@Param("listID")List<String> listID);
	
	public void putPhotograph(@Param("photo")PhotoDto photo);
	
	public void putPhotographs(@Param("listPhoto")List<PhotoDto> listPhoto);
}
