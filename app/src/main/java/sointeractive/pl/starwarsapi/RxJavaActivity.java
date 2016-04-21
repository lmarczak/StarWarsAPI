package sointeractive.pl.starwarsapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sointeractive.pl.starwarsapi.network.StarWarsCharacter;
import sointeractive.pl.starwarsapi.network.StarWarsClient;

public class RxJavaActivity extends AppCompatActivity {

    public static final String TAG = RxJavaActivity.class.getSimpleName();

    RecyclerView recyclerView;
    ProgressBar progressBar;
    StarWarsAdapter starWarsAdapter;
    rx.Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        setupViews();
        setupRecyclerView();
        downloadData();
    }

    private void setupViews() {
        progressBar = (ProgressBar) findViewById(R.id.rx_progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        starWarsAdapter = new StarWarsAdapter(new ArrayList<StarWarsCharacter>());
        recyclerView.setAdapter(starWarsAdapter);
    }

    private void downloadData() {
        Log.d(TAG, "downloadData ");
        showProgressBar();

        subscription = Observable.range(1, 10).flatMap(new Func1<Integer, Observable<StarWarsCharacter>>() {
            @Override
            public Observable<StarWarsCharacter> call(Integer characterId) {
                return StarWarsClient.getRxApi().getRxCharacterById(characterId).onErrorResumeNext(new Func1<Throwable, Observable<? extends StarWarsCharacter>>() {
                    @Override
                    public Observable<? extends StarWarsCharacter> call(Throwable throwable) {
                        return Observable.empty();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<StarWarsCharacter>() {
            @Override
            public void onCompleted() {
                starWarsAdapter.notifyItemRangeChanged(0, starWarsAdapter.getItemCount());
                hideProgressBar();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError " + e.getMessage());
                e.printStackTrace();
                hideProgressBar();
                Toast.makeText(RxJavaActivity.this, "Cannot download data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(StarWarsCharacter starWarsCharacter) {
                starWarsAdapter.dataSet.add(starWarsCharacter);
            }
        });
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        if (subscription != null) subscription.unsubscribe();
        super.onDestroy();
    }
}
