package com.frogobox.research.model

/**
 * Created by Faisal Amir on 04/12/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

sealed class SpinnerItemType {

    data class Loading(
        val isLoading: Boolean = true,
    ) : SpinnerItemType()

    data class Error(
        val message: String,
    ) : SpinnerItemType()

    data class Empty(
        val message: String,
    ) : SpinnerItemType()

    data class Content(
        val data: String,
    ) : SpinnerItemType()

}