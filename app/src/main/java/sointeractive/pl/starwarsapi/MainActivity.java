package sointeractive.pl.starwarsapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    TextView characterInfo;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectViews();
        downloadData();
    }

    private void injectViews() {
        characterInfo = (TextView) findViewById(R.id.characterInfo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void downloadData() {
        Log.d(TAG, "downloadData ");
        //show progress bar: indicate HTTP request delay
        showProgressBar();
        StarWarsClient.getApi().getCharacterById(1).enqueue(new Callback<StarWarsCharacter>() {
            @Override
            public void onResponse(Call<StarWarsCharacter> call, Response<StarWarsCharacter> response) {
                Log.d(TAG, "onResponse " + response.isSuccessful());
                if (response.isSuccessful()) {
                    StarWarsCharacter character = response.body();
                    characterInfo.setText(character.toString());
                    hideProgressBar();
                }
            }

            @Override
            public void onFailure(Call<StarWarsCharacter> call, Throwable t) {
                characterInfo.setText("Error occurred :(");
                hideProgressBar();
            }
        });
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
