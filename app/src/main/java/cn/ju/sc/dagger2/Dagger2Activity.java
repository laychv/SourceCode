package cn.ju.sc.dagger2;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import cn.ju.sc.R;
import cn.ju.sc.base.BaseActivity;

public class Dagger2Activity extends BaseActivity {

    @Inject
    User mUser;

    @Inject
    TextView mTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

//        DaggerMyComponent.builder().build().inject(this);
//        Toast.makeText(this, mUser.name, Toast.LENGTH_LONG).show();

        DaggerMyComponent.builder().textViewModule(new TextViewModule(this)).build().inject(this);
        FrameLayout frameLayout = findViewById(R.id.frame);
        mTextView.setText(mUser.name);
        frameLayout.addView(mTextView);

    }


}
