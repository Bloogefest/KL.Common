/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.common.function;

import com.bloogefest.annotation.analysis.Contract;
import com.bloogefest.annotation.analysis.NotNull;
import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;

/**
 * Функциональный интерфейс логической функции.
 *
 * @since 1.0.0
 */
@FunctionalInterface
public interface Condition {

    /**
     * Инициализирует и возвращает логическую функцию, метод вычисления которой возвращает переданный в этот метод
     * результат вычисления.
     *
     * @param result результат вычисления.
     *
     * @return Новая логическая функция, метод вычисления которой возвращает переданный в этот метод результат
     * вычисления.
     *
     * @since 1.0.0
     */
    @Contract(value = "_ -> new")
    static @NotNull Condition constant(final boolean result) {
        return () -> result;
    }

    /**
     * Проверяет переданную логическую функцию и, если та нулевая, генерирует исключение валидации нулевого объекта
     * (переданной логической функции) с переопределённым сообщением (отформатированным именем переданной логической
     * функции шаблонным сообщением), в противном случае возвращает её.
     *
     * @param condition логическая функция.
     *
     * @return Переданная логическая функция.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной логической функции).
     * @apiNote Этот метод можно использовать для приведения лямбда-выражений к типу логической функции.
     * @since 1.0.0
     */
    @Contract(value = "_ -> param1")
    static @NotNull Condition of(final @NotNull Condition condition) throws NullException {
        return Validator.notNull(condition, "condition");
    }

    /**
     * Возвращает переданную логическую функцию.
     *
     * @param condition логическая функция.
     *
     * @return Переданная логическая функция.
     *
     * @apiNote Этот метод можно использовать для приведения лямбда-выражений к типу логической функции.
     * @since 4.0.0
     */
    @Contract(value = "_ -> param1")
    static @NotNull Condition as(final @NotNull Condition condition) {
        return condition;
    }

    /**
     * Вычисляет эту логическую функцию, инициализирует и возвращает результат её вычисления.
     *
     * @return Результат вычисления этой логической функции.
     *
     * @throws ComputeException исключение вычисления функции (этой логической функции).
     * @since 3.0.0
     */
    @Contract(pure = false)
    boolean compute() throws ComputeException;

    /**
     * Инициализирует и возвращает логическую функцию, метод вычисления которой сначала вызывает метод вычисления этой
     * логической функции, а после инвертирует и возвращает её результат вычисления.
     *
     * @return Новая логическая функция, метод вычисления которой сначала вызывает метод вычисления этой логической
     * функции, а после инвертирует и возвращает её результат вычисления.
     *
     * @since 1.0.0
     */
    @Contract(value = "-> new")
    default @NotNull Condition invert() {
        return () -> !compute();
    }

    /**
     * Проверяет переданную логическую функцию и, если та нулевая, генерирует исключение валидации нулевого объекта
     * (переданной логической функции) с переопределённым сообщением (отформатированным именем переданной логической
     * функции шаблонным сообщением), в противном случае инициализирует другую логическую функцию, метод вычисления
     * которой вызывает метод вычисления этой логической функции и, если её результат вычисления истинный, вызывает
     * метод вычисления переданной в этот метод логической функции, а после возвращает её результат вычисления, в
     * противном случае возвращает ложный результат вычисления.
     *
     * @param condition логическая функция.
     *
     * @return Новая логическая функция, метод вычисления которой вызывает метод вычисления этой логической функции и,
     * если её результат вычисления истинный, вызывает метод вычисления переданной в этот метод логической функции, а
     * после возвращает её результат вычисления, в противном случае возвращает ложный результат вычисления.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной логической функции).
     * @since 1.0.0
     */
    @Contract(value = "_ -> new")
    default @NotNull Condition and(final @NotNull Condition condition) throws NullException {
        Validator.notNull(condition, "condition");
        return () -> compute() && condition.compute();
    }

    /**
     * Проверяет переданную логическую функцию и, если та нулевая, генерирует исключение валидации нулевого объекта
     * (переданной логической функции) с переопределённым сообщением (отформатированным именем переданной логической
     * функции шаблонным сообщением), в противном случае инициализирует другую логическую функцию, метод вычисления
     * которой сначала вызывает метод вычисления этой логической функции, потом вызывает метод вычисления переданной в
     * этот метод логической функции и, если их результаты вычисления неравны, возвращает истинный результат вычисления,
     * в противном случае возвращает ложный результат вычисления.
     *
     * @param condition логическая функция.
     *
     * @return Новая логическая функция, метод вычисления которой сначала вызывает метод вычисления этой логической
     * функции, потом вызывает метод вычисления переданной в этот метод логической функции и, если их результаты
     * вычисления неравны, возвращает истинный результат вычисления, в противном случае возвращает ложный результат
     * вычисления.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной логической функции).
     * @since 1.0.0
     */
    @Contract(value = "_ -> new")
    default @NotNull Condition xor(final @NotNull Condition condition) throws NullException {
        Validator.notNull(condition, "condition");
        return () -> compute() ^ condition.compute();
    }

    /**
     * Проверяет переданную логическую функцию и, если та нулевая, генерирует исключение валидации нулевого объекта
     * (переданной логической функции) с переопределённым сообщением (отформатированным именем переданной логической
     * функции шаблонным сообщением), в противном случае инициализирует другую логическую функцию, метод вычисления
     * которой вызывает метод вычисления этой логической функции и, если её результат вычисления истинный, возвращает
     * истинный результат вычисления, в противном случае вызывает метод вычисления переданной в этот метод логической
     * функции, а после возвращает её результат вычисления.
     *
     * @param condition логическая функция.
     *
     * @return Новая логическая функция, метод вычисления которой вызывает метод вычисления этой логической функции и,
     * если её результат вычисления истинный, возвращает истинный результат вычисления, в противном случае вызывает
     * метод вычисления переданной в этот метод логической функции, а после возвращает её результат вычисления.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной логической функции).
     * @since 1.0.0
     */
    @Contract(value = "_ -> new")
    default @NotNull Condition or(final @NotNull Condition condition) throws NullException {
        Validator.notNull(condition, "condition");
        return () -> compute() || condition.compute();
    }

    /**
     * Проверяет переданную функцию обратного вызова и, если та нулевая, генерирует исключение валидации нулевого
     * объекта (переданной функции обратного вызова) с переопределённым сообщением (отформатированным именем переданной
     * функции обратного вызова шаблонным сообщением), в противном случае инициализирует и возвращает логическую
     * функцию, метод вычисления которой вызывает метод вычисления этой логической функции и, если результат её
     * вычисления истинный, вызывает функцию обратного вызова, а после либо в противном случае возвращает результат
     * вычисления этой логической функции.
     *
     * @param callback функция обратного вызова.
     *
     * @return Новая логическая функция, метод вычисления которой вызывает метод вычисления этой логической функции и,
     * если результат её вычисления истинный, вызывает функцию обратного вызова, а после либо в противном случае
     * возвращает результат вычисления этой логической функции.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной функции обратного вызова).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new")
    default @NotNull Condition then(final @NotNull Callback callback) throws NullException {
        Validator.notNull(callback, "callback");
        return () -> {
            final var result = compute();
            if (result) callback.call();
            return result;
        };
    }

    /**
     * Проверяет переданный поставщик и, если тот нулевой, генерирует исключение валидации нулевого объекта (переданного
     * поставщика) с переопределённым сообщением (отформатированным именем переданного поставщика шаблонным сообщением),
     * в противном случае инициализирует и возвращает логическую функцию, метод вычисления которой вызывает метод
     * вычисления этой логической функции и, если результат её вычисления истинный, генерирует исключение вычисления
     * функции (этой логической функции) с переопределённой причиной (полученным исключением либо ошибкой от переданного
     * в этот метод поставщика), в противном случае возвращает ложный результат вычисления.
     *
     * @param supplier поставщик исключения либо ошибки.
     *
     * @return Новая логическая функция, метод вычисления которой вызывает метод вычисления этой логической функции и,
     * если результат её вычисления истинный, генерирует исключение вычисления функции (этой логической функции) с
     * переопределённой причиной (полученным исключением либо ошибкой от переданного в этот метод поставщика), в
     * противном случае возвращает ложный результат вычисления.
     *
     * @throws NullException исключение валидации нулевого объекта (переданного поставщика).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new")
    default @NotNull Condition then(final @NotNull Supplier<? extends Throwable> supplier) throws NullException {
        Validator.notNull(supplier, "supplier");
        return () -> {
            if (compute()) throw new ComputeException(supplier.supply());
            return false;
        };
    }

    /**
     * Проверяет переданную функцию обратного вызова и, если та нулевая, генерирует исключение валидации нулевого
     * объекта (переданной функции обратного вызова) с переопределённым сообщением (отформатированным именем переданной
     * функции обратного вызова шаблонным сообщением), в противном случае инициализирует и возвращает логическую
     * функцию, метод вычисления которой вызывает метод вычисления этой логической функции и, если результат её
     * вычисления ложный, вызывает функцию обратного вызова, а после либо в противном случае возвращает результат
     * вычисления этой логической функции.
     *
     * @param callback функция обратного вызова.
     *
     * @return Новая логическая функция, метод вычисления которой вызывает метод вычисления этой логической функции и,
     * если результат её вычисления ложный, вызывает функцию обратного вызова, а после либо в противном случае
     * возвращает результат вычисления этой логической функции.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной функции обратного вызова).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new")
    default @NotNull Condition otherwise(final @NotNull Callback callback) throws NullException {
        Validator.notNull(callback, "callback");
        return () -> {
            final var result = compute();
            if (!result) callback.call();
            return result;
        };
    }

    /**
     * Проверяет переданный поставщик и, если тот нулевой, генерирует исключение валидации нулевого объекта (переданного
     * поставщика) с переопределённым сообщением (отформатированным именем переданного поставщика шаблонным сообщением),
     * в противном случае инициализирует и возвращает логическую функцию, метод вычисления которой вызывает метод
     * вычисления этой логической функции и, если результат её вычисления ложный, генерирует исключение вычисления
     * функции (этой логической функции) с переопределённой причиной (полученным исключением либо ошибкой от переданного
     * в этот метод поставщика), в противном случае возвращает истинный результат вычисления.
     *
     * @param supplier поставщик исключения либо ошибки.
     *
     * @return Новая логическая функция, метод вычисления которой вызывает метод вычисления этой логической функции и,
     * если результат её вычисления ложный, генерирует исключение вычисления функции (этой логической функции) с
     * переопределённой причиной (полученным исключением либо ошибкой от переданного в этот метод поставщика), в
     * противном случае возвращает истинный результат вычисления.
     *
     * @throws NullException исключение валидации нулевого объекта (переданного поставщика).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new")
    default @NotNull Condition otherwise(final @NotNull Supplier<? extends Throwable> supplier) throws NullException {
        Validator.notNull(supplier, "supplier");
        return () -> {
            if (!compute()) throw new ComputeException(supplier.supply());
            return true;
        };
    }

}
