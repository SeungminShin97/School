#include <iostream>
#include <string>
#include <ctime>
#include <cstdlib>
#include <fstream>

using namespace std;


class Word {
protected:

public:
    Word() {}


};

class KorWord : public Word {
private:
    string pos;
    string mean;
public:
    KorWord() {}

    void SetPos(string a) {
        pos = a;
    }

    void SetMean(string a) {
        mean = a;
    }

    string GetPos() { return pos; }
    string GetMean() { return mean; }
    
};

class EngWord : public Word{
private:
    string engword;
    string pos;
    int korwordcnt = 0;
    KorWord* korword;
    EngWord* link = NULL;
public:
    EngWord() {}    

    void SetKorwordcnt(int a) {
        korwordcnt = a;
    }

    void Setkorwordlink(KorWord *k) {
        korword = k;
    }

    void SetEngWord(string a) {
        engword = a;
    }

    void SetPos(string a) {
        pos = a;
    }

    void SetKorWord(int a) {
        korword = new KorWord[a];
        for (int i = 0; i < a; i++) {
            string pos;
            cout << "[단어 " << i + 1 << "] 품사 : ";
            cin >> pos;
            korword[i].SetPos(pos);

            string mean;
            cout << "[단어 " << i + 1 << "] 뜻 : ";
            cin.ignore();
            getline(cin, mean);
            korword[i].SetMean(mean);
        }
        korwordcnt = a;
    }  

    void SetLink(EngWord* asdf ) {
        link = asdf;
    }
    
    
    KorWord *GoKorword() { return korword; }
    EngWord* Getlink() { return link; }
    int getKorwordcnt() { return korwordcnt; }
    string GetEngword() { return engword; }
    string GetEngpos() { return pos; }
    string getkorwordPos(int a) { return korword[a].GetPos(); }
    string getkorwordMean(int a) { return korword[a].GetMean(); }
};


class Vocabulary {
private:
    EngWord *englist, *engword;
    int wordcnt = 0;
    int size = 0;
public:
    Vocabulary() {}
    Vocabulary(int a) {
        englist = new EngWord  [a];
        size = a;
    }

    void SetWordcnt(int a) {
        wordcnt = a;
    }


    void SetEngWord()  {
        if (wordcnt < size) {
            string word;
            cout << "등록할 영어단어 : ";
            cin.ignore();
            getline(cin, word);
            engword = new EngWord;
            engword->SetEngWord(word);

            string apos;
            cout << "품사 : ";
            cin >> apos;
            engword->SetPos(apos);

            int cnt;
            cout << word << "에 대한 한글단어의 등록 개수 : ";
            cin >> cnt;
            engword->SetKorWord(cnt);
            englist[wordcnt].SetLink(engword);

            wordcnt++;
        }
        else
            cout << "배열이 꽉 찼습니다.";

        
      
    }
    
    void FindEngWord() {
        string word;
        cout << "검색할 영어단어 : ";
        cin.ignore();
        getline(cin, word);
        for (int i = 0; i < size; i++) {
            if (englist[i].Getlink() != NULL) {
                if (word == englist[i].Getlink()->GetEngword()) {
                    cout << word << " [" << englist[i].Getlink()->GetEngpos() << "]" << endl;
                    for (int j = 0; j < englist[i].Getlink()->getKorwordcnt(); j++) {
                        cout << "(" << englist[i].Getlink()->getkorwordPos(j) << ", " << englist[i].Getlink()->getkorwordMean(j) << ")" << endl;
                    }
                }
            }
            
        }
    }

    void DeleteEngWord() {
        if (wordcnt == 0) {
            cout << "등록된 단어가 없습니다.";
        }
        else {
            string word;
            cout << "삭제할 영어단어 : ";
            cin.ignore();
            getline(cin, word);
            for (int i = 0; i < wordcnt; i++) {
                if (word == englist[i].Getlink()->GetEngword()) {
                    delete englist[i].Getlink();
                    englist[i].SetLink(NULL);
                    wordcnt--;
                    break;
                }
            }
        }

    }

    void ShowWord() {
        cout << "단어장을 출력합니다." << endl;
        int i = 0;
        while (i < size) {
            if (englist[i].Getlink() != NULL) {
                cout << "[" << i + 1 << "] " << englist[i].Getlink()->GetEngword() << " [" << englist[i].Getlink()->GetEngpos() << "]" << endl;
                for (int j = 0; j < englist[i].Getlink()->getKorwordcnt(); j++) {
                    cout << "   [단어 " << j + 1 << "] 품사 : " << englist[i].Getlink()->getkorwordPos(j) << endl;
                    cout << "   [단어 " << j + 1 << "] 품사 : " << englist[i].Getlink()->getkorwordMean(j) << endl;
                }
            }
            i++;
        }
    }

    void TestWord() {
        cout << "암기 테스트를 시작합니다.";
        int o = 0, x = 0, cnt = 0, buff = 0;
        while (true) {
            srand(time(NULL));
            int rnum = (rand() % wordcnt) + 1;
            int i = 0;
            int oooo = 0;
            while(i < (rnum - 1)) {
                int j = i;
                while (englist[j].Getlink() == NULL) {
                    i++;
                }
                i++;
            }
            cout << "영어단어 : " << englist[i].Getlink()->GetEngword() << endl;
            cout << "뜻 : ";
            string word;
            if (buff == 0) {
                cin.ignore();
            }
            getline(cin, word);
            if ((word == "q") || (word == "Q")) {
                cout << "암기 테스트를 종료합니다." << endl;
                cout << "테스트횟수 : " << cnt << "  정답 : " << o << "  오답 : " << x;
                break;
            }
            for (int k = 0; k < englist[i].Getlink()->getKorwordcnt(); k++) {
                if (word == englist[i].Getlink()->getkorwordMean(k)) {
                    oooo++;
                    break;
                }
            }
            if (oooo > 0) {
                cout << "=>정답입니다." << endl;
                o++;
                cnt++;
            }
            else {
                cout << "=>오답입니다." << endl;
                x++;
                cnt++;
            }
            buff++;
        }
    }

    void Delword() {
        delete[] englist;
    }

    EngWord *GoEnglist() { return englist; }
    int GetWordcnt() { return wordcnt; }
};

void saveWord(Vocabulary voca, int cnt) {
    fstream myfile;
    myfile.open("Word.txt", ios::out);
    myfile << cnt << endl;
    for (int i = 0; i < cnt; i++) {
        myfile << voca.GoEnglist()[i].Getlink()->GetEngword() << " ; " << voca.GoEnglist()[i].Getlink()->GetEngpos() << " " << voca.GoEnglist()[i].Getlink()->getKorwordcnt() << endl;
        for (int j = 0; j < voca.GoEnglist()[i].Getlink()->getKorwordcnt(); j++) {
            myfile << voca.GoEnglist()[i].Getlink()->getkorwordPos(j) << " " << voca.GoEnglist()[i].Getlink()->getkorwordMean(j) << " ; " << endl;
        }
    }
    myfile.close();
}


int loadWord(Vocabulary voca, int size) {
    fstream myfile;
    myfile.open("Word.txt", ios::in);
    int cnt;
    myfile >> cnt;
    if ((cnt < 0) || (cnt > size)) {
        cnt = 0;
    }
    
    voca.SetWordcnt(cnt);
    for (int i = 0; i < cnt; i++) {
        string engword, engpos, korpos, mean, append;
        int a, b = 0;
        while (b == 0) {
            myfile >> append;
            engword.append(append);
            engword.append(" ");
            if (append == ";") {
                b++;
                engword.pop_back();
                engword.pop_back();
            }

        }
        myfile >> engpos >> a;  
        EngWord* asdf;
        asdf = new EngWord;
        voca.GoEnglist()[i].SetLink(asdf);
        asdf->SetEngWord(engword);
        asdf->SetPos(engpos);
        KorWord* kword;
        kword = new KorWord[a];
        asdf->Setkorwordlink(kword);
        asdf->SetKorwordcnt(a);
        string loadmean;
        int c = 0;
        for (int j = 0; j < a; j++) {
            myfile >> korpos;
            while (c == 0) {
                myfile >> loadmean;
                mean.append(loadmean);
                mean.append(" ");
                if (loadmean == ";") {
                    c++;
                    mean.pop_back();
                    mean.pop_back();
                }
            }
            asdf->GoKorword()[j].SetMean(mean);
            asdf->GoKorword()[j].SetPos(korpos);
            mean.clear();
            korpos.clear();
            loadmean.clear();
            c = 0;
        }
        
        
    }
    myfile.close();
    return cnt;
}



int main()
{

    int vocasize;
    cout << "+------------------------------+\n";
    cout << "      >>단어장 프로그램<<       \n";
    cout << "   created by 20161324 신승민   \n";
    cout << "+------------------------------+\n";
    cout << "단어장 프로그램을 시작합니다....\n";
    cout << "단어장의 크기(등록할 영어단어의 개수)를 입력하세요.";
    cin >> vocasize;
    Vocabulary voca(vocasize);
    cout << vocasize << "개의 단어를 등록할 단어장이 생성되었습니다." << endl;
    voca.SetWordcnt(loadWord(voca, vocasize));
    

        int asdf = 0;
    while (asdf == 0) {
        int menu;
        cout << "\n\n";
        cout << "#########################" << endl;
        cout << "   1) 단어 등록" << endl;
        cout << "   2) 단어 검색" << endl;
        cout << "   3) 단어 삭제" << endl;
        cout << "   4) 단어장출력" << endl;
        cout << "   5) 암기테스트" << endl;
        cout << "   0) 종료" << endl;
        cout << "# 기능 선택 : ";
        cin >> menu;

        switch (menu) {
        case 1:
            voca.SetEngWord();
            break;
        case 2:
            voca.FindEngWord();
            break;
        case 3:
            voca.DeleteEngWord();
            break;
        case 4:
            voca.ShowWord();
            break;
        case 5:
            voca.TestWord();
            break;
        case 0:
            saveWord(voca, voca.GetWordcnt());
            voca.Delword();
            asdf++;
        default :
            break;
        }
       

        
    }



    return 0;
}

