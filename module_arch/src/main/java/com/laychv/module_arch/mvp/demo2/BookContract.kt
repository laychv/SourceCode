package com.laychv.module_arch.mvp.demo2

import com.laychv.module_arch.mvp.demo2.base.BaseModel
import com.laychv.module_arch.mvp.demo2.base.BasePresenter
import com.laychv.module_arch.mvp.demo2.base.BaseView

interface BookContract {

    interface Model : BaseModel {

    }

    interface View : BaseView {

    }

    open class Presenter : BasePresenter<View, Model>() {

    }
}