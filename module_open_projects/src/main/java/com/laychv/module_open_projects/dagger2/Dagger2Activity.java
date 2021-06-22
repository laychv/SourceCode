package com.laychv.module_open_projects.dagger2;

import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laychv.module_base.ui.BaseActivity;
import com.laychv.module_open_projects.R;
import com.laychv.module_open_projects.dagger2.di.component.DaggerMyComponent;
import com.laychv.module_open_projects.dagger2.di.module.TextViewModule;

import javax.inject.Inject;

public class Dagger2Activity extends BaseActivity {

    @Inject
    User mUser;
    @Inject
    TextView mTextView;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_dagger2;
    }

    @Override
    public void initView() {
        DaggerMyComponent.builder().textViewModule(new TextViewModule(this)).build().inject(this);
        FrameLayout frameLayout = findViewById(R.id.frame);
        mTextView.setText(mUser.name);
        frameLayout.addView(mTextView);
//        DaggerMyComponent.builder().build().inject(this);
        Toast.makeText(this, mUser.name, Toast.LENGTH_LONG).show();
    }

}
