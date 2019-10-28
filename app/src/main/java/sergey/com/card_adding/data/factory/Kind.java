package sergey.com.getwinner.data.factory;


import java.io.IOException;


public enum Kind {
    /**
     * An {@link IOException} occurred while communicating to the server.
     */
    NETWORK,
    /**
     * A non-200 HTTP status code was received from the server.
     */
    HTTP,
    /**
     * An internal error occurred while attempting to execute a request. It is best practice to
     * re-throw this exception so your application crashes.
     */
    UNEXPECTED
}

