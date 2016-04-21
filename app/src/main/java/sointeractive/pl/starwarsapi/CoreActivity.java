package sointeractive.pl.starwarsapi;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoreActivity extends AppCompatActivity {

    FloatingActionButton single, many;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
        single = (FloatingActionButton) findViewById(R.id.single);
        many = (FloatingActionButton) findViewById(R.id.many);

        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotoActivity(SingleCallActivity.class);
            }
        });

        many.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(RxJavaActivity.class);
            }
        });
    }

    <T> void gotoActivity(Class<T> klazz) {
        Intent intent = new Intent(this, klazz);
        startActivity(intent);
    }
}
