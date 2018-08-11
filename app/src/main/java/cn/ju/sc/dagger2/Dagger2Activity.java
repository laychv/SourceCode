package cn.ju.sc.dagger2;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import cn.ju.sc.R;
import cn.ju.comm.BaseActivity;
import cn.ju.sc.dagger2.di.component.DaggerMyComponent;
import cn.ju.sc.dagger2.di.module.TextViewModule;

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
