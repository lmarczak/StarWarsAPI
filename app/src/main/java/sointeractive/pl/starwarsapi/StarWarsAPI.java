package sointeractive.pl.starwarsapi;

import android.database.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Thanks Again
 * Created by Łukasz Marczak
 *
 * @since 2016-04-21.
 * Copyright © 2015 SoInteractive S.A. All rights reserved.
 */
public interface StarWarsAPI {

    String ENDPOINT = "http://swapi.co/api/";

    @GET("people/{id}")
    Call<StarWarsCharacter> getCharacterById(@Path("id") int id);


    @GET("people/{id}")
    rx.Observable<StarWarsCharacter> getRxCharacterById(@Path("id") int id);
}
