package yandexTaxi

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val yandexTaxi = YandexTaxi()
    var a = true
    while (a){
        println("Menyulardan birini tanlang :")
        val values = UserType.values()
        for (i in values.indices){
            println("${i+1} -> ${values[i]}")
        }
        println("3 -> Monitoring\n4 -> Tizimdan chiqish")
        val n = scanner.nextInt()

        when(n){
            0->{
                a=false
            }
            1->{
                yandexTaxi.driverMenu()
            }
            2->{
                yandexTaxi.passengerMenu()
            }
            3->{
                yandexTaxi.monitoring()
            }
        }
    }
}