package com.roquebuarque.cpynpasta.base


interface BaseContract {

    interface View

    interface Presenter<T : View> {

        fun attachView(view: T)

        fun detachView()

    }
}
