package com.example.diarytest.ui.post

import android.net.Uri
import java.util.*

data class PostData(
    var createdAt: String =  "",
    var image: String = "",
    var title: String = "",
    var tag: String = "",
    var text: String = ""
)