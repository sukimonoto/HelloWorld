package com.skyxvn.skyfilm.pro;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity {

	private int mTitleRes;
	protected SlidingMenu sm;
	protected ActionBar actionBar;

	// public BaseMenu(int titleRes) {
	// mTitleRes = titleRes;
	// }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTitleRes = R.string.app_name;
		setTitle(mTitleRes);
		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		// customize the SlidingMenu
		sm = getSlidingMenu();

		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setBehindOffsetRes(R.dimen.actionbar_home_width);
		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.show();

	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
