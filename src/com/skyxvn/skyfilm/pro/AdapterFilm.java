package com.skyxvn.skyfilm.pro;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.imageloader.ImageLoaderMutilTask;
import com.android.imageloader.ImageOptions;
import com.android.imageloader.ManagerCache.ManagerCacheParams;
import com.skyxvn.skyfilm.bean.Film;
import com.skyxvn.skyfilm.bean.Logger;

public class AdapterFilm extends BaseAdapter {
	private final static String TAG = "AdapterFilm";
	private ArrayList<Film> listFilms;
	private LayoutInflater inflater;
	private ImageLoaderMutilTask multiImageLoader;
	private Activity mActivity;

	public AdapterFilm(Activity activity, ArrayList<Film> listFilms) {
		// TODO Auto-generated constructor stub
		mActivity = activity;
		this.listFilms = listFilms;
		inflater = LayoutInflater.from(mActivity);
		init();
	}

	private void init() {

		RetainFragmentData mRetainFragmentData = RetainFragmentData
				.findOrCreatRetainFragmentData(((FragmentActivity) mActivity)
						.getSupportFragmentManager());

		multiImageLoader = mRetainFragmentData.getImageLoaderMutilTask();

		if (multiImageLoader == null) {
			Logger.d(TAG, "multiImageLoader instance......");
			ManagerCacheParams cacheParams = new ManagerCacheParams(
					"ImageLoader");
			cacheParams.diskCacheSize = 1024 * 1024 * 10;
			cacheParams.compressFormat = CompressFormat.PNG;
			multiImageLoader = new ImageLoaderMutilTask(mActivity, cacheParams) {

				public Bitmap getBitmap(Object data, ImageOptions imageOptions) {
					// TODO Auto-generated method stub
					Logger.d("AdapterTrollFace", data.toString());
					return com.android.imageloader.Utils.downloadBitmap(
							String.valueOf(data), 20000);
				}

			};
			multiImageLoader.setLoadingImage(null);
			multiImageLoader.setImageFadeIn(true);
			mRetainFragmentData.setImageLoaderMutilTask(multiImageLoader);

		}
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return listFilms.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (v == null) {
			viewHolder = new ViewHolder();
			v = inflater.inflate(R.layout.item_film, null);
			viewHolder.imageView = (ImageView) v.findViewById(R.id.thumb);
			viewHolder.tilte = (TextView) v.findViewById(R.id.title);
			viewHolder.sologu = (TextView) v.findViewById(R.id.slug);
			viewHolder.imdb = (TextView) v.findViewById(R.id.imdb);
			v.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) v.getTag();
			final Film film = listFilms.get(position);
			viewHolder.tilte.setText(film.filmTitle);
			viewHolder.sologu.setText(film.filmSapo);
			viewHolder.imdb.setText(film.filmImdb+"");
			multiImageLoader.displayImage(viewHolder.imageView, film.filmCropThumb
					, null, null);
		}

		return v;
	}

	private class ViewHolder {
		ImageView imageView;
		TextView tilte;
		TextView sologu;
		TextView imdb;
	}

}
