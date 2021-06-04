package com.michael.pluginwarehouse

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.Transition

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnTestClickListener).setOnClickListener(object :
            View.OnClickListener {
            override fun onClick(v: View?) {
                Exception().printStackTrace()
                Log.i(TAG, "setCircleData: ")
            }

        })
        findViewById<Button>(R.id.btnTestBackLamba).setOnClickListener {
            Exception().printStackTrace()
            Log.d(TAG, "-------btnTestBackLamba------")
        }

        val itemExamBinding = JvmTest()
        var questionAnswer: QuestionAnswer = QuestionAnswer()
        var b : Float = 5f
        var view: View = View(this)
        var c: Animation? = null
        var questionTv: TextView = TextView(this)
        var e: Button = Button(this)
        var optionViewList  = listOf<Int>(1,1,5)
        var map = mutableMapOf(4 to 8, 9 to 7)
        var isMulti = false
        findViewById<Button>(R.id.btnTestBlock).setOnClickListener { //反选
            itemExamBinding.test111()
            if (!isMulti) {
                //单选逻辑处理 取消其他选择
                if (optionViewList.size > 0) {
                    for (j in optionViewList.indices) {
                        if (j == 1 || !(optionViewList[1] as Boolean)) {
                            continue
                        }
                        optionViewList[j]
                    }
                }
            }
            //选择逻辑
            if (!isMulti) {
                //单选 清空已选
                map.clear()
            }
                map.put(5,9)
                map.remove(4)

            //给问题的tv设置tag标记 多选必须多选
            questionTv.tag = !isMulti || map.size > 1
            for (key in 0 until map.size) {
                b
                view.post({b.compareTo(8)})
            }
            if (map.isNotEmpty()) {
                questionAnswer.answerOption = questionAnswer.answerOption!!.substring(
                    0,
                    questionAnswer.answerOption!!.length - 1
                )
            }
            e.isEnabled = map.size == 8
        }

        findViewById<Button>(R.id.btnTestBlock2222).setOnClickListener(this)

        JvmTest().test(findViewById<Button>(R.id.btnTestBackLamba),object : JvmTest.ClickMonitor {
            override fun onClick(view: View) {
                Log.d(TAG, "-------JvmTest------")
            }
        })
    }
    class QuestionAnswer {
        var questionId: Long = 0
        var answerOption: String? = null
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTestBlock2222 -> {
                Exception().printStackTrace()
                Log.d(TAG, "-------btnTestBlock2222------")
            }
        }
    }
}