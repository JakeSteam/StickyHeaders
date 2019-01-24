package uk.co.jakelee.stickyheadersdemo

import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

class Randomiser {
    companion object {
        fun int(min: Int, max: Int): Int {
            return (min..max).shuffled().last()
        }

        fun date(): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -int(0, 1000))
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
            return format.format(calendar.time)
        }

        fun word() = ('a'..'z').map { it }.shuffled().subList(0, 8).joinToString("").capitalize()

        fun message() = UUID.randomUUID().toString()

        fun colour(): Int {
            val rnd = Random()
            return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        }
    }
}