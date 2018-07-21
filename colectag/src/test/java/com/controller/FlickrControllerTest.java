package com.controller;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.controller.services.impl.FlickrControllerServiceImpl;
import com.domain.colectag.repositories.FlickrPersistenceRepository;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = 
{ "classpath:applicationContext-test.xml" })
public class FlickrControllerTest extends AbstractJUnit4SpringContextTests{
	private final static String TAG_OK = "summer";
	private final static String TAG_NOK = "summerrtaljar";
	private final static Integer RESULTS_PER_PAGE = 25;
	private final static Integer DESIRED_PAGES = 1;
	
	@InjectMocks
	@Spy
	private FlickrControllerServiceImpl flickrControllerService; 
	
	@Mock
	private FlickrPersistenceRepository flickrPersistenceRepository;
	
	@Captor
	private ArgumentCaptor<PhotoList<Photo>> photoListCaptor;
	
	@Captor
	private ArgumentCaptor<String> tagCaptor;
	
	@Test
	public void searchByTagAndPersist_test() throws FlickrException {
		Mockito.doNothing().when(flickrPersistenceRepository).putPhotographs(Mockito.any(), Mockito.any());
		
		Assert.assertThat(flickrControllerService.searchByTagAndPersist(TAG_OK), is(RESULTS_PER_PAGE));
		
		Mockito.verify(flickrPersistenceRepository, Mockito.times(1))
			.putPhotographs(photoListCaptor.capture(), tagCaptor.capture());
		
		photoListCaptor.getValue().forEach(photo -> {
			Assert.assertNotNull(photo.getId());
			Assert.assertNotNull(photo.getServer());
			Assert.assertNotNull(photo.getTitle());
			Assert.assertNotNull(photo.getId());
			Assert.assertNotNull(photo.getTags());
		});
		Assert.assertThat(photoListCaptor.getValue().getPage(), is(DESIRED_PAGES));
		Assert.assertThat(photoListCaptor.getValue().getPerPage(), is(RESULTS_PER_PAGE));
		Assert.assertThat(tagCaptor.getValue(), is(TAG_OK));		
	}
	
	@Test
	public void searchByTagAndPersist_test_no_matches() throws FlickrException {
		
		Assert.assertThat(flickrControllerService.searchByTagAndPersist(TAG_NOK), is(0));	
	}
}
