package ru.otus.cars

class GasStation {

    fun refuel(car: Car, liters: Int, fuelType: FuelType) {
        try {
            car.refuel(liters, fuelType) /* Используем новую функцию refuel у Car */
        } catch (e: Exception) { /* Ловим исключения */
            println("Ошибка заправки: ${e.message}")
        }
    }


    fun refuelAll(cars: List<Car>) {
        cars.forEach {
            when (it) { /* Определяем тип машины и заправляем соответствующим топливом */
                is Vaz2107 -> refuel(it, 10, FuelType.GAS)
                is Vaz2108 -> refuel(it, 10, FuelType.PETROL)
                is Taz -> refuel(it, 10, FuelType.PETROL) /* Попытка заправить Taz */
                else -> println("Неизвестный тип машины")
            }
        }
    }
}