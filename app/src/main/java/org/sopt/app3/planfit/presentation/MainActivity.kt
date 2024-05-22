package org.sopt.app3.planfit.presentation

import android.app.Dialog
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.presentation.exerciselist.ExerciseViewModel
import org.sopt.app3.planfit.presentation.exercisetimelist.ExerciseConditionBottomSheetFragment
import org.sopt.app3.planfit.presentation.exercisetimelist.ExerciseTimeBottomSheetFragment
import org.sopt.app3.planfit.presentation.exercisetimelist.ExerciseTimeListViewModel
import org.sopt.app3.planfit.presentation.exercisetimelist.adapter.ExerciseTimeListAdapter

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
