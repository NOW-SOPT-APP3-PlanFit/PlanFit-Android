package org.sopt.app3.planfit.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.common.ViewModelFactory
import org.sopt.app3.planfit.databinding.ActivityMainBinding
import org.sopt.app3.planfit.domain.model.MainPut
import org.sopt.app3.planfit.presentation.exerciselist.ExerciseListActivity
import org.sopt.app3.planfit.presentation.exercisetimelist.ExerciseConditionBottomSheetFragment
import org.sopt.app3.planfit.presentation.exercisetimelist.ExerciseTimeBottomSheetFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> { ViewModelFactory() }
    private val conditionMap: Map<String, String> = mapOf(
        Pair("125%", "최상"),
        Pair("100%", "상"),
        Pair("75%", "무거움"),
        Pair("50%", "피곤함"),
        Pair("25%", "안좋음")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            val intent = Intent(this, ExerciseListActivity::class.java)
            startActivity(intent)
        }

        binding.btnMinute.setOnClickListener {
            val fragment = ExerciseTimeBottomSheetFragment.newInstance(
                onDismiss = {},
                onSuccess = {
                    viewModel.putMain(
                        MainPut(
                            it,
                            binding.btnCondition.text.toString()
                        )
                    )
                },
                selectedTime = binding.btnMinute.text.toString()
            )
            fragment.show(supportFragmentManager, fragment.tag)
        }

        binding.btnCondition.setOnClickListener {
            val fragment = ExerciseConditionBottomSheetFragment.newInstance(
                onDismiss = {},
                onSuccess = {
                    viewModel.putMain(
                        MainPut(
                            binding.btnMinute.text.toString().filter { it.isDigit() }.toInt(),
                            conditionMap.get(it) ?: "무거움"
                        )
                    )
                },
                selectedCondition = binding.btnCondition.text.toString()
            )
            fragment.show(supportFragmentManager, fragment.tag)
        }

        viewModel.main.observe(this) {
            binding.btnMinute.text = it.minute.toString() + "분"
            binding.btnCondition.text = it.condition
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMain()
    }
}
