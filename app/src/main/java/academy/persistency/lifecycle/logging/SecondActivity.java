package academy.persistency.lifecycle.logging;

import academy.persistency.R;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SecondActivity extends BaseLoggingActivity {

    private static final String TAG = "SecondActivity.LifeCycle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
    }

    @NonNull
    @Override
    public String getActivityTag() {
        return TAG;
    }
}
