package ru.otus.cars
/* Интерфейс бака доступный для пользователя */
interface Tank {
/* Горловина */
    val mouth: TankMouth
/* Функция получения кол-ва топлива */
    fun FuelLevel(): Int
/* Функция заправки топливом */
    fun FillFuel(liters: Int)
}