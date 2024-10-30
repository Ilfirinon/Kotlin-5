package ru.otus.cars

fun main() {
    println("\n===> drive cars...")
    driveCars()
    println("\n===> inner test...")
    innerNestedCheck()
    println("\n===> garage make...")
    garageMake()
    println("\n===> model special...")
    println("\n===> get equipment...")
    getEquipment()
    println("\n===> get color...")
    getColor()
    println("\n===> tech checks...")
    techChecks()
    println("\n===> Taz...")
    println(Taz.color)
}

fun driveCars() {
    val vaz1 = Togliatti.buildCar(Vaz2107, Car.Plates("123", 77))
    val vaz2 = Togliatti.buildCar(Vaz2108, Car.Plates("321", 78))

    println("Экземпляры класса имеют разное внутреннее состояние:")
    vaz1.wheelToRight(10)
    println(vaz1.toString()) // Выводит 10 и случайную скорость
    vaz2.wheelToLeft(20)
    println(vaz2.toString()) // Выводит -20 и случайную скорость
}

fun innerNestedCheck() {
    val vaz = Vaz2107.build(Car.Plates("123", 77))
    val output = vaz.VazOutput() // Создаем новый объект ИЗ ЭКЗЕМПЛЯРА МАШИНЫ

    println("Скорость до проверки: ${output.getCurrentSpeed()}") // Выводит 0
    Vaz2107.test(vaz) // Газуем...
    println("Скорость после проверки: ${output.getCurrentSpeed()}") // Выводит случайную скорость
}

fun garageMake() {
    val maker = "Дядя Вася"
    val garage = object : CarFactory {
        override fun buildCar(builder: CarBuilder, plates: Car.Plates): Car {
            println("Запил Жигулей у: $maker...")
            println("Машину не проверяем... и в продакшн...")
            return builder.build(plates)
        }
    }

    val vaz = garage.buildCar(Vaz2107, Car.Plates("500", 50))
    println(vaz.toString())
}

fun getEquipment() {
    val cars = listOf(
        Vaz2107.build(Car.Plates("123", 77)),
        Vaz2108.build(Car.Plates("321", 78))
    )

    cars.forEach { car ->
        println("Оборудование: ${car.getEquipment()}")
    }
}

fun getColor() {
    val cars = listOf(
        Vaz2107.build(Car.Plates("123", 77)),
        Vaz2108.build(Car.Plates("321", 78))
    )

    cars.forEach { car ->
        println("Цвет: ${car.color}")
    }
}

fun techChecks() {
    val vaz1 = Vaz2107.build(Car.Plates("123", 77))
    val vaz2 = Vaz2108.build(Car.Plates("321", 78))

    repairEngine(vaz1)
    repairEngine(vaz2)
}

fun repairEngine(car: VazPlatform) {
    // Проверяем тип двигателя
    // В зависимости от типа двигателя выполняем разные действия
    // when обеспечивает обход всех вариантов перечисления
    when (car.engine) {
        is VazEngine.LADA_2107 -> println("Чистка карбюратора у двигателя объемом ${car.engine.volume} куб.см у машины $car")
        is VazEngine.SAMARA_2108 -> println("Угол зажигания у двигателя объемом ${car.engine.volume} куб.см у машины $car")
    }
}

interface Tank {
    fun fill(fuel: Int)
    fun getLevel(): Int
}

interface TankMouth {
    fun fillTank(tank: Tank, fuel: Int)
}

class GasTankMouth : TankMouth {
    override fun fillTank(tank: Tank, fuel: Int) {
        println("Заправка газом")
        tank.fill(fuel)
    }
}

class PetrolTankMouth : TankMouth {
    override fun fillTank(tank: Tank, fuel: Int) {
        println("Заправка бензином")
        tank.fill(fuel)
    }
}


class RegularTank(var capacity: Int = 100) : Tank {
    private var level = 0

    override fun fill(fuel: Int) {
        val availableSpace = capacity - level
        val actualFill = minOf(fuel, availableSpace)
        level += actualFill
    }

    override fun getLevel(): Int = level

}

class ExplodingTank() : Tank {
    private var level = 0

    override fun fill(fuel: Int) {
        throw Exception("Взрыв бака!")
    }

    override fun getLevel(): Int = level
}


class CarSeven : CarOutput {
    private val tank = RegularTank()
    private val tankMouth = GasTankMouth()


    fun refuel(fuel: Int) {
        tankMouth.fillTank(tank, fuel)
    }

    override fun getFuelLevel(): Int = tank.getLevel()


    override fun printStatus() {
        println("Семерка: Уровень топлива: ${getFuelLevel()}")
    }

    override fun toString(): String {
        return "Семерка: Уровень топлива: ${getFuelLevel()}"
    }
    override fun getCurrentSpeed(): String {
        return "Семерка: Скорость: ${getCurrentSpeed()}"
    }
}

class CarEight : CarOutput {
    private val tank = RegularTank()
    private val tankMouth = PetrolTankMouth()

    fun refuel(fuel: Int) {
        tankMouth.fillTank(tank, fuel)
    }

    override fun getFuelLevel(): Int = tank.getLevel()

    override fun printStatus() {
        println("Восьмерка: Уровень топлива: ${getFuelLevel()}")
    }

    override fun toString(): String {
        return "Восьмерка: Уровень топлива: ${getFuelLevel()}"
    }
    override fun getCurrentSpeed(): String {
        return "Восьмерка: Скорость: ${getCurrentSpeed()}"
    }


    class TAZ : CarOutput {
        private val tank = ExplodingTank()
        private val tankMouth = PetrolTankMouth()


        fun refuel(fuel: Int) {
            try {
                tankMouth.fillTank(tank, fuel)
            } catch (e: Exception) {
                println("ТАЗ: ${e.message}")
            }
        }

        override fun getFuelLevel(): Int = tank.getLevel()


        override fun printStatus() {
            println("TAZ: Уровень топлива: ${getFuelLevel()}")
        }

        override fun toString(): String {
            return "TAZ: Уровень топлива: ${getFuelLevel()}"
        }
        override fun getCurrentSpeed(): String {
            return "TAZ: Скорость: ${getCurrentSpeed()}"
        }
    }



    fun refuelCar(car: CarOutput, fuel: Int) {
        try {
            if(car is CarSeven) {
                car.refuel(fuel)
            } else if(car is CarEight){
                car.refuel(fuel)
            } else if(car is TAZ){
                car.refuel(fuel)
            } else {
                println("Неизвестный тип машины")
            }


        } catch (e: Exception) {
            println("Ошибка заправки: ${e.message}")
        }
    }

    fun refuelCars(cars: List<CarOutput>, fuel: Int) {
        cars.forEach {
            refuelCar(it, fuel)
        }
    }

    fun GasStation() {
        val cars = listOf(CarSeven(), CarEight(), TAZ())

        println("Состояние баков до заправки:")
        cars.forEach { println(it) }

        refuelCars(cars, 50)

        println("Состояние баков после заправки:")
        cars.forEach { println(it) }
    }
}