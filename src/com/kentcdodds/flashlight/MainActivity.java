package com.kentcdodds.flashlight;

import android.app.*;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.*;
import android.view.*;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

  private GestureDetector gestureDetector;
  private Date lastTouched = new Date();

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      codeForNewerAPI();
    } else {
      codeForOlderAPI();
    }
    gestureDetector = new GestureDetector(getApplicationContext(), new MyGestureDetector(getDefaultColorList()));
    setContentView(R.layout.main);
  }

  private List<ColorDrawable> getDefaultColorList() {
    List<ColorDrawable> colorList = new ArrayList<ColorDrawable>();
    colorList.add(new ColorDrawable(Color.WHITE));
    colorList.add(new ColorDrawable(Color.BLUE));
    colorList.add(new ColorDrawable(Color.GREEN));
    colorList.add(new ColorDrawable(Color.RED));
    colorList.add(new ColorDrawable(Color.YELLOW));
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

  private class MyGestureDetector extends SimpleOnGestureListener {

    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private final List<ColorDrawable> colorList;
    private int currentPosition = 0;

    public MyGestureDetector(List<ColorDrawable> colorList) {
      super();
      this.colorList = colorList;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      try {
        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
          if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                  && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            onUpSwipe();
            return true;
          } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                  && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            onDownSwipe();
            return true;
          }
        } else {
          // right to left swipe
          if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                  && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            onLeftSwipe();
            return true;
          } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                  && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            onRightSwipe();
            return true;
          }
        }
        // up to down swipe
      } catch (Exception e) {
      }
      return false;
    }

    /**
     * What to do when the user swipes left. Change activity color
     */
    private void onLeftSwipe() {
      changeColor(((currentPosition == 0) ? colorList.size() - 1 : currentPosition - 1));
      Toast.makeText(getBaseContext(), "Left Swipe: " + currentPosition, Toast.LENGTH_SHORT).show();
    }

    /**
     * What to do when the user swipes right. Change activity color
     */
    private void onRightSwipe() {
      changeColor(((currentPosition == colorList.size() - 1) ? 0 : currentPosition + 1));
      Toast.makeText(getBaseContext(), "Right Swipe: " + currentPosition, Toast.LENGTH_SHORT).show();
    }

    /**
     * Changes the currentPosition to the new position and sets the background drawable of the window to the color
     * drawable at that position
     *
     * @param newPosition
     */
    private void changeColor(int newPosition) {
      currentPosition = newPosition;
//      getWindow().setBackgroundDrawable(colorList.get(currentPosition));
      getWindow().getDecorView().setBackground(colorList.get(currentPosition));
    }

    /**
     * What to do when the user swipes up. Brighten the screen
     */
    private void onUpSwipe() {
      Toast.makeText(getBaseContext(), "Up Swipe", Toast.LENGTH_SHORT).show();
    }

    /**
     * What to do when the user swipes down. Dim the screen
     */
    private void onDownSwipe() {
      Toast.makeText(getBaseContext(), "Down Swipe", Toast.LENGTH_SHORT).show();
    }
  }
}
