package com.skyxvn.skyfilm.pro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.imageloader.ImageLoaderMutilTask;
import com.skyxvn.skyfilm.bean.Logger;

/**
 * Class is not a view on
 * Class contains objects when configre change All objects is not destroyed  
 * @author DIUEDV
 *
 */
public class RetainFragmentData extends Fragment{
	public static final String TAG = "RetainFragmentData";  
	private ImageLoaderMutilTask mImageLoaderMutilTask;
	// object to save
	public Object mObject; 
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//keep data when configuration changed
		// void called method destroy()
		setRetainInstance(true);
	}
	
	/**
	 * To get all Object data is saved
	 * @param frm
	 * @return
	 */
	public static RetainFragmentData findOrCreatRetainFragmentData(FragmentManager frm){
		RetainFragmentData mRetainFragmentData = null;
		mRetainFragmentData = (RetainFragmentData) frm.findFragmentByTag(TAG);
		if(mRetainFragmentData == null){
			// create new Retain Fragment Data
			mRetainFragmentData = new RetainFragmentData();
			// add to FragmentActivity
			frm.beginTransaction().add(mRetainFragmentData, TAG).commit();
		}
		return mRetainFragmentData;
		
		
	}
	
	public static void removeRetainFragmentData(FragmentManager frm){
		RetainFragmentData retainFragmentData = (RetainFragmentData) frm.findFragmentByTag(TAG);
		if(retainFragmentData!=null){
			retainFragmentData.mImageLoaderMutilTask.clearCacheMemory();
			frm.beginTransaction().remove(retainFragmentData).commit();
			Logger.d(TAG, "remove retain...");
		}
		
	}
	
	public ImageLoaderMutilTask getImageLoaderMutilTask() {
		return mImageLoaderMutilTask;
	}

	public void setImageLoaderMutilTask(ImageLoaderMutilTask mImageLoaderMutilTask) {
		this.mImageLoaderMutilTask = mImageLoaderMutilTask;
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mImageLoaderMutilTask!=null){
			mImageLoaderMutilTask.clearCacheMemory();
		}
		
	}

	/**
	 * To save object 
	 * @param object
	 */
	public void setObjectSaved(Object object){
		mObject = object;
	}
	/**
	 * get Object is saved
	 * @return
	 */
	public Object getObjectSaved(){
		return mObject;
	}
	
	
}
