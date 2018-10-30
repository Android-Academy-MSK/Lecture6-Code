package academy.persistency.relaunch.prefs;

import academy.persistency.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CounterWithSaveInstanceAndPrefsSavingActivity extends AppCompatActivity {

    private static final String KEY_COUNTER = "KEY_COUNTER";
    private static final String SHARED_PREF = "COUNTERS_SHARED_PREF";
    private static final String SHARED_PREF_KEY_COUNTER = "COUNTER";

    private int mCounter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        if (savedInstanceState == null) {
            initCounter();
        } else {
            mCounter = savedInstanceState.getInt(KEY_COUNTER, 0);
        }

        findViewById(R.id.activity_counter_button_increase_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter++;
                updateCounterView();
            }
        });

        updateCounterView();
    }

    private void updateCounterView() {
        TextView counter = findViewById(R.id.activity_counter_text_view_counter);
        counter.setText(String.valueOf(mCounter));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putInt(KEY_COUNTER, mCounter);
        }
        saveCounter();
    }

    private void saveCounter() {
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(SHARED_PREF_KEY_COUNTER, mCounter);
        editor.apply();
    }

    private void initCounter() {
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        mCounter = sharedPref.getInt(SHARED_PREF_KEY_COUNTER, 0);
    }
}
