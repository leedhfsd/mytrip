package com.ssafy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;

//import com.ssafy.attraction.model.AttractionDtoBefore;

@Component
public class AlgorithmService {
	
	private static AlgorithmService instance;
	private AlgorithmService() {};
	
	public static AlgorithmService getAlgoService() {
		if(instance == null)
			instance = new AlgorithmService();
		return instance;
	}
	
	
	//TODO : List를 받아와 Dto.userDistance 갱신
//	public List<AttractionDtoBefore> getDistance(List<AttractionDtoBefore> list, double user_lat, double user_long){		
//		for(int i = 0 ; i < list.size() ; i++) {
//			double aLat = list.get(i).getLatitude().doubleValue();
//			double aLon = list.get(i).getLongitude().doubleValue();
//			
//			double radian = Math.PI / 180;
//		    double latitudeRadianDiff = Math.abs(aLat - user_lat) * radian;
//		    double longitudeRadianDiff = Math.abs(aLon - user_long) * radian;
//		    
//		    double R = 6371;
//		    double res1 = Math.sin(latitudeRadianDiff / 2) * Math.sin(latitudeRadianDiff / 2) 
//		            + Math.cos(aLat*radian) * Math.cos(user_lat*radian) 
//		            * Math.sin(longitudeRadianDiff / 2) * Math.sin(longitudeRadianDiff / 2);
//		    
//		    list.get(i).setUserDistance(2 * R * Math.asin(Math.sqrt(res1)));
//		}
//		
//		return list;
//	}
//	
//	//TODO : List를 받아와 Dto.userDistance별로 Sorting
//	public List<AttractionDtoBefore> attractionSortList(List<AttractionDtoBefore> list){
//		return quickSort(list, 0, list.size()-1);
//	}
//	
//	public List<AttractionDtoBefore> quickSort(List<AttractionDtoBefore> list, int low, int high){
//		
//		if(low < high) {
//			int p_index = partition(list, low, high);
//			quickSort(list, low, p_index - 1);
//			quickSort(list, p_index + 1, high);
//		}
//		return list;
//	}
//	
//	public int partition(List<AttractionDtoBefore> list, int low, int high) {
//		double pivot = list.get(low).getUserDistance();
//		int left = low + 1;
//		int right = high;
//		
//		do {
//			while(left < high && list.get(left).getUserDistance() < pivot) left++;
//			while(right > low && list.get(right).getUserDistance() >= pivot) right--;
//			if(left < right) listSwap(list, left, right);
//			
//		}while(left < right);
//		
//		listSwap(list, low, right);
//		
//		return right;
//	}
//	
//	public void listSwap(List<AttractionDtoBefore> list, int a, int b) {
//		AttractionDtoBefore temp = list.get(a);
//		list.set(a, list.get(b));
//		list.set(b, temp);
//	}
	
	//TODO : password의 hash화
	public String hashPassword(String userId, String userPassword) {
	    int iter = 1000;
	    try {
	        byte[] salt = userId.getBytes();
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(salt);
	        byte[] hashedPassword = md.digest(userPassword.getBytes());
	        
	        for (int i = 0; i < iter; i++) {
	            md.reset();
	            hashedPassword = md.digest(hashedPassword);
	        }
	        byte[] hashed = new byte[salt.length + hashedPassword.length];
	        System.arraycopy(salt, 0, hashed, 0, salt.length);
	        System.arraycopy(hashedPassword, 0, hashed, salt.length, hashedPassword.length);
	        return Base64.getEncoder().encodeToString(hashed);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
