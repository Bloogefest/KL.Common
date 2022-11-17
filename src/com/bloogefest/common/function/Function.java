package com.bloogefest.common.function;

/**
 * @param <T> Not specified
 * @param <R> Not specified
 *
 * @author Bloogefest
 * @version 0.0
 * @since 0.0.0
 */
@FunctionalInterface
public interface Function<T, R> {

    /**
     * @param <T> Not specified
     * @param <R> Not specified
     *
     * @return Not specified
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    static <T, R> Function<T, R> nullable() {
        return __ -> null;
    }

    /**
     * @param <T>    Not specified
     * @param <R>    Not specified
     * @param result Not specified
     *
     * @return Not specified
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    static <T, R> Function<T, R> strict(final R result) {
        return __ -> result;
    }

    /**
     * @param <T>     Not specified
     * @param <R>     Not specified
     * @param function Not specified
     *
     * @return Not specified
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    static <T, R> Function<T, R> as(final Function<T, R> function) {
        assert function != null : "The function mustn't be null";
        return function;
    }

    /**
     * @param object Not specified
     *
     * @return Not specified
     *
     * @throws FunctionException Not specified
     * @author Bloogefest
     * @since 0.0.0
     */
    R execute(final T object) throws FunctionException;

    /**
     * @param function Not specified
     *
     * @return Not specified
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    default Function<T, R> with(final Function<T, R> function) {
        assert function != null : "The function mustn't be null";
        return object -> {
            execute(object);
            return function.execute(object);
        };
    }

    /**
     * @param object Not specified
     *
     * @return Not specified
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    default Function<T, R> suppress(final T object) {
        return __ -> execute(object);
    }

}
