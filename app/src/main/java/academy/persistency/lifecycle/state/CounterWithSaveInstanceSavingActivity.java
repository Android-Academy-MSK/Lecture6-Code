package academy.persistency.lifecycle.state;

import academy.persistency.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CounterWithSaveInstanceSavingActivity extends AppCompatActivity {

    private static final String KEY_COUNTER = "KEY_COUNTER";
    private int mCounter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        if (savedInstanceState != null) {
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
    }
}
