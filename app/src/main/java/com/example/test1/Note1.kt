package com.example.test

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Note (
        var id: Int = 0,
        var title: String? = null,
        var description: String? = null,
        var date: String? = null
): Parcelable