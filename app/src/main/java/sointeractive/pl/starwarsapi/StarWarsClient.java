package sointeractive.pl.starwarsapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Thanks Again
 * Created by Łukasz Marczak
 *
 * @since 2016-04-21.
 * Copyright © 2015 SoInteractive S.A. All rights reserved.
 */
public class StarWarsClient {

    private static Retrofit buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StarWarsAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static StarWarsAPI getApi() {
        return buildRetrofit().create(StarWarsAPI.class);
    }
}
