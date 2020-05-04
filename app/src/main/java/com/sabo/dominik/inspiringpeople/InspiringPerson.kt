package com.sabo.dominik.inspiringpeople

import android.graphics.Bitmap

class InspiringPerson(val name: String, val time: String, val description: String, val quotes: ArrayList<String>, val picture: Bitmap) {

fun getRandomQuote(): String{
    quotes.shuffle()
    return quotes[0]
}

}