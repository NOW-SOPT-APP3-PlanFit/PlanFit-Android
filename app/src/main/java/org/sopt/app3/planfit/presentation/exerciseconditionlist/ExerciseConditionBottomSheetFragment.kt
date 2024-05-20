package org.sopt.app3.planfit.presentation.exercisetimelist

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.databinding.FragmentExerciseConditionBottomSheetBinding
import org.sopt.app3.planfit.domain.model.ExerciseCondition
import org.sopt.app3.planfit.presentation.exercisetimelist.adapter.ExerciseConditionListAdapter


class ExerciseConditionBottomSheetFragment : BottomSheetDialogFragment() {
    private var binding: FragmentExerciseConditionBottomSheetBinding? = null
    lateinit var onDismiss: () -> Unit
    lateinit var onSuccess: (String) -> Unit
    var selectedCondition: String? = null
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

        val mockData: List<ExerciseCondition> = listOf(
            ExerciseCondition("Condition 1", "Condition 1"),
            ExerciseCondition("Condition 2", "Condition 1"),
            ExerciseCondition("Condition 3", "Condition 1"),
            ExerciseCondition("Condition 4", "Condition 1"),
            ExerciseCondition("Condition 5", "Condition 1"),
        )
        val conditionListAdapter = ExerciseConditionListAdapter(selectedCondition!!) {
            onSuccess(it)
            dismiss()
        }
        binding?.rvExerciseCondititon?.adapter = conditionListAdapter
        binding?.rvExerciseCondititon?.layoutManager = LinearLayoutManager(requireContext())


        conditionListAdapter.submitList(mockData)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        onDismiss
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