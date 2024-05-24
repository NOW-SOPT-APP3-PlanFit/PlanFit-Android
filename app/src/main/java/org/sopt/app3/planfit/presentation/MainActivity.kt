package org.sopt.app3.planfit.presentation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.presentation.exercisetimelist.ExerciseConditionBottomSheetFragment
import org.sopt.app3.planfit.presentation.exercisetimelist.ExerciseTimeBottomSheetFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTime = findViewById<Button>(R.id.btn_minute)
        btnTime.setOnClickListener {
            val fragment = ExerciseTimeBottomSheetFragment.newInstance(
                onDismiss = {},
                onSuccess = {},
                selectedTime = 0
            )
            fragment.show(supportFragmentManager, fragment.tag)
        }

        val btnCondition = findViewById<Button>(R.id.btn_condition)
        btnCondition.setOnClickListener {
            val fragment = ExerciseConditionBottomSheetFragment.newInstance(
                onDismiss = {},
                onSuccess = {},
                selectedCondition = "1"
            )
            fragment.show(supportFragmentManager, fragment.tag)
        }
    }
}
