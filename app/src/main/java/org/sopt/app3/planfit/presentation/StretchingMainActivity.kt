package org.sopt.app3.planfit.presentation

import android.os.Bundle
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder
import coil.load
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.ui.base.BaseActivity
import org.sopt.app3.planfit.databinding.ActivityStretchingMainBinding


class StretchingMainActivity : BaseActivity<ActivityStretchingMainBinding>({ inflater ->
    ActivityStretchingMainBinding.inflate(inflater)
}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifLoad()
    }

    private fun gifLoad() {
        val imageLoader = ImageLoader.Builder(this)
            .components {
                add(ImageDecoderDecoder.Factory())
            }
            .build()

        binding.ivExerciseStretching.load(R.raw.gif_exc_cycle, imageLoader = imageLoader)
    }


}