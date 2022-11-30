package com.bloogefest.common.other;

import org.jetbrains.annotations.ApiStatus.AvailableSince;
import org.jetbrains.annotations.Contract;

/**
 * Изменяемое.
 *
 * @author Bloogefest
 * @version 1.0
 * @see MutableException
 * @since 0.3.0
 */
@AvailableSince("0.3.0")
@SuppressWarnings("unused")
public interface Mutable {

    /**
     * @return Является ли изменяемым.
     *
     * @author Bloogefest
     * @since 0.3.0
     */
    @Contract(pure = true)
    @AvailableSince("0.3.0")
    default boolean mutable() {
        return true;
    }

}
