package com.controller.services;

import java.util.List;

public interface FlickrControllerService {
	List<String> getFromAPI(String tag);
	
	int persistData(List<String> listData);
}
