package org.sopt.app3.planfit.core.ui.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.ui.base.BaseActivity
import org.sopt.app3.planfit.databinding.ActivityExerciseMainBinding

class ExerciseMainActivity : BaseActivity<ActivityExerciseMainBinding>({ inflater ->
    ActivityExerciseMainBinding.inflate(inflater) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Glide.with(this)
            .load(R.raw.gif_exc_latpulldown)
            .fitCenter()
            .into(binding.ivExerciseMainLatpulldown)
    }
}