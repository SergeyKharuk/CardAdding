package sergey.com.card_adding.data.factory;


import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * The class customize {@link Retrofit} calls, he returns an {@link Observable}
 * that allows you to work with RxJava.
 */

public class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {

    private final Retrofit retrofit;
    private final CallAdapter<R, ?> wrapped;

    public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<R, ?> wrapped) {
        this.retrofit = retrofit;
        this.wrapped = wrapped;
    }

    /**
     * @see CallAdapter.Factory#responseType()
     */
    @Override
    public Type responseType() {
        return wrapped.responseType();
    }


    /**
     * @see Factory#adapt(Call) ()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object adapt(@NonNull final Call<R> call) {
        return convert(wrapped.adapt(call));
    }

    private Object convert(final Object o) {
        if (o instanceof Completable)
            return ((Completable) o).onErrorResumeNext(throwable ->
                    Completable.error(handleErrorToShow(throwable)));
        else if (o instanceof Single)
            return ((Single) o).onErrorResumeNext(new Function<Throwable, SingleSource>() {
                @Override
                public SingleSource apply(Throwable throwable) throws Exception {
                    return Single.error(handleErrorToShow(throwable));
                }
            });
        else if (o instanceof Observable)
            return ((Observable) o).onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                @Override
                public ObservableSource apply(final Throwable throwable) throws Exception {
                    return Observable.error(handleErrorToShow(throwable));
                }
            });
        else
            throw new RuntimeException("Not allowed reactive call = " + o.toString());
    }

    private RetrofitException handleErrorToShow(final Throwable throwable) {
        throwable.printStackTrace();
        return asRetrofitException(throwable);
    }


    public RetrofitException asRetrofitException(final Throwable throwable) {
        // We had non-200 http error
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            Response response = httpException.response();

            return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit);
        }
        // A network error happened
        if (throwable instanceof TimeoutException || throwable instanceof ConnectException ||
                throwable instanceof SocketTimeoutException) {
            return RetrofitException.networkError(new IOException(throwable.getMessage(), throwable));
        }

        // to display no internet connection localized message
        if (throwable instanceof UnknownHostException) {
            return RetrofitException.networkError(new IOException("no_internet", throwable));
        }

        // We don't know what happened. We need to simply convert to an unknown error
        return RetrofitException.unexpectedError(throwable);
    }
}