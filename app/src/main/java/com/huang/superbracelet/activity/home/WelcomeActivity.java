package com.huang.superbracelet.activity.home;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.activity.exam.ExamMainActivity;
import com.huang.superbracelet.activity.exam.SubjectActivity;
import com.huang.superbracelet.base.BaseActivity;

/**
 * Created by 黄家远 on 16/5/27.
 */
public class WelcomeActivity extends BaseActivity{

    private ImageView welcome_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initWedgit();
    }

    @Override
    protected void initWedgit() {
        super.initWedgit();
        welcome_iv = (ImageView) findViewById(R.id.welcome_iv);
        ObjectAnimator animator = ObjectAnimator.ofFloat(welcome_iv,"alpha",0.3f,1.0f);
        animator.setDuration(3000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(!TextUtils.isEmpty(studentId)){
                    if(TextUtils.isEmpty(childSubjectId)){
                        startActivity(new Intent(WelcomeActivity.this, SubjectActivity.class));
                    }else{
                        startActivity(new Intent(WelcomeActivity.this,ExamMainActivity.class));
                    }
                }else{
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
