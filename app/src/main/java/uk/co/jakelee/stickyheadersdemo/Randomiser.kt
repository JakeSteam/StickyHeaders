package uk.co.jakelee.stickyheadersdemo

import android.graphics.Color
import java.util.*

class Randomiser {
    companion object {
        fun int(min: Int, max: Int): Int {
            return (min..max).shuffled().last()
        }

        fun date(): String = Date(Math.abs(System.currentTimeMillis() - Random().nextLong())).toString()

        fun message() = UUID.randomUUID().toString();

        fun colour(): Int {
            val rnd = Random()
            return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        }
    }
}