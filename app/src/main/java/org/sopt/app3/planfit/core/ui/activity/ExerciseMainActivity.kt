package org.sopt.app3.planfit.core.ui.activity

import android.os.Bundle
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder
import coil.load
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.ui.base.BaseActivity
import org.sopt.app3.planfit.databinding.ActivityExerciseMainBinding

class ExerciseMainActivity : BaseActivity<ActivityExerciseMainBinding>({ inflater ->
    ActivityExerciseMainBinding.inflate(inflater)
}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifLoad()
        changeHeartState()
    }

    private fun gifLoad() {
        val imageLoader = ImageLoader.Builder(this)
            .components {
                add(ImageDecoderDecoder.Factory())
            }
            .build()

        binding.ivExerciseMainLatpulldown.load(R.raw.gif_exc_latpulldown, imageLoader = imageLoader)
    }

    private fun changeHeartState() {
        val ivHeart: ImageView = binding.ivExerciseMainHeart
        ivHeart.setOnClickListener{
            ivHeart.isSelected = !ivHeart.isSelected
        }
    }
}