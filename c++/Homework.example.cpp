#include <iostream>
#include <string>

using namespace std;

class Multimedia {
protected:
    string title;
    string resolution;
    string format;
public:
    Multimedia() {}
    Multimedia(string t, string r, string f) {
        title = t;
        resolution = r;
        format = f;
    }

    virtual void print() {};
};

class Image : public Multimedia {
private:
    string color;
public:
    Image() {}
    Image(string t, string r, string f, string c) : Multimedia(t, r, f) {
        color = c;
    }
    void print() override {
        cout << "이미지 =>" << title << ", " << resolution << ", " << format << ", " << color << endl;
    }
};

class Movie : public Multimedia {
private:
    string runtime;
public:
    Movie(string t, string r, string f, string c) : Multimedia(t, r, f) {
        runtime = c;
    }
    void print() override {
        cout << "동영상 =>" << title << ", " << resolution << ", " << format << ", " << runtime << endl;
    }

};

int main()
{
    Multimedia** marray;       //Image 또는 Movie 객체를 가리키는 포인터 배열
    int idx = 0, size = 0;     //배열의 빈자리를 가리키는 인덱스, 배열의 크기

    cout << "******* 멀티미디어 자료관리 프로그램 *******" << endl;
    cout << "크기 : "; cin >> size;

    marray = new Multimedia*[size];
    cout << "멀티미디어 자료 등록공간이 생성됨(크기 : " << size << ")" << endl;

    int mcnt = 0;

    
    while (true) {
        int a;
        cout << "기능선택?(1.등록, 2.출력, 3.종료)";
        cin >> a;
        
        if (a == 1) {   ////////////////////////////////////////등록
            int b;
            cout << "등록할 자료 유형?(1.이미지, 2.동영상)";
            cin >> b;
            if (b == 1) {/////////////////////////////////////////////이미지
                string title, resolution, format, color;
                cout << "제목 : "; cin >> title;
                cout << "해상도 : "; cin >> resolution;
                cout << "파일포맷 : "; cin >> format;
                cout << "색상 : "; cin >> color;
                Image* image;
                image = new Image(title, resolution, format, color);
                marray[mcnt] = image;
                cout << "\n등록되었습니다.!!\n\n";
                mcnt++;

            }
            else if (b == 2) {/////////////////////////////////////////동영상
                string title, resolution, format, runtime;
                cout << "제목 : "; cin >> title;
                cout << "해상도 : "; cin >> resolution;
                cout << "파일포맷 : "; cin >> format;
                cout << "러닝타임 : "; cin >> runtime;
                Movie* movie;
                movie = new Movie(title, resolution, format, runtime);
                marray[mcnt] = movie;
                cout << "\n등록되었습니다.!!\n\n";
                mcnt++;
            }

        }
        else if (a == 2) {////////////////////////////////////////출력
            for (int i = 0; i < mcnt; i++) {
                marray[i]->print();
            }
            cout << endl;
        }
        else if (a == 3) {////////////////////////////////////////종료
            for (int i = 0; i < mcnt + 1; i++) {
                delete marray[i];
            }
            delete[] marray;
            break;
        }
        else
            cout << "Wrong Value";


    }
    



    

    return 0;
}

