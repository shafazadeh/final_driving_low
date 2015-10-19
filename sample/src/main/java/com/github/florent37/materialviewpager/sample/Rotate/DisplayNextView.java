package com.github.florent37.materialviewpager.sample.Rotate;

import android.view.View;
import android.view.animation.Animation;

public final class DisplayNextView implements Animation.AnimationListener {
//private boolean mCurrentView;
View image1;
//ImageView image2;

public DisplayNextView(View image1) {
//mCurrentView = currentView;
this.image1 = image1;
//this.image2 = image2;
}

public void onAnimationStart(Animation animation) {
}

public void onAnimationEnd(Animation animation) {
image1.post(new SwapViews( image1));
}

public void onAnimationRepeat(Animation animation) {
}
}
