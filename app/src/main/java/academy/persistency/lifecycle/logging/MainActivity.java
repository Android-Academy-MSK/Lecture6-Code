package academy.persistency.lifecycle.logging;

import academy.persistency.R;
import academy.persistency.relaunch.room.RoomActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainActivity extends BaseLoggingActivity {

    private static final String TAG = "MainActivity.LifeCycle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_main_button_start_second).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, RoomActivity.class));
                    }
                }
        );
    }

    @NonNull
    @Override
    public String getActivityTag() {
        return TAG;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
