/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.bloogefest.common.function;

import com.bloogefest.common.validation.NullException;
import com.bloogefest.common.validation.Validator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Функциональный интерфейс предикативной функции.
 *
 * @param <T> тип оцениваемого объекта.
 *
 * @since 1.0.0
 */
@FunctionalInterface
public interface Predicate<T> {

    /**
     * Инициализирует и возвращает предикативную функцию, метод оценивания которой возвращает переданный в этот метод
     * результат оценивания.
     *
     * @param result результат оценивания.
     *
     * @return Новая предикативная функция, метод оценивания которой возвращает переданный в этот метод результат
     * оценивания.
     *
     * @since 1.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    static <T> @NotNull Predicate<T> constant(final boolean result) {
        return ignored -> result;
    }

    /**
     * Проверяет переданную предикативную функцию и, если та нулевая, инициализирует и бросает исключение валидации
     * нулевого объекта (переданной предикативной функции) с переопределённым сообщением (отформатированным именем
     * переданной предикативной функции шаблонным сообщением), в противном случае возвращает её.
     *
     * @param predicate предикативная функция.
     *
     * @return Переданная предикативная функция.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной предикативной функции).
     * @apiNote Этот метод можно использовать для приведения лямбда-выражений к типу предикативной функции.
     * @since 1.0.0
     */
    @Contract(value = "_ -> param1", pure = true)
    static <T> @NotNull Predicate<T> of(final @NotNull Predicate<T> predicate) throws NullException {
        return Validator.notNull(predicate, "predicate");
    }

    /**
     * Оценивает переданный оцениваемый объект, инициализирует и возвращает результат его оценивания.
     *
     * @param object оцениваемый объект.
     *
     * @return Результат оценивания переданного оцениваемого объекта.
     *
     * @throws NullException исключение валидации нулевого объекта (переданного оцениваемого объекта).
     * @throws EvaluateException исключение оценивания объекта (переданного оцениваемого объекта).
     * @since 1.0.0
     */
    @Contract
    boolean evaluate(final @NotNull T object) throws NullException, EvaluateException;

    /**
     * Инициализирует и возвращает предикативную функцию, метод оценивания которой сначала вызывает метод оценивания
     * этой предикативной функции, используя передаваемый в него оцениваемый объект, а после инвертирует и возвращает её
     * результат оценивания.
     *
     * @return Новая предикативная функция, метод оценивания которой сначала вызывает метод оценивания этой
     * предикативной функции, используя передаваемый в него оцениваемый объект, а после инвертирует и возвращает её
     * результат оценивания.
     *
     * @since 1.0.0
     */
    @Contract(value = "-> new", pure = true)
    default @NotNull Predicate<T> invert() {
        return object -> !evaluate(object);
    }

    /**
     * Проверяет переданную предикативную функцию и, если та нулевая, инициализирует и бросает исключение валидации
     * нулевого объекта (переданной предикативной функции) с переопределённым сообщением (отформатированным именем
     * переданной предикативной функции шаблонным сообщением), в противном случае инициализирует другую предикативную
     * функцию, метод оценивания которой вызывает метод оценивания этой предикативной функции, используя передаваемый в
     * него оцениваемый объект и, если её результат оценивания истинный, вызывает метод оценивания переданной в этот
     * метод предикативной функции, вновь используя передаваемый в него оцениваемый объект, а после возвращает её
     * результат оценивания, в противном случае возвращает ложный результат оценивания.
     *
     * @param predicate предикативная функция.
     *
     * @return Новая предикативная функция, метод оценивания которой вызывает метод оценивания этой предикативной
     * функции, используя передаваемый в него оцениваемый объект и, если её результат оценивания истинный, вызывает
     * метод оценивания переданной в этот метод предикативной функции, вновь используя передаваемый в него оцениваемый
     * объект, а после возвращает её результат оценивания, в противном случае возвращает ложный результат оценивания.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной предикативной функции).
     * @since 1.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Predicate<T> and(final @NotNull Predicate<? super T> predicate) throws NullException {
        Validator.notNull(predicate, "predicate");
        return object -> evaluate(object) && predicate.evaluate(object);
    }

    /**
     * Проверяет переданную предикативную функцию и, если та нулевая, инициализирует и бросает исключение валидации
     * нулевого объекта (переданной предикативной функции) с переопределённым сообщением (отформатированным именем
     * переданной предикативной функции шаблонным сообщением), в противном случае инициализирует другую предикативную
     * функцию, метод оценивания которой сначала вызывает метод оценивания этой предикативной функции, используя
     * передаваемый в него оцениваемый объект, потом вызывает метод оценивания переданной в этот метод предикативной
     * функции, вновь используя передаваемый в него оцениваемый объект, и, если их результаты оценивания неравны,
     * возвращает истинный результат оценивания, в противном случае возвращает ложный результат оценивания.
     *
     * @param predicate предикативная функция.
     *
     * @return Новая предикативная функция, метод оценивания которой сначала вызывает метод оценивания этой
     * предикативной функции, используя передаваемый в него оцениваемый объект, потом вызывает метод оценивания
     * переданной в этот метод предикативной функции, вновь используя передаваемый в него оцениваемый объект, и, если их
     * результаты оценивания неравны, возвращает истинный результат оценивания, в противном случае возвращает ложный
     * результат оценивания.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной предикативной функции).
     * @since 1.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Predicate<T> xor(final @NotNull Predicate<? super T> predicate) throws NullException {
        Validator.notNull(predicate, "predicate");
        return object -> evaluate(object) ^ predicate.evaluate(object);
    }

    /**
     * Проверяет переданную предикативную функцию и, если та нулевая, инициализирует и бросает исключение валидации
     * нулевого объекта (переданной предикативной функции) с переопределённым сообщением (отформатированным именем
     * переданной предикативной функции шаблонным сообщением), в противном случае инициализирует другую предикативную
     * функцию, метод оценивания которой вызывает метод оценивания этой предикативной функции, используя передаваемый в
     * него оцениваемый объект, и, если её результат оценивания истинный, возвращает истинный результат оценивания, в
     * противном случае вызывает метод оценивания переданной в этот метод предикативной функции, вновь используя
     * передаваемый в него оцениваемый объект, а после возвращает её результат оценивания.
     *
     * @param predicate предикативная функция.
     *
     * @return Новая предикативная функция, метод оценивания которой вызывает метод оценивания этой предикативной
     * функции, используя передаваемый в него оцениваемый объект, и, если её результат оценивания истинный, возвращает
     * истинный результат оценивания, в противном случае вызывает метод оценивания переданной в этот метод предикативной
     * функции, вновь используя передаваемый в него оцениваемый объект, а после возвращает её результат оценивания.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной предикативной функции).
     * @since 1.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Predicate<T> or(final @NotNull Predicate<? super T> predicate) throws NullException {
        Validator.notNull(predicate, "predicate");
        return object -> evaluate(object) || predicate.evaluate(object);
    }

    /**
     * Проверяет переданную функцию обратного вызова и, если та нулевая, инициализирует и бросает исключение валидации
     * нулевого объекта (переданной функции обратного вызова) с переопределённым сообщением (отформатированным именем
     * переданной функции обратного вызова шаблонным сообщением), в противном случае инициализирует и возвращает
     * предикативную функцию, метод оценивания которой вызывает метод оценивания этой предикативной функции и, если её
     * результат оценивания истинный, вызывает функцию обратного вызова, а после либо в противном случае возвращает
     * результат оценивания этой предикативной функции.
     *
     * @param callback функция обратного вызова.
     *
     * @return Новая предикативная функция, метод оценивания которой вызывает метод оценивания этой предикативной
     * функции и, если её результат оценивания истинный, вызывает функцию обратного вызова, а после либо в противном
     * случае возвращает результат оценивания этой предикативной функции.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной функции обратного вызова).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Predicate<T> then(final @NotNull Callback callback) throws NullException {
        Validator.notNull(callback, "callback");
        return object -> {
            final var result = evaluate(object);
            if (result) callback.call();
            return result;
        };
    }

    /**
     * Проверяет переданный обработчик и, если тот нулевой, инициализирует и бросает исключение валидации нулевого
     * объекта (переданного обработчика) с переопределённым сообщением (отформатированным именем переданного обработчика
     * шаблонным сообщением), в противном случае инициализирует и возвращает предикативную функцию, метод оценивания
     * которой вызывает метод оценивания этой предикативной функции, используя переданный в него оцениваемый объект, и,
     * если её результат оценивания истинный, вызывает функцию обработки (функцию обработки переданного обработчика),
     * используя переданный в него оцениваемый объект, а после возвращает результат оценивания этой предикативной
     * функции.
     *
     * @param handler обработчик объекта.
     *
     * @return Новая предикативная функция, метод оценивания которой вызывает метод оценивания этой предикативной
     * функции, используя переданный в него оцениваемый объект, и, если её результат оценивания истинный, вызывает
     * функцию обработки (функцию обработки переданного обработчика), используя переданный в него оцениваемый объект, а
     * после возвращает результат оценивания этой предикативной функции.
     *
     * @throws NullException исключение валидации нулевого объекта (переданного обработчика).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Predicate<T> then(final @NotNull Handler<T> handler) throws NullException {
        Validator.notNull(handler, "handler");
        return object -> {
            final var result = evaluate(object);
            if (result) handler.handle(object);
            return result;
        };
    }

    /**
     * Проверяет переданный поставщик и, если тот нулевой, инициализирует и бросает исключение валидации нулевого
     * объекта (переданного поставщика) с переопределённым сообщением (отформатированным именем переданного поставщика
     * шаблонным сообщением), в противном случае инициализирует и возвращает предикативную функцию, метод оценивания
     * которой вызывает метод оценивания этой предикативной функции и, если её результат оценивания истинный,
     * инициализирует и бросает исключение оценивания объекта (переданного в этот метод оцениваемого объекта) с
     * переопределённой причиной (полученным исключением либо ошибкой от переданного в этот метод поставщика), в
     * противном случае возвращает ложный результат оценивания.
     *
     * @param supplier поставщик исключения либо ошибки.
     *
     * @return Новая предикативная функция, метод оценивания которой вызывает метод оценивания этой предикативной
     * функции и, если её результат оценивания истинный, инициализирует и бросает исключение оценивания объекта
     * (переданного в этот метод оцениваемого объекта) с переопределённой причиной (полученным исключением либо ошибкой
     * от переданного в этот метод поставщика), в противном случае возвращает ложный результат оценивания.
     *
     * @throws NullException исключение валидации нулевого объекта (переданного поставщика).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Predicate<T> then(final @NotNull Supplier<? extends Throwable> supplier) throws NullException {
        Validator.notNull(supplier, "supplier");
        return object -> {
            if (evaluate(object)) throw new EvaluateException(supplier.supply());
            return false;
        };
    }

    /**
     * Проверяет переданную функцию обратного вызова и, если та нулевая, инициализирует и бросает исключение валидации
     * нулевого объекта (переданной функции обратного вызова) с переопределённым сообщением (отформатированным именем
     * переданной функции обратного вызова шаблонным сообщением), в противном случае инициализирует и возвращает
     * предикативную функцию, метод оценивания которой вызывает метод оценивания этой предикативной функции и, если её
     * результат оценивания ложный, вызывает функцию обратного вызова, а после либо в противном случае возвращает
     * результат оценивания этой предикативной функции.
     *
     * @param callback функция обратного вызова.
     *
     * @return Новая предикативная функция, метод оценивания которой вызывает метод оценивания этой предикативной
     * функции и, если её результат оценивания ложный, вызывает функцию обратного вызова, а после либо в противном
     * случае возвращает результат оценивания этой предикативной функции.
     *
     * @throws NullException исключение валидации нулевого объекта (переданной функции обратного вызова).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Predicate<T> otherwise(final @NotNull Callback callback) throws NullException {
        Validator.notNull(callback, "callback");
        return object -> {
            final var result = evaluate(object);
            if (!result) callback.call();
            return result;
        };
    }

    /**
     * Проверяет обработчик и, если тот нулевой, инициализирует и бросает исключение валидации нулевого объекта
     * (обработчика) с переопределённым сообщением (отформатированным именем обработчика шаблонным сообщением), в
     * противном случае инициализирует предикат, метод оценивания которого проверяет результат оценивания этого
     * предиката и, если тот ложный, вызывает функцию обработки объекта (функцию обработчика) с оцениваемым объектом
     * этого предиката, а после возвращает результат оценивания этого предиката.
     *
     * @param handler обработчик объекта.
     *
     * @return Предикат с результатом оценивания этого предиката, при ложности которого вызывается функция обработки
     * объекта (функцию обработчика) с оцениваемым объектом этого предиката.
     *
     * @throws NullException исключение валидации нулевого объекта (обработчика).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Predicate<T> otherwise(final @NotNull Handler<T> handler) throws NullException {
        Validator.notNull(handler, "handler");
        return object -> {
            final var result = evaluate(object);
            if (!result) handler.handle(object);
            return result;
        };
    }

    /**
     * Проверяет поставщик и, если тот нулевой, инициализирует и бросает исключение валидации нулевого объекта
     * (поставщика) с переопределённым сообщением (отформатированным именем поставщика шаблонным сообщением), в
     * противном случае инициализирует предикат, метод оценивания которого проверяет результат оценивания этого
     * предиката и, если тот ложный, инициализирует и бросает исключение оценивания объекта (объекта этого предиката) с
     * переопределённой причиной (полученным исключением либо ошибкой от поставщика), в противном случае возвращает
     * истинный результат оценивания этого предиката.
     *
     * @param supplier поставщик исключения либо ошибки.
     *
     * @return Предикат с результатом оценивания этого предиката, при ложности которого инициализируется и бросается
     * исключение оценивания объекта (объекта этого предиката) с переопределённой причиной (полученным исключением либо
     * ошибкой от поставщика).
     *
     * @throws NullException исключение валидации нулевого объекта (поставщика).
     * @since 4.0.0
     */
    @Contract(value = "_ -> new", pure = true)
    default @NotNull Predicate<T> otherwise(
            final @NotNull Supplier<? extends Throwable> supplier) throws NullException {
        Validator.notNull(supplier, "supplier");
        return object -> {
            if (!evaluate(object)) throw new ComputeException(supplier.supply());
            return true;
        };
    }

}
