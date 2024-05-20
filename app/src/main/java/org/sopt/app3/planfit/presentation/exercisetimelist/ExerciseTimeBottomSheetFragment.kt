package org.sopt.app3.planfit.presentation.exercisetimelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.sopt.app3.planfit.databinding.FragmentExerciseTimeBottomSheetBinding
import org.sopt.app3.planfit.domain.model.ExerciseTime

import org.sopt.app3.planfit.presentation.exercisetimelist.adapter.ExerciseTimeListAdapter

class ExerciseTimeBottomSheetFragment : BottomSheetDialogFragment() {
    private var binding: FragmentExerciseTimeBottomSheetBinding? = null
    lateinit var onDismiss: () -> Unit
    lateinit var onSuccess: (Int) -> Unit
    var selectedTime: Int? = null

    private val mock: List<ExerciseTime> = listOf(
        ExerciseTime("짧게", "약 29분"),
        ExerciseTime("짧게2", "약 29분"),
        ExerciseTime("짧게3", "약 29분"),
        ExerciseTime("짧게4", "약 29분"),
        ExerciseTime("짧게5", "약 29분"),
        ExerciseTime("짧게6", "약 29분"),
    )

    private val exerciseTimeViewModel by lazy { ExerciseTimeListViewModel() }
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

        val timeListAdapter = ExerciseTimeListAdapter(selectedTime!!){
            onSuccess(it)
            dismiss()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

        onDismiss
    }

    companion object{
        fun newInstance(
            onDismiss: () -> Unit,
            onSuccess: (Int) -> Unit,
            selectedTime: Int
        ): ExerciseTimeBottomSheetFragment = ExerciseTimeBottomSheetFragment().apply {
            this.onDismiss = onDismiss
            this.onSuccess = onSuccess
            this.selectedTime = selectedTime
        }
    }
}