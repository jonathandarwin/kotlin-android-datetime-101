package com.jonathandarwin.androiddatetime101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parseDateTime()
        getCurrentDateTime()
        getDateDifference()
        addDate()
        compareDates()
    }

    private fun parseDateTime() {
        // Let's assume that this is the date that got from the server
        val before = "2020-07-12"

        // Create a parser and formatter with its pattern respectively
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // the initial pattern
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()) // the desired output pattern

        // Put the date in the parser, convert the string to Date class
        val parse = parser.parse(before)
        // Format the result with formatter, and put the result in var named "after"
        val result = formatter.format(parse!!)

        // Print the result
        Log.d("<RESULT>", "==============")
        Log.d("<RESULT>", "PARSE DATETIME")
        Log.d("<RESULT>", "==============")
        Log.d("<RESULT>", "before : $before")
        Log.d("<RESULT>", "after : $result")
    }

    private fun getCurrentDateTime() {
        // Get the current time (in millis)
        val now = Date().time

        // Create a formatter along with the desired output pattern
        val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault())

        // Put the time (in millis) in our formatter
        val result = formatter.format(now)

        // Print the result
        Log.d("<RESULT>", "====================")
        Log.d("<RESULT>", "GET CURRENT DATETIME")
        Log.d("<RESULT>", "====================")
        Log.d("<RESULT>", "Current Date Time : $result")
    }

    private fun getDateDifference() {
        // This 2 dates will be subtract soon
        val from = "2020-07-12 08:10:40"
        val to = "2020-07-15 10:00:00"

        // Here is the parser with the pattern
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        // Parse both of the date, and save it to variable
        val fromParse = formatter.parse(from)!!
        val toParse = formatter.parse(to)!!

        // Calculate the difference by subtract the time (in millis)
        var diff = fromParse.time - toParse.time
        // Validation
        if(diff < 0) diff *= -1

        // Because the difference is in the form of milliseconds, we should divide it first.
        val days = diff / (24 * 60 * 60 * 1000)
        // Get the remainder
        diff %= (24 * 60 * 60 * 1000)

        // Do this again as necessary.
        val hours = diff / (60 * 60 * 1000)
        diff %= (60 * 60 * 1000)
        val minutes = diff / (60 * 1000)
        diff %= (60 * 1000)
        val seconds = diff / 1000

        // Print the result
        Log.d("<RESULT>", "===================")
        Log.d("<RESULT>", "GET DATE DIFFERENCE")
        Log.d("<RESULT>", "===================")
        Log.d("<RESULT>", "from : $from")
        Log.d("<RESULT>", "to : $to")
        Log.d("<RESULT>", "difference : $days day(s) $hours hour(s) $minutes minute(s) $seconds second(s).")
    }

    private fun addDate() {
        // Prepare the date
        val date = "2020-07-30"

        // Parse the date
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateParse = parser.parse(date)

        // Set the date to Calendar
        val calendar = Calendar.getInstance()
        calendar.time = dateParse!!

        // Adding date by using calendar function
        calendar.add(Calendar.DATE, 2)

        // Format date back to string
        val output = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val result = output.format(calendar.time)

        Log.d("<RESULT>", "========")
        Log.d("<RESULT>", "ADD DATE")
        Log.d("<RESULT>", "========")
        Log.d("<RESULT>", "before add : $date")
        Log.d("<RESULT>", "after add : $result")
    }

    private fun compareDates() {
        // Prepare the dates
        val now = "2020-07-12 12:23:45"
        val deadline = "2020-07-12 20:32:54"

        // Create a parser along with the pattern
        val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        // Parse the dates and get the time (in millis)
        val nowTime = parser.parse(now)?.time
        val deadlineTime = parser.parse(deadline)?.time

        var result = "Cannot compare the dates"
        // Validate whether the time is null or not
        if(nowTime != null && deadlineTime != null){
            // Compare the time
            result = when {
                nowTime < deadlineTime -> "now < deadline"
                nowTime > deadlineTime -> "now > deadline"
                else -> "now = deadline"
            }
        }

        // Print the result
        Log.d("<RESULT>", "=============")
        Log.d("<RESULT>", "COMPARE DATES")
        Log.d("<RESULT>", "=============")
        Log.d("<RESULT>", "now      : $now")
        Log.d("<RESULT>", "deadline : $deadline")
        Log.d("<RESULT>", result)
    }
}
