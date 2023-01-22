package com.example.assignment_1.Show

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assignment_1.R

class ShowSlideAdapter(adapter: ShowActivity) : FragmentStateAdapter(adapter) {
    private val image = listOf<Int>(
        R.drawable.show1,
        R.drawable.show2,
        R.drawable.show3,
        R.drawable.show4,
        R.drawable.show5,
        R.drawable.show6,
        R.drawable.show7,
        R.drawable.show8,
        R.drawable.show9,
        R.drawable.show10
    )
    private val title = listOf<String>("브로드웨이 42번가", "캣츠 오리지널", "호두까기 인형", "영웅", "마틸다", "히사이시조 영화음악 콘서트",
        "여신님이 보고 계서", "임윤찬 피아노 리사이틀", "푸에르자 부르타 웨이라", "용의자X의 헌신")
    override fun getItemCount(): Int = image.size

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ShowFragment.newInstance(image[0], title[0])
            1 -> ShowFragment.newInstance(image[1], title[1])
            2 -> ShowFragment.newInstance(image[2], title[2])
            3 -> ShowFragment.newInstance(image[3], title[3])
            4 -> ShowFragment.newInstance(image[4], title[4])
            5 -> ShowFragment.newInstance(image[5], title[5])
            6 -> ShowFragment.newInstance(image[6], title[6])
            7 -> ShowFragment.newInstance(image[7], title[7])
            8 -> ShowFragment.newInstance(image[8], title[8])
            9 -> ShowFragment.newInstance(image[9], title[9])
            else -> ShowFragment.newInstance(image[0], title[0])
        }
    }

}