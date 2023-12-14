package yandexTaxi

import java.util.*
import kotlin.collections.ArrayList

class YandexTaxi {
    private var driverList = ArrayList<Driver>()
    private var passengerList = ArrayList<Passenger>()
    private var monitorList = ArrayList<Monitor>()

    private val scanner = Scanner(System.`in`)


    fun driverMenu() {
        var a = true
        while (a) {
            println("1 -> Ro'yxatdan o'tish; 2 -> Tizimga kirish; 3 -> Chiqish")
            when (scanner.nextInt()) {
                1 -> {
                    print("Ismingiz : ")
                    val name = scanner.next()

                    print("Mashinangizni raqami : ")
                    val carNumber = scanner.next()

                    print("Mashinagizni nomi : ")
                    val carName = scanner.next()

                    println("Mashinangiz uchun tarif tanlang :")
                    val values = StatusType.values()
                    for (i in values.indices) {
                        println("${i + 1} -> ${values[i]}")
                    }
                    val n = scanner.nextInt() - 1
                    val statusType = values[n]

                    print("Telefon raqamingiz : ")
                    val phoneNumber = scanner.next()

                    print("Parolingiz : ")
                    val password = scanner.next()

                    driverList.add(Driver(name, carNumber, carName, statusType, password, phoneNumber))
                    println("Muvaffaqiyatli ro'yxatdan o'tkazildi!!!")

                }
                2 -> {
                    print("Telefon raqamingiz : ")
                    val phoneNumber = scanner.next()

                    print("Parolingiz : ")
                    val password = scanner.next()

                    var index = -1
                    for (i in driverList.indices) {
                        if (driverList[i].password.equals(password) && driverList[i].phoneNumber.equals(phoneNumber)) {
                            index = i
                            break
                        }
                    }
                    if (index == -1) {
                        println("Haydovchi topilmadi!!!")
                    } else {
                        println("${driverList[index].name} - ${driverList[index].carName} - ${driverList[index].carNumber} xush kelibsiz!!!")
                        driverTaxi(index)
                    }
                }
                3 -> {
                    a = false
                }
            }
        }
    }

    private fun driverTaxi(index: Int) {
        var a = true
        val driver = driverList[index]
        while (a) {
            println("1 -> Yo'lovchini manzilga eltib qo'yish; 2 -> Hozirgi yo'lovchi ma'lumotlari; 3 -> Chiqish")
            when (scanner.nextInt()) {
                1 -> {
                    for (i in monitorList.indices) {
                        if (monitorList[i].driver == driver && !monitorList[i].isFinished!!) {
                            monitorList[i].isFinished = true
                        }
                    }
                }
                2 -> {
                    for (i in monitorList.indices) {
                        if (monitorList[i].driver == driver && !monitorList[i].isFinished!!) {
                            println(monitorList[i].passenger)
                        }
                    }
                }
                3 -> a = false
            }
        }

    }

    private fun bookingTaxi(index: Int) {
        var a = true
        while (a) {
            println("1 -> taxi chaqirish; 2 -> chiqish")
            when (scanner.nextInt()) {
                1 -> {
                    val passenger = passengerList[index]
                    val values = StatusType.values()
                    println("Tarifni tanlang : ")
                    for (i in values.indices) {
                        println("${i + 1} -> ${values[i]}")
                    }
                    val n = scanner.nextInt() - 1
                    val statusType = values[n]

                    var freeDriverList = ArrayList<Driver>()
                    for (i in driverList.indices) {
                        if (driverList[i].statusType == statusType && driverList[i].isFree == true) {
                            freeDriverList.add(driverList[i])
                        }
                    }
                    println("Mashinani tanlang :")
                    for (i in freeDriverList.indices) {
                        println("${i + 1} -> ${freeDriverList[i]}")
                    }
                    val n2 = scanner.nextInt() - 1
                    val driver = freeDriverList[n2]
                    driver.isFree = false

                    print("Turgan joyingiz : ")
                    val currentPlace = scanner.next()
                    print("Bormoqchi bo'lgan manzilingiz : ")
                    val targetPlace = scanner.next()

                    monitorList.add(Monitor(driver, passenger, currentPlace, targetPlace))

                }
                2 -> {
                    a = false
                }
            }
        }
    }

    fun passengerMenu() {
        var a = true
        while (a) {
            println("1 -> Ro'yxatdan o'tish; 2 -> Tizimga kirish; 3 -> Chiqish")
            when (scanner.nextInt()) {
                1 -> {
                    print("Ismingiz : ")
                    val name = scanner.next()

                    print("Telefon raqamingiz : ")
                    val phoneNumber = scanner.next()

                    print("Parolingiz : ")
                    val password = scanner.next()

                    passengerList.add(Passenger(name, phoneNumber, password))
                    println("Muvaffaqiyatli ro'yxatdan o'tkazildi!!!")
                }
                2 -> {
                    print("Telefon raqamingiz : ")
                    val phoneNumber = scanner.next()

                    print("Parolingiz : ")
                    val password = scanner.next()

                    var index = -1
                    for (i in passengerList.indices) {
                        if (passengerList[i].password.equals(password) && passengerList[i].phoneNumber.equals(
                                phoneNumber
                            )
                        ) {
                            index = i
                            break
                        }
                    }
                    if (index == -1) {
                        println("Yo'lovchi topilmadi!!!")
                    } else {
                        println("${passengerList[index].name} - ${passengerList[index].phoneNumber} xush kelibsiz!!!")
                        bookingTaxi(index)
                    }

                }
                3 -> a = false
            }

        }
    }

    fun monitoring() {
        monitorList.forEach {
            println(it)
        }
    }


    private inner class Driver {
        var name: String? = null
        var carNumber: String? = null
        var carName: String? = null
        var statusType: StatusType? = null
        var password: String? = null
        var phoneNumber: String? = null
        var isFree: Boolean? = true

        constructor(
            name: String?,
            carNumber: String?,
            carName: String?,
            statusType: StatusType?,
            password: String?,
            phoneNumber: String?
        ) {
            this.name = name
            this.carNumber = carNumber
            this.carName = carName
            this.statusType = statusType
            this.password = password
            this.phoneNumber = phoneNumber
        }

        override fun toString(): String {
            return "Driver(name=$name, carNumber=$carNumber, carName=$carName, statusType=$statusType, phoneNumber=$phoneNumber, isFree=$isFree)"
        }


    }

    private inner class Passenger {
        var name: String? = null
        var phoneNumber: String? = null
        var password: String? = null

        constructor(name: String?, phoneNumber: String?, password: String?) {
            this.name = name
            this.phoneNumber = phoneNumber
            this.password = password
        }

        override fun toString(): String {
            return "Passenger(name=$name, phoneNumber=$phoneNumber"
        }

    }

    private inner class Monitor {
        var driver: Driver? = null
        var passenger: Passenger? = null
        var currentPlace: String? = null
        var targetPlace: String? = null
        var isFinished: Boolean? = false

        constructor(driver: Driver?, passenger: Passenger?, currentPlace: String?, targetPlace: String?) {
            this.driver = driver
            this.passenger = passenger
            this.currentPlace = currentPlace
            this.targetPlace = targetPlace
        }

        override fun toString(): String {
            return "Monitor(driver=$driver, passenger=$passenger, currentPlace=$currentPlace, targetPlace=$targetPlace, isFinished=$isFinished)"
        }


    }


}