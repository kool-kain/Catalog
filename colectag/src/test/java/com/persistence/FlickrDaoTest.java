package com.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.domain.colectag.pojo.Photo;
import com.persistence.mybatis.daos.FlickrDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
{ "classpath:applicationContext-test.xml" })
public class FlickrDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	private static final Long ID1 = 1L;
	private static final Long ID2 = 2L;
	private static final String TAG = "cool";
	private static final String OWNER = "Daniel";
	private static final String SECRET = "098a9";
	private static final Integer SERVER = 1;
	private static final String TITLE = "MyPhoto";
	
	@Autowired
	private FlickrDao flickrDao;
	
	@Test
	public void insert_and_get_photo_test() {
		
		List<Photo> listPhoto = getPhotoList(2);
		flickrDao.putPhotographs(listPhoto);
		
		List<Photo> listPhotoDto =
				flickrDao.getPhotographs(listPhoto.stream().map(Photo::getId).collect(Collectors.toList()));
		Assert.assertNotNull(listPhotoDto);
		Assert.assertThat(listPhotoDto.size(), is(2));
		Assert.assertThat(listPhotoDto.get(0).getId(), is(ID1));
		Assert.assertThat(listPhotoDto.get(0).getTag(), is(TAG+0));
		Assert.assertThat(listPhotoDto.get(1).getId(), is(ID2));
		Assert.assertThat(listPhotoDto.get(1).getTag(), is(TAG+1));
	}
	
	private List<Photo> getPhotoList(Integer quantity){
		List<Photo> listPhoto = new ArrayList<>();
		
		for(int i = 0; i<quantity; i++) {
			Photo photo = new Photo();
			
			photo.setId(Integer.toUnsignedLong(i+1));
			photo.setTag(TAG+i);
			photo.setOwner(OWNER);
			photo.setSecret(SECRET);
			photo.setServer(SERVER);
			photo.setTitle(TITLE);
			
			listPhoto.add(photo);
		}
		
		return listPhoto;
	}
}
