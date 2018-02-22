package com.example.miodragmilosevic.roomtest.startattack;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by miodrag.milosevic on 1/31/2018.
 */

public class StartAttackRepository {
    private static final int ONE_SECOND_DELAY_MILLIS = 1000;

    public Observable<Long> getElapsedTimeMillis(){
        return Observable.interval(0, ONE_SECOND_DELAY_MILLIS, TimeUnit.MILLISECONDS)
                .scan((previous, current) -> (previous + ONE_SECOND_DELAY_MILLIS));
    }

   /*

// Lce -> Loading / Content / Error
class Lce<T> {
  public static <T> Lce<T> data(T data) {
    // implementation
  }
  public static <T> Lce<T> error(Throwable error) {
    // implementation
  }
  public static <T> Lce<T> loading() {
    // implementation
  }
  boolean isLoading();
  boolean hasError();
  Throwable getError();
  T getData();
}

/////
Observable<Lce<Data>> getDataEventStream() {
  return api.getData()
    .map(data -> Lce.data(data))
    .startWith(Lce.loading())
    .onErrorReturn(e -> Lce.error(e))
}


/////
class UserContent {
 // requires both the user and list of published content
}

Observable<Lce<User>> getUserEventStream(String userId);
Observable<Lce<List<Content>> getContentEventStream(String userId);

Observable<Lce<UserContent>> getProfileEventStream(String id) {
  return Observable.combineLatest(
    getUserEventStream(id),
    getContentEventStream(id),
    { user, content ->
      if (user.isLoading || content.isLoading) {
        return Lce.loading()
      } else if (user.hasError()) {
        return Lce.error(user.getError()
      } else if (content.hasError()) {
        return Lce.error(content)
      } else {
        return Lce.data(createUserContent(
            user.getData(), content.getData())
      }
    })
}
     */
}
