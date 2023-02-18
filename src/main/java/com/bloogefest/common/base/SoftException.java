/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.common.base;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

/**
 * Тип мягкого исключения.
 *
 * @apiNote Данный тип исключения оповещает о несерьёзной проблеме, без решения которой корректное выполнение кода
 * возможно.
 * @since 0.3.0
 */
public class SoftException extends RuntimeException {

    /**
     * Сообщение по умолчанию.
     *
     * @since 0.3.0
     */
    protected static final @NonNls @Nullable String defaultMessage = null;

    /**
     * Причина по умолчанию.
     *
     * @since 0.3.0
     */
    protected static final @Nullable Throwable defaultCause = null;

    /**
     * Параметр подавления по умолчанию.
     *
     * @since 0.3.0
     */
    protected static final boolean defaultSuppression = false;

    /**
     * Параметр трассировки стека по умолчанию.
     *
     * @since 0.3.0
     */
    protected static final boolean defaultWritable = true;

    /**
     * Инициализирует экземпляр по умолчанию.
     *
     * @since 0.3.0
     */
    @Contract(pure = true)
    public SoftException() {
        super();
    }

    /**
     * Инициализирует экземпляр с переопределённым сообщением.
     *
     * @param message сообщение.
     *
     * @since 0.3.0
     */
    @Contract(pure = true)
    public SoftException(final @NonNls @Nullable String message) {
        super(message);
    }

    /**
     * Инициализирует экземпляр с переопределённой причиной.
     *
     * @param cause причина.
     *
     * @since 0.3.0
     */
    @Contract(pure = true)
    public SoftException(final @Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Инициализирует экземпляр с переопределённым сообщением и причиной.
     *
     * @param message сообщение.
     * @param cause причина.
     *
     * @since 0.3.0
     */
    @Contract(pure = true)
    public SoftException(final @NonNls @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Инициализирует экземпляр с переопределённым сообщением, причиной, параметром подавления и трассировки стека.
     *
     * @param message сообщение.
     * @param cause причина.
     * @param suppression параметр подавления.
     * @param writable параметр трассировки стека.
     *
     * @since 0.3.0
     */
    @Contract(pure = true)
    protected SoftException(final @NonNls @Nullable String message, final @Nullable Throwable cause,
                            final boolean suppression, final boolean writable) {
        super(message, cause, suppression, writable);
    }

}