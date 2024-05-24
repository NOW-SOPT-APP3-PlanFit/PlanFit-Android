package org.sopt.app3.planfit.presentation.exercisetimelist

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.window.layout.WindowMetricsCalculator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.sopt.app3.planfit.databinding.FragmentExerciseTimeBottomSheetBinding
import org.sopt.app3.planfit.domain.model.ExerciseTime

import org.sopt.app3.planfit.presentation.exercisetimelist.adapter.ExerciseTimeListAdapter

class ExerciseTimeBottomSheetFragment : BottomSheetDialogFragment() {
    private var binding: FragmentExerciseTimeBottomSheetBinding? = null
    lateinit var onDismiss: () -> Unit
    lateinit var onSuccess: (Int) -> Unit
    var selectedTime: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseTimeBottomSheetBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            adjustRecyclerViewHeightToScreen(it, binding!!.rvExerciseTime, 0.8f)
        }
        val mockData: List<ExerciseTime> = listOf(
            ExerciseTime("짧게", "약 29분"),
            ExerciseTime("조금 짧게", "약 44분"),
            ExerciseTime("보통", "약 58분"),
            ExerciseTime("조금 길게", "약 73분"),
            ExerciseTime("길게", "약 87분"),
            ExerciseTime("아주 길게", "약 116분"),
        )


        val timeListAdapter = ExerciseTimeListAdapter(selectedTime!!){
            onSuccess(it)
            dismiss()
        }
        binding?.rvExerciseTime?.adapter = timeListAdapter
        binding?.rvExerciseTime?.layoutManager = LinearLayoutManager(requireContext())

        timeListAdapter.submitList(mockData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        onDismiss
    }

    private fun adjustRecyclerViewHeightToScreen(context: Context, recyclerView: RecyclerView, screenHeightRatio: Float) {
        val screenHeight = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context as Activity)
            windowMetrics.bounds.height()
        } else {
            val displayMetrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }

        val maxRecyclerViewHeight = (screenHeight * screenHeightRatio).toInt()

        recyclerView.layoutParams = recyclerView.layoutParams.apply {
            height = maxRecyclerViewHeight
        }
    }

    companion object{
        fun newInstance(
            onDismiss: () -> Unit,
            onSuccess: (Int) -> Unit,
            selectedTime: String
        ): ExerciseTimeBottomSheetFragment = ExerciseTimeBottomSheetFragment().apply {
            this.onDismiss = onDismiss
            this.onSuccess = onSuccess
            this.selectedTime = selectedTime
        }
    }
}