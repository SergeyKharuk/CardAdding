package sergey.com.card_adding.data.factory;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    /**
     * Returns an {@link RxErrorHandlingCallAdapterFactory} instance
     */
    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    /**
     * Returns an {@link RxCallAdapterWrapper} instance
     */
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }
}
