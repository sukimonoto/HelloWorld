package com.skyxvn.skyfilm.pro;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.skyxvn.skyfilm.bean.Film;
import com.skyxvn.skyfilm.bean.Utils;
import com.skyxvn.skyfilm.data.DTO;

public class Home extends BaseActivity {

	private ExecutorService executorService;
	private ArrayList<Film> mListFilms;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_phone_above);
        Utils.fixNetwork();
        init();
    }
    
    private final Handler handler = new Handler();

    private void init(){
    	executorService = Executors.newSingleThreadExecutor();
    	executorService.execute(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				
		        mListFilms = DTO.getListFilm();
		        handler.post(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						ListView listView = (ListView) findViewById(R.id.listView1);
						listView.setAdapter(new AdapterFilm(Home.this, mListFilms));
					}
				});
			}
		});
    }
    
    
   
}
