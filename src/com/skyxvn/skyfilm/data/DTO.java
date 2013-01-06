package com.skyxvn.skyfilm.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.skyxvn.skyfilm.bean.Film;
import com.skyxvn.skyfilm.bean.Logger;

public class DTO {

	static String TAG = "DTO";
	static String url = "http://api.phim.soha.vn/mobile_api/API/device_public_api.ashx?a=film_country&v=mobile&country=1&page=0&size=12&thumbsize=130_187&sort=5";
	
	
	public static ArrayList<Film> getListFilm(){
		String respone = new HTTPServer().getResponse(url, false);
		return parseFilm(respone);
	}
	public static ArrayList<Film> parseFilm(String respone){
		if(TextUtils.isEmpty(respone))
			return null;
		ArrayList<Film> listFilms = null;
		
		try {
			JSONObject jsonObject = new JSONObject(respone);
			if(jsonObject==null)
				return null;
			
			JSONArray jsonArray = jsonObject.getJSONArray("listfilm");
			if(jsonArray==null)
				return null;
			int lenght = jsonArray.length();
			if(lenght==0)
				return null;
			
			listFilms = new ArrayList<Film>();
			for(int i=0; i < lenght; i++){
				JSONObject jsonFilm = jsonArray.getJSONObject(i);
				if (jsonFilm != null) {
					Film film = new Film();
					film.filmId = jsonFilm.getInt("filmId");
					Logger.d(TAG, "filmId "+film.filmId);
					film.filmHD = jsonFilm.getInt("filmHD");
					film.filmTitle = jsonFilm.getString("filmTitle");
					film.filmTitleEn = jsonFilm.getString("filmTitleEn");
					film.filmActor = jsonFilm.getString("filmActor");
					film.filmDirector = jsonFilm.getString("filmDirector");
					film.filmImdb = jsonFilm.getDouble("filmImdb");
					film.filmDesc = jsonFilm.getString("filmDesc");
					Logger.d(TAG, "film.filmDesc "+film.filmDesc);
					film.filmSapo = jsonFilm.getString("filmSapo");
					film.filmCropThumb = jsonFilm.getString("filmCropThumb");
					film.filmThumb = jsonFilm.getString("filmThumb");
					film.filmYear = jsonFilm.getInt("filmYear");
					film.filmSub = jsonFilm.getInt("filmSub");
					film.film_hd_free = jsonFilm.getInt("film_hd_free");
					listFilms.add(film);
				}
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listFilms;
	}
}
