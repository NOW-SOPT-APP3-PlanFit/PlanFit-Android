package org.sopt.app3.planfit.presentation.exercisetimelist

import android.app.Activity
import android.content.Context
import android.os.Build.*
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.window.layout.WindowMetricsCalculator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.sopt.app3.planfit.databinding.FragmentExerciseConditionBottomSheetBinding
import org.sopt.app3.planfit.presentation.exerciseconditionlist.ExerciseConditionListViewModel
import org.sopt.app3.planfit.presentation.exercisetimelist.adapter.ExerciseConditionListAdapter


class ExerciseConditionBottomSheetFragment : BottomSheetDialogFragment() {
    private var binding: FragmentExerciseConditionBottomSheetBinding? = null
    lateinit var onDismiss: () -> Unit
    lateinit var onSuccess: (String) -> Unit
    var selectedCondition: String? = null
    private val viewModel: ExerciseConditionListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentExerciseConditionBottomSheetBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            adjustRecyclerViewHeightToScreen(it, binding!!.rvExerciseCondititon, 0.8f)
        }

        val conditionListAdapter = ExerciseConditionListAdapter(selectedCondition!!) {
            onSuccess(it)
            dismiss()
        }
        binding?.rvExerciseCondititon?.adapter = conditionListAdapter
        binding?.rvExerciseCondititon?.layoutManager = LinearLayoutManager(requireContext())
        viewModel.exerciseConditions.observe(viewLifecycleOwner) { conditions ->
            conditionListAdapter.submitList(conditions)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        onDismiss
    }

    private fun adjustRecyclerViewHeightToScreen(context: Context, recyclerView: RecyclerView, screenHeightRatio: Float) {
        val screenHeight = if (VERSION.SDK_INT >= VERSION_CODES.R) {
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

    companion object {
        fun newInstance(
            onDismiss: () -> Unit,
            onSuccess: (String) -> Unit,
            selectedCondition: String,
        ): ExerciseConditionBottomSheetFragment = ExerciseConditionBottomSheetFragment().apply {
            this.onDismiss = onDismiss
            this.onSuccess = onSuccess
            this.selectedCondition = selectedCondition
        }
    }
}