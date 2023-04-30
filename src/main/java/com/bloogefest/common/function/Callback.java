/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.common.function;

import com.bloogefest.annotation.analysis.*;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

/**
 * Функциональный интерфейс функции обратного вызова.
 *
 * @since 2.0.0
 */
@FunctionalInterface
public interface Callback {

    /**
     * Инициализирует и возвращает функцию обратного вызова, метод вызова которой ничего не делает.
     *
     * @return Новая функция обратного вызова, метод вызова которой ничего не делает.
     *
     * @since 2.0.0
     */
    @Contract(value = "-> new")
    static @NotNull Callback empty() {
        return () -> {};
    }

    /**
     * Проверяет переданную функцию обратного вызова и, если та нулевая, генерирует исключение валидации нулевого
     * объекта (переданной функции обратного вызова) с переопределённым сообщением (отформатированным именем переданной
     * функции обратного вызова шаблонным сообщением), в противном случае возвращает её.
     *
     * @param callback функция обратного вызова.
     *
     * @return Переданная функция обратного вызова.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной функции обратного вызова).
     * @apiNote Этот метод можно использовать для приведения лямбда-выражений к типу функции обратного вызова.
     * @since 4.0.0
     */
    @Contract("!null -> 1; _ -> failure")
    static @NotNull Callback check(final @Nullable Callback callback) throws NullException {
        return Validator.notNull(callback, "callback");
    }

    /**
     * Проверяет переданную функцию обратного вызова и, если та нулевая, генерирует исключение валидации нулевого
     * объекта (переданной функции обратного вызова) с переопределённым сообщением (отформатированным именем переданной
     * функции обратного вызова шаблонным сообщением), в противном случае возвращает её.
     *
     * @param callback функция обратного вызова.
     *
     * @return Переданная функция обратного вызова.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной функции обратного вызова).
     * @apiNote Этот метод можно использовать для приведения лямбда-выражений к типу функции обратного вызова.
     * @since 2.0.0
     * @deprecated Используйте {@linkplain #check(Callback)}.
     */
    @Removal("4.0.0-RC4")
    @Obsolete("com.bloogefest.common.function.Callback.check")
    @Contract("_ -> 1")
    static @NotNull Callback of(final @NotNull Callback callback) throws NullException {
        return Validator.notNull(callback, "callback");
    }

    /**
     * Возвращает переданную функцию обратного вызова.
     *
     * @param callback функция обратного вызова.
     *
     * @return Переданная функция обратного вызова.
     *
     * @apiNote Этот метод можно использовать для приведения лямбда-выражений к типу функции обратного вызова.
     * @since 4.0.0
     */
    @Contract(value = "_ -> param1")
    static @NotNull Callback as(final @NotNull Callback callback) {
        return callback;
    }

    /**
     * Проверяет переданную функцию обратного вызова и, если та ненулевая, возвращает её, в противном случае вызывает
     * метод инициализации функции обратного вызова, метод вызова которой ничего не делает, а после возвращает его
     * результирующий объект (требуемую функцию обратного вызова).
     *
     * @param callback функция обратного вызова.
     *
     * @return Переданная либо новая функция обратного вызова, метод вызова которой ничего не делает.
     *
     * @apiNote Этот метод можно использовать для приведения лямбда-выражений к типу функции обратного вызова.
     * @since 3.0.0
     */
    @Contract(value = "!null -> param1; _ -> new")
    static @NotNull Callback auto(final @Nullable Callback callback) {
        return callback != null ? callback : empty();
    }

    /**
     * Вызывает эту функцию обратного вызова.
     *
     * @throws CallException исключение вызова функции (этой функции обратного вызова).
     * @since 2.0.0
     */
    @Contract(pure = false)
    void call() throws CallException;

    /**
     * Проверяет переданную функцию обратного вызова и, если та нулевая, генерирует исключение валидации нулевого
     * объекта (переданной функции обратного вызова) с переопределённым сообщением (отформатированным именем переданной
     * функции обратного вызова шаблонным сообщением), в противном случае инициализирует и возвращает другую функцию
     * обратного вызова, метод вызова которой сначала вызывает метод вызова этой функции обратного вызова, а после
     * вызывает метод вызова переданной в этот метод функции обратного вызова.
     *
     * @param callback функция обратного вызова.
     *
     * @return Новая функция обратного вызова, метод вызова которой сначала вызывает метод вызова этой функции обратного
     * вызова, а после вызывает метод вызова переданной в этот метод функции обратного вызова.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной функции обратного вызова).
     * @since 2.0.0
     */
    @Contract(value = "_ -> new")
    default @NotNull Callback with(final @NotNull Callback callback) throws NullException {
        Validator.notNull(callback, "callback");
        return () -> {
            call();
            callback.call();
        };
    }

}
