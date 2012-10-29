package com.kentcdodds.flashlight;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kentcdodds
 */
public class ColorFragmentAdapter extends FragmentPagerAdapter {

  private List<Integer> colors = new ArrayList<Integer>();

  /**
   *
   * @param fm
   * @param colors
   */
  public ColorFragmentAdapter(FragmentManager fm, List<Integer> colors) {
    super(fm);
    this.colors = colors;
  }

  @Override
  public int getCount() {
    MainActivity.print("getCount() returning " + getColors().size() * 10000);
    return getColors().size() * 10000;
  }

  /**
   * @return the actual count of the fragments
   */
  public int getRealCount() {
    return getColors().size();
  }

  @Override
  public Fragment getItem(int i) {
    MainActivity.print("getItem() returning getColorFragment(" + i % getColors().size() + ") it is: " + i + " % " + getColors().size());
    Integer colorInt = getColor(i % getColors().size());
    return new ColorFragment(colorInt);
  }

  /**
   * Get the color from the colors list in the given position
   *
   * @param position
   * @return
   */
  private Integer getColor(int position) {
    return getColors().get(position);
  }

  /**
   * @return the colors
   */
  public List<Integer> getColors() {
    return colors;
  }

  /**
   * @param colors the colors to set
   */
  public void setColors(List<Integer> colors) {
    this.colors = colors;
  }
}
