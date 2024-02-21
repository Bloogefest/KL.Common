/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.common.function;

import com.bloogefest.annotation.Contract;
import com.bloogefest.annotation.NonNull;
import com.bloogefest.annotation.Nullable;
import com.bloogefest.common.SoftException;

/**
 * Исключение выполнения функции. Возникает при неудачной попытке выполнения функции.
 *
 * @since 1.0.0
 */
public class FunctionException extends SoftException {

    /**
     * Содержит сообщение этого исключения по умолчанию.
     *
     * @since 4.0.0-RC3
     */
    public static final @NonNull String DEFAULT_MESSAGE = "Failed to call the function";

    /**
     * Содержит шаблонное сообщение этого исключения.
     *
     * @since 4.0.0-RC3
     */
    public static final @NonNull String TEMPLATE_MESSAGE = "Failed to call %s";

    /**
     * Создаёт экземпляр этого исключения на основе {@linkplain #DEFAULT_MESSAGE сообщения},
     * {@linkplain #DEFAULT_CAUSE причины}, {@linkplain #DEFAULT_SUPPRESSION параметров подавления} и
     * {@linkplain #DEFAULT_WRITABLE трассировки стека по умолчанию}.
     *
     * @since 1.0.0
     */
    @Contract(value = "-> new", impact = Contract.Impact.INTERNAL)
    public FunctionException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Создаёт экземпляр этого исключения на основе переданного сообщения, а также {@linkplain #DEFAULT_CAUSE причины},
     * {@linkplain #DEFAULT_SUPPRESSION параметров подавления} и
     * {@linkplain #DEFAULT_WRITABLE трассировки стека по умолчанию}.
     *
     * @param message сообщение.
     *
     * @since 1.0.0
     */
    @Contract(value = "? -> new", impact = Contract.Impact.INTERNAL)
    public FunctionException(final @Nullable String message) {
        super(message);
    }

    /**
     * Создаёт экземпляр этого исключения на основе переданной причины, а также {@linkplain #DEFAULT_MESSAGE сообщения},
     * {@linkplain #DEFAULT_SUPPRESSION параметров подавления} и
     * {@linkplain #DEFAULT_WRITABLE трассировки стека по умолчанию}.
     *
     * @param cause причина.
     *
     * @since 1.0.0
     */
    @Contract(value = "? -> new", impact = Contract.Impact.INTERNAL)
    public FunctionException(final @Nullable Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    /**
     * Создаёт экземпляр этого исключения на основе переданных сообщения и причины, а также
     * {@linkplain #DEFAULT_SUPPRESSION параметров подавления} и
     * {@linkplain #DEFAULT_WRITABLE трассировки стека по умолчанию}.
     *
     * @param message сообщение.
     * @param cause причина.
     *
     * @since 1.0.0
     */
    @Contract(value = "?, ? -> new", impact = Contract.Impact.INTERNAL)
    public FunctionException(final @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Создаёт экземпляр этого исключения на основе переданных параметров подавления и трассировки стека, а также
     * {@linkplain #DEFAULT_MESSAGE сообщения} и {@linkplain #DEFAULT_CAUSE причины по умолчанию}.
     *
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 3.0.0
     */
    @Contract(value = "?, ? -> new", impact = Contract.Impact.INTERNAL)
    public FunctionException(final boolean suppression, final boolean writable) {
        super(DEFAULT_MESSAGE, suppression, writable);
    }

    /**
     * Создаёт экземпляр этого исключения на основе переданных сообщения, параметров подавления и трассировки стека, а
     * также {@linkplain #DEFAULT_CAUSE причины по умолчанию}.
     *
     * @param message сообщение.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 4.0.0-RC3
     */
    @Contract(value = "?, ?, ? -> new", impact = Contract.Impact.INTERNAL)
    public FunctionException(final @Nullable String message, final boolean suppression, final boolean writable) {
        super(message, suppression, writable);
    }

    /**
     * Создаёт экземпляр этого исключения на основе переданных причины, параметров подавления и трассировки стека, а
     * также {@linkplain #DEFAULT_MESSAGE сообщения по умолчанию}.
     *
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 4.0.0-RC3
     */
    @Contract(value = "?, ?, ? -> new", impact = Contract.Impact.INTERNAL)
    public FunctionException(final @Nullable Throwable cause, final boolean suppression, final boolean writable) {
        super(DEFAULT_MESSAGE, cause, suppression, writable);
    }

    /**
     * Создаёт экземпляр этого исключения на основе переданных сообщения, причины, параметров подавления и трассировки
     * стека.
     *
     * @param message сообщение.
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 1.0.0
     */
    @Contract(value = "?, ?, ?, ? -> new", impact = Contract.Impact.INTERNAL)
    public FunctionException(final @Nullable String message, final @Nullable Throwable cause, final boolean suppression,
                             final boolean writable) {
        super(message, cause, suppression, writable);
    }

}
