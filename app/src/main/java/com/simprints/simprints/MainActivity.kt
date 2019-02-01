package com.simprints.simprints

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


//puzzle 3 been solved
class MainActivity : AppCompatActivity() {

    val spaceValue = 32

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val values = arrayListOf<String>(
            resources.getString(R.string.relentless_commitment_to_impact),
            resources.getString(R.string.robust_as_fudge),
            resources.getString(R.string.be_surprisingly_bold),
            resources.getString(R.string.get_back_up),
            resources.getString(R.string.make_it_happen),
            resources.getString(R.string.dont_be_a_jerk),
            resources.getString(R.string.confront_the_grey),
            resources.getString(R.string.laugh_together_grow_together))

        showValuesButton.setOnClickListener {
            resultsView.removeAllViews()
            showValues(values)
        }

    }

    private fun showValues(values: ArrayList<String>){
        var map : MutableMap<Long, String> = mutableMapOf()
        for(value in values){
            map.put(ASCIIStringSum(value), value)

        }

        val sorted = map.toSortedMap(reverseOrder())
        for(item in sorted){
            val textView = TextView(this)
            textView.text = item.value.toString()
            textView.gravity = Gravity.CENTER_HORIZONTAL
            textView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
            resultsView.addView(textView)
        }
    }

    private fun ASCIIStringSum(str: String): Long {
        var substringCount = 0
        for (i: Int in 0..str.length - 1) {
            if (str.get(i) == ' ') substringCount++
        }
        return ASCIIWordSum(str, LongArray(substringCount + 1)) + spaceValue * substringCount
    }

    private fun ASCIIWordSum(str: String, sumArr: LongArray): Long {
        var index = 0
        var sum: Long = 0
        var totalSum: Long = 0
        for (i: Int in 0..str.length - 1) {
            if (str[i] == ' ') {
                totalSum += sum
                sumArr[index++] = sum
                sum = 0
            } else
                sum += str[i].toLong()
        }

        sumArr[index] = sum
        totalSum += sum
        return totalSum
    }

}
