package com.example.assignment_1.Book

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_1.R

class BookActivity : AppCompatActivity() {
    private lateinit var rv_book: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        val BookList = arrayListOf(
            Book(R.drawable.book1, "역행자", "자청", "웅진지식하우스"),
            Book(R.drawable.book2, "웰씽킹(WEALTHINKING)", "켈리 최", "다산북스"),
            Book(R.drawable.book3, "잘 살아라 그게 최고의 복수다", "권민창", "마인드셋(Mindset)"),
            Book(R.drawable.book4, "오은영의 화해", "오은영", "코리아닷컴"),
            Book(R.drawable.book5, "멘탈을 바꿔야 인생이 바뀐다", "박세니", "마인드셋(Mindset)"),
            Book(R.drawable.book6, "그릿(100쇄 기념 리커버 에디션)", "엔젤라 더크워스", "비즈니스북스"),
            Book(R.drawable.book7, "데일 카네기 인간관계론", "데일 카네기", "현대지성"),
            Book(R.drawable.book8, "원씽(The One Thing)(리커버 특별판)", "게리켈러, 제이 파파산", "비즈니스북스"),
            Book(R.drawable.book9, "타이탄의 도구들(블랙 에디션)", "팀 페리스", "토네이도"),
            Book(R.drawable.book10, "럭키(10만부 기념 황금열쇠 양장 특별판", "김도윤", "북로망스"),
            Book(R.drawable.book11, "마음챙김", "샤우나 샤피로", "안드로메디안"),
            Book(R.drawable.book12, "시크릿", "론다 번", "살림Biz"),
            Book(R.drawable.book13, "데일 카네기 자기관리론(1948년 초판 완역본", "데일 카네기", "현대지성"),
            Book(R.drawable.book14, "미움받을 용기", "기시미 이치로(岸見一郞)", "인플루엔셜")
        )
        rv_book = findViewById(R.id.rv_book)
        rv_book.layoutManager = GridLayoutManager(this, 2)
        rv_book.setHasFixedSize(true)
        rv_book.adapter = BookAdapter(BookList)
    }
}