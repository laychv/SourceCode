package com.assess15.module_arch.mvp.demo1

interface BookContract {

    interface View : BaseView<Presenter> {
        fun showResult()
    }

    interface Presenter : BasePresenter {

    }
}