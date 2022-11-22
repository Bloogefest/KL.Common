package com.bloogefest.common.function;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

/**
 * Мягкое исключение потребления объекта, не требующее обязательного перехвата.
 *
 * @author Bloogefest
 * @version 0.1
 * @since 0.1.0
 */
@SuppressWarnings("unused")
public class ConsumeException extends FunctionException {

    /**
     * Конструктор, использующий стандартное сообщение.
     *
     * @author Bloogefest
     * @since 0.1.0
     */
    public ConsumeException() {}

    /**
     * Конструктор, использующий сообщение, переданное параметром.
     *
     * @param message Сообщение, используемое вместо стандартного.
     *
     * @author Bloogefest
     * @since 0.1.0
     */
    public ConsumeException(final @NonNls @Nullable String message) {
        super(message);
    }

    /**
     * Конструктор, использующий причину, переданную параметром.
     *
     * @param cause Причина возникновения исключения.
     *
     * @author Bloogefest
     * @since 0.1.0
     */
    public ConsumeException(final @Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Конструктор, использующий сообщение и причину, переданные параметрами.
     *
     * @param message Сообщение, используемое вместо стандартного.
     * @param cause   Причина возникновения исключения.
     *
     * @author Bloogefest
     * @since 0.1.0
     */
    public ConsumeException(final @NonNls @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Конструктор, использующий сообщение, причину, разрешение об упоминании подавленных исключений и разрешение для записи трассировки стека, переданные параметрами.
     *
     * @param message     Сообщение, используемое вместо стандартного.
     * @param cause       Причина возникновения исключения.
     * @param suppression Позволяет упоминать подавленные исключения.
     * @param writable    Позволяет трассировке стека записываться.
     *
     * @author Bloogefest
     * @since 0.1.0
     */
    protected ConsumeException(final @NonNls @Nullable String message, final @Nullable Throwable cause, final boolean suppression, final boolean writable) {
        super(message, cause, suppression, writable);
    }

}