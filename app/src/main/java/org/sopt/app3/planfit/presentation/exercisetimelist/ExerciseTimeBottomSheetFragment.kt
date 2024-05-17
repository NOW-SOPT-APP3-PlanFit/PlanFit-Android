package org.sopt.app3.planfit.presentation.exercisetimelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.sopt.app3.planfit.databinding.FragmentExerciseTimeBottomSheetBinding

import org.sopt.app3.planfit.presentation.exercisetimelist.adapter.ExerciseTimeListAdapter

class ExerciseTimeBottomSheetFragment : BottomSheetDialogFragment() {
    private var binding: FragmentExerciseTimeBottomSheetBinding? = null
    lateinit var onDismiss: () -> Unit
    lateinit var onSuccess: (Int) -> Unit
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

        val timeListAdapter = ExerciseTimeListAdapter{
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
            onSuccess: (Int) -> Unit
        ): ExerciseTimeBottomSheetFragment = ExerciseTimeBottomSheetFragment().apply {
            this.onDismiss = onDismiss
            this.onSuccess = onSuccess
        }
    }
}