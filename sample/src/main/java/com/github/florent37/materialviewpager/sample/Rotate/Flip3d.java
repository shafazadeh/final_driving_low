package com.github.florent37.materialviewpager.sample.Rotate;


import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class Flip3d{
	
	public static void applyRotation(float start, float end,View view) {
	// Find the center of image
	final float centerX = view.getWidth() / 2.0f;
	final float centerY = view.getHeight() / 2.0f;

	final Flip3dAnimation rotation =
	       new Flip3dAnimation(start, end, centerX, centerY);
	rotation.setDuration(500);
	rotation.setFillAfter(true);
	rotation.setInterpolator(new AccelerateInterpolator());
	rotation.setAnimationListener(new DisplayNextView( view));

	view.startAnimation(rotation);

	}
}