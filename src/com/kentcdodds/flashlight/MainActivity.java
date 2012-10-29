package com.kentcdodds.flashlight;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends FragmentActivity {

  private GestureDetector gestureDetector;
  private Date lastTouched = new Date();
  private FragmentPagerAdapter mAdapter;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setContentView(R.layout.main);
    if (isNewerAPI()) {
      codeForNewerAPI();
    } else {
      codeForOlderAPI();
    }
    mAdapter = new ColorFragmentAdapter(getSupportFragmentManager(), getDefaultColorList());
    ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);
    pager.setAdapter(mAdapter);
    pager.setCurrentItem(Math.round(mAdapter.getCount() / 2));
  }

  /**
   * @return the default list of color ints
   */
  private List<Integer> getDefaultColorList() {
    List<Integer> colorList = new ArrayList<Integer>();
    colorList.add(Color.WHITE);
    colorList.add(Color.BLUE);
    colorList.add(Color.GREEN);
    colorList.add(Color.RED);
    colorList.add(Color.YELLOW);
    return colorList;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if ((lastTouched.getTime() * 60000) + 3 < (new Date().getTime() * 60000)) {
      hideNavigation();
    }
    lastTouched = new Date();
    return gestureDetector.onTouchEvent(event);
  }

  /**
   * Code for newer api. Stuff that honeycomb isn't compatible with
   */
  private void codeForNewerAPI() {
    hideNavigation();
  }

  private void hideNavigation() {
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
  }

  /**
   * Stuff for honeycomb only
   */
  private void codeForOlderAPI() {
  }

  /**
   * @return whether the build version is greater than HONEYCOMB.
   */
  private boolean isNewerAPI() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
  }

  /**
   * Prints the given object to the log with the debug priority with the tag FLASHLIGHT
   *
   * @param object
   */
  public static void print(Object object) {
    Log.println(Log.DEBUG, "FLASHLIGHT", object.toString());
  }
}
