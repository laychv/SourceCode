package cn.ju.openProject.dagger2.di.module;

import android.content.Context;
import android.widget.TextView;

import dagger.Module;
import dagger.Provides;

@Module
public class TextViewModule {

    private Context context;

    public TextViewModule(Context context) {
        this.context = context;
    }

    @Provides
    TextView provideTextView(Context context) {
        return new TextView(context);
    }

    @Provides
    Context provideContext() {
        return context;
    }

}
