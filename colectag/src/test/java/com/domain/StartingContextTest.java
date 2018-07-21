package com.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.controller.services.FlickrControllerService;
import com.controller.services.impl.FlickrControllerServiceImpl;
import com.domain.colectag.context.ApplicationContextProvider;
import com.domain.colectag.repositories.FlickrPersistenceRepository;
import com.persistence.mybatis.adapters.FlickrPersistenceRepositoryAdapter;
import com.persistence.mybatis.daos.FlickrDao;
import com.view.swing.gui.ColectagAdmin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
{ "classpath:applicationContext-test.xml" })
public class StartingContextTest{
	private static final AnnotationConfigApplicationContext getContext() {
		return new AnnotationConfigApplicationContext(ApplicationContextProvider.class);}
	
	@Test
	public void loadingContext() {
		AnnotationConfigApplicationContext appContext = getContext();
		
		appContext.start();
		
		FlickrControllerServiceImpl flickrControllerService = 
				(FlickrControllerServiceImpl)appContext.getBean(FlickrControllerService.class);
		Assert.assertNotNull(flickrControllerService);
		
		FlickrPersistenceRepositoryAdapter flickrPersistenceRepository = 
				(FlickrPersistenceRepositoryAdapter)appContext.getBean(FlickrPersistenceRepository.class);
		Assert.assertNotNull(flickrPersistenceRepository);
		
		FlickrDao flickrDao = (FlickrDao)appContext.getBean(FlickrDao.class);
		Assert.assertNotNull(flickrDao);
		
		ColectagAdmin colectagAdmin = (ColectagAdmin)appContext.getBean(ColectagAdmin.class);
		Assert.assertNotNull(colectagAdmin);
		
		
		appContext.close();
	}
	
}
