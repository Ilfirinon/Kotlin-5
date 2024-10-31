package ru.otus.cars

/**
 * Следит за машиной
 */
interface CarOutput {
    /**
     * Скажи текущую скорость
     */
    fun getCurrentSpeed(): Int
    /* Добавляем функцию вывода уровня топлива для пользователя */
    fun getFuelV(): Int
}