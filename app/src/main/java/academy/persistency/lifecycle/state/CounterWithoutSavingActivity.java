package academy.persistency.lifecycle.state;

import academy.persistency.R;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CounterWithoutSavingActivity extends AppCompatActivity {

    private int mCounter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Process Configuration Changed manually
    }
}
