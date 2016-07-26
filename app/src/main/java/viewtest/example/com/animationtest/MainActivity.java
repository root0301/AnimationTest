package viewtest.example.com.animationtest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    Button bt1,bt2,bt3,bt4,bt5,bt6;
    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findView();

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ro_bt :
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(testView,"rotation",0f,360f);
                anim1.setDuration(5000);
                anim1.start();
                break;
            case R.id.tra_bt:
                float cuX = testView.getTranslationX();
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(testView,"translationX",cuX,-1000f,cuX);
                anim2.setDuration(5000);
                anim2.start();
                break;
            case R.id.sca_bt:
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(testView,"scaleY",1f,2f,1f);
                anim3.setDuration(5000);
                anim3.start();
                break;
            case R.id.alp_bt:
                ObjectAnimator anim4 = ObjectAnimator.ofFloat(testView,"alpha",1f,0f,1f);
                anim4.setDuration(5000);
                anim4.start();
                anim4.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        System.out.print("动画开始");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        System.out.print("动画结束");
                    }
                });
                break;
            case R.id.com_bt:
                ObjectAnimator comeIn = ObjectAnimator.ofFloat(testView,"translationX",-1000f,0f);
                ObjectAnimator roa = ObjectAnimator.ofFloat(testView,"rotation",0f,360f);
                ObjectAnimator alp = ObjectAnimator.ofFloat(testView,"alpha",1f,0f,1f);
                ObjectAnimator sca = ObjectAnimator.ofFloat(testView,"scaleY",1f,2f,1f);
                AnimatorSet aniset = new AnimatorSet();
                aniset.play(roa).with(alp).with(sca).after(comeIn);
                aniset.setDuration(5000);
                aniset.start();
                break;
            case R.id.xml_bt:
                Animator ani = AnimatorInflater.loadAnimator(this,R.animator.xml_animation);
                ani.setTarget(testView);
                ani.start();
                break;
        }
    }

    private void findView() {
        bt1 = (Button) findViewById(R.id.ro_bt);
        bt2 = (Button) findViewById(R.id.tra_bt);
        bt3 = (Button) findViewById(R.id.sca_bt);
        bt4 = (Button) findViewById(R.id.alp_bt);
        bt5 = (Button) findViewById(R.id.com_bt);
        bt6 = (Button) findViewById(R.id.xml_bt);
        testView = (TextView) findViewById(R.id.test_view);
    }

}
