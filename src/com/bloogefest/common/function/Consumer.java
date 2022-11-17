package com.bloogefest.common.function;

/**
 * @param <T> Not specified
 *
 * @author Bloogefest
 * @version 0.0
 * @since 0.0.0
 */
@FunctionalInterface
public interface Consumer<T> {

    /**
     * @param <V> Not specified
     *
     * @return Not specified
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    static <V> Consumer<V> empty() {
        return __ -> {};
    }

    /**
     * @param <T>      Not specified
     * @param consumer Not specified
     *
     * @return Not specified
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    static <T> Consumer<T> as(final Consumer<T> consumer) {
        assert consumer != null : "The consumer mustn't be null";
        return consumer;
    }

    /**
     * @param object Not specified
     *
     * @throws FunctionException Not specified
     * @author Bloogefest
     * @since 0.0.0
     */
    void consume(final T object) throws FunctionException;

    /**
     * @param consumer Not specified
     *
     * @return Not specified
     *
     * @author Bloogefest
     * @since 0.0.0
     */
    default Consumer<T> with(final Consumer<T> consumer) {
        assert consumer != null : "The consumer mustn't be null";
        return object -> {
            consume(object);
            consumer.consume(object);
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
    default Consumer<T> suppress(final T object) {
        return __ -> consume(object);
    }

}
