package com.kentcdodds.flashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author kentcdodds
 */
public class ColorFragment extends Fragment {

  private final int color;

  /**
   * Sets the color of the fragment
   *
   * @param color
   */
  public ColorFragment(int color) {
    super();
    this.color = color;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = new View(getActivity());
    v.setBackgroundColor(color);
    return v;
  }

  /**
   * @return the color
   */
  public int getColor() {
    return color;
  }
}
