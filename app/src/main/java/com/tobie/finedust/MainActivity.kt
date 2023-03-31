package com.tobie.finedust

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.tobie.finedust.viewmodel.ActionType
import com.tobie.finedust.viewmodel.MyNumberViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG: String = "로그"
    }
    
    private lateinit var myViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewModel = ViewModelProvider(this@MainActivity)[MyNumberViewModel::class.java]

        myViewModel.currentValue.observe(this) {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경: $it")
        number_textview.text = it.toString()
        }

        // 리스너 등록
        plus_btn.setOnClickListener(this)
        minus_btn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val userInput = userinput_edittext.text.toString().toInt()

        when(p0){
            plus_btn -> myViewModel.updateValue(ActionType.PLUS, userInput)
            minus_btn ->myViewModel.updateValue(ActionType.MINUS, userInput)
        }
    }
}

