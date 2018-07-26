package cn.ju.sc.glide

import android.os.Bundle
import cn.ju.sc.R
import cn.ju.sc.base.BaseActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_glide.*

class GlideActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        loadImageSimple(this, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532592878669&di=c6e3196959a714e83adf836e2b51bb9d&imgtype=0&src=http%3A%2F%2Fsrc.onlinedown.net%2Fnew_img%2Fapk_image%2F2015%2F0713%2F408649_1436753506_065.jpg.t.jpg", ivGlide)
    }

}