package com.letit0or1.akimaleo.eyedoctor.colorblind

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import ch.halcyon.squareprogressbar.SquareProgressBar
import com.letit0or1.akimaleo.eyedoctor.R
import com.letit0or1.akimaleo.eyedoctor.colorblind.entity.DataCollection
import com.letit0or1.akimaleo.eyedoctor.colorblind.entity.DataItem
import java.util.*

class ColorBlindDiagnosticActivity : AppCompatActivity() {
    //Data collection
    private var mDataSet: ArrayList<DataItem>? = null
    //image view and progressbar
    private var mProgressBar: SquareProgressBar? = null
    private var mCurrentSlide = 0
    private var first: Button? = null
    private var second: Button? = null
    private var third: Button? = null
    private var fourth: Button? = null
    private var fullBlindCount = 0
    private var red_greenBlind = 0
    private var normal = 0
    private var fake = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //displaying layout
        setContentView(R.layout.activity_color_blind_diagnostic)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        //get data
        mDataSet = DataCollection.getInstance().data
        Collections.shuffle(mDataSet!!)
        //init progressbar
        mProgressBar = findViewById(R.id.progressbar) as SquareProgressBar

        first = findViewById(R.id.first) as Button
        second = findViewById(R.id.second) as Button
        third = findViewById(R.id.third) as Button
        fourth = findViewById(R.id.fourth) as Button

        //start diagnostic
        startSlideshow()
    }


    internal fun startSlideshow() {
        nextImage()
    }

    internal fun setButtons(item: DataItem) {
        val right = item.answer[0]
        val green_red = item.answer[1]
        val full = item.answer[2]

        val btns = ArrayList<Button?>()
        btns.add(first)
        btns.add(second)
        btns.add(third)
        btns.add(fourth)

        Collections.shuffle(btns)
        btns[0]?.text = if (right == 0) getString(R.string.button_nothing) else right.toString() + ""
        btns[0]?.setOnClickListener {
            normal++
            nextImage()
        }
        btns[1]?.text = green_red.toString() + ""
        btns[1]?.setOnClickListener {
            red_greenBlind++
            nextImage()
        }
        if (right == 0)
            btns[2]?.text = Random().nextInt(85).toString() + ""
        else
            btns[2]?.text = if (full == 0) getString(R.string.button_nothing) else full.toString() + ""
        btns[2]?.setOnClickListener {
            fullBlindCount++
            nextImage()
        }
        btns[3]?.text = Random().nextInt(85).toString() + ""
        btns[3]?.setOnClickListener {
            fake++
            nextImage()
        }

    }

    internal fun nextImage() {
        if (mCurrentSlide == mDataSet!!.size) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("The result").setMessage(result()).setPositiveButton("ok") { dialogInterface, i -> finish() }.show()
            return
        }
        val i = mDataSet!![mCurrentSlide++]
        mProgressBar!!.setImage(i.imageResource)
        mProgressBar!!.setProgress((mCurrentSlide.toFloat() / mDataSet!!.size * 100 - 0.1f).toDouble())
        setButtons(i)
    }

    private fun result(): String {
        val answersSum = fullBlindCount + red_greenBlind + normal + fake
        val percentage = (normal.toFloat() / answersSum.toFloat() * 100).toInt()

        val frst = getString(R.string.conclusion) + " " + percentage + "%\n"
        val scnd = if (percentage < 98) String.format(getString(R.string.conclusion2), percentage) else "";

        return frst + scnd

        //        return "В ході діагностики було отримо такі результати:" +
        //                "\n\tПри нормально зорі відповідей: " + normal +
        //                "\n\tПри сліпоті в зеленому або червоно спектрі: " + red_greenBlind +
        //                "\n\tПри повній кольоровій сліпоті: " + fullBlindCount +
        //                "\n\tМоживих симуляцій: " + fake;
    }
}
