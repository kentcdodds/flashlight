package com.kentcdodds.flashlight;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import java.util.List;

public class SwipeDetector extends SimpleOnGestureListener {

  private static final int SWIPE_MAX_OFF_PATH = 250;
  private static final int SWIPE_MIN_DISTANCE = 120;
  private static final int SWIPE_THRESHOLD_VELOCITY = 200;
  private final Window window;

  public SwipeDetector(Window window) {
    super();
    this.window = window;
  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    try {
      if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
        if (Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
          changeBrightness((e1.getY() - e2.getY())/100);
          return true;
        }
      } else {
        // right to left swipe
        if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//          onLeftSwipe();
          return true;
        } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//          onRightSwipe();
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
//    changeColor(((currentPosition == 0) ? getFragments().size() - 1 : currentPosition - 1));
  }

  /**
   * What to do when the user swipes right. Change activity color
   */
  private void onRightSwipe() {
//    changeColor(((currentPosition == getFragments().size() - 1) ? 0 : currentPosition + 1));
//    Toast.makeText(getBaseContext(), "Right Swipe: " + currentPosition, Toast.LENGTH_SHORT).show();
  }

  /**
   * Changes the currentPosition to the new position and sets the background drawable of the window to the color
   * drawable at that position
   *
   * @param newPosition
   */
  private void changeColor(int newPosition) {
//    currentPosition = newPosition;
//      getWindow().setBackgroundDrawable(colorList.get(currentPosition));
//    getWindow().getDecorView().setBackground(colorList.get(currentPosition));
  }

  /**
   * What to do when the user swipes up. Brighten the screen
   */
  private void onUpSwipe(float change) {
    changeBrightness(change/100);
//    Toast.makeText(getBaseContext(), "Up Swipe", Toast.LENGTH_SHORT).show();
  }

  /**
   * What to do when the user swipes down. Dim the screen
   */
  private void onDownSwipe(float change) {
    changeBrightness(-change/100);
//    Toast.makeText(getBaseContext(), "Down Swipe", Toast.LENGTH_SHORT).show();
  }

  /**
   * Changes the brightness by the given amount
   *
   * @param change
   */
  private void changeBrightness(float change) {
    MainActivity.print("Changing brightness to " + change);
    WindowManager.LayoutParams lp = getWindow().getAttributes();
    lp.screenBrightness = lp.screenBrightness + change;
    getWindow().setAttributes(lp);
  }

  /**
   * @return the window
   */
  public Window getWindow() {
    return window;
  }

  /**
   * @return the fragments
   */
//  public List<ColorFragment> getFragments() {
//    return fragments;
//  }
}