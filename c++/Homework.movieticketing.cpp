/// 1. 영화등록 : 제목(띄어쓰기 포함),장르,개봉일(ex 2020.10.26),평점
/// 2. 영화목록 : 상영중인영화
/// 3. 영화검색(제목)
/// 4. 영화검색(평점) : 입력점수 이상 평점들의 영화 
/// 5. 영화삭제
/// 0. 종료
/// 
/// 영화목록은 링크드리스트
/// 
#include <iostream>
#include <string>
using namespace std;


class Date
{
private:
    int year = 0, month = 0, day = 0;
public:
    Date() {}
    
    void setDate(char *rday) {
        int j = 1000;
        for (int i = 0; i < 4; i++) {
            year += (rday[i] - 48) * j;
            j /= 10;
        }
        month = ((rday[5] - 48) * 10) + (rday[6] - 48);
        day = ((rday[8] - 48) * 10) + (rday[9] - 48);
    }
    void getDate() {
        cout << year << "." << month << "." << day;
    }

};

class Movie
{
private:
    string title, genre;
    float rating;
    Date openday;
public:
    Movie() {}
    /// <summary>
    /////////////////////////////// set/////////////////////////
    /// </summary>
    void setTitle() {       // 제목 입력
        cout << "제목 : ";
        getline(cin,title);
    }
    void setGenre() {       // 장르 입력
        cout << "장르 : ";
        getline(cin, genre);
    }
    void setOpenday() {     // 개봉일 입력 & 계산
        char rday[11];
        cout << "개봉일 : ";
        cin >> rday;
        openday.setDate(rday);
    }
    void setRating() {      // 평점 입력
        cout << "평점 : ";
        cin >> rating;
    }
    //////////////////////////////// get //////////////////////////

    string getTitle() { return title; }
    string getGenre() { return genre; }
    void getDate() { openday.getDate(); }
    float getRating() { return rating; }

};

class MovieNode
{
private:
    Movie data;
    MovieNode* next;
public: 
    MovieNode() {}

    void setNext(MovieNode *nextnode) {
        next = nextnode;
    }
    
    void setData() {                         // 데이터 입력
        data.setTitle();    // 제목 등록
        data.setGenre();    // 장르 등록
        data.setOpenday();  // 개봉일 등록
        data.setRating();   // 평점 등록
        cout << ">> [" << data.getTitle() << "] 영화가 등록되었습니다." << endl;
    }

    MovieNode* getNext() {                    //  가리키는 노드 반환
        return next;
    }

    void getDatalist() {                      // 영화정보 표시
        cout << data.getTitle() << " : " << data.getGenre() << " : ";
        data.getDate();
        //cout <<" : " << data.getRating() << endl;
        //printf(" : %.1f\n", data.getRating());
        cout.setf(ios::fixed);         // 평점이 n.0 일때 n으로만 표시되는것을 막음
        cout.precision(1);
        cout << " : " << data.getRating() << endl;
        cout.unsetf(ios::fixed);
    }
    
    string getTitle() {
        return data.getTitle();
    }
    
    float getRating() {
        return data.getRating();
    }

};

class MovieManager             // 각종 기능들
{
private:
    int size = 0;
    MovieNode* head, * tail, *node;

public:
    MovieManager() {}
    void addMovie() {       // 영화등록
        if (size == 0) {
            node = new MovieNode;
            head = tail = node;             
            node->setData();                // 노드의 데이터 생성
            size++;                         // 노드의 수 1증가
        }
        else {
            node = new MovieNode;
            tail->setNext(node);              // tail이 가리키고있는 노드가 새로 생성한 노드를 가리킴
            tail = node;                      // tail이 새로 생성된 노드를 가리킴
            node->setData();

            size++;
        }
    }
    ~MovieManager() {
        delete node;
    }

    void listMovie() { // 영화목록
        cout << ">>상영중인 영화" << endl;
        MovieNode* ptr = new MovieNode;
        ptr = head;                  //ptr이 노드를 가리킴
        for (int i = 0; i < size; i++) {
            cout << "[" << i+1 << "] ";
            ptr->getDatalist();             // 영화정보 출력
            ptr = ptr->getNext();        // ptr이 다음 노드를 가리킴
        }
        delete ptr;
    }

    void findtitleMovie() { // 영화검색(제목)
        string findtitle;
        cout << ">> 검색할 영화 : ";
        getline(cin, findtitle);
        MovieNode* ptr = new MovieNode;
        ptr = head;
        int cnt = 0;  // 검색결과가 없을경우를 대비한 카운팅
        for (int i = 0; i < size; i++) {
            if (findtitle == ptr->getTitle()) {   //제목검색
                ptr->getDatalist();   //리스트반환
                cnt++;
            }
            ptr = ptr->getNext();
        }
        if (cnt == 0) {
            cout << "검색 결과가 없습니다" << endl;
        }
    }
    void findratingMovie() { // 영화검색 (평점)
        float findrating;
        cout << ">> 평점 : ";
        cin >> findrating;
        MovieNode* ptr = new MovieNode;
        ptr = head;
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (findrating == ptr->getRating()) {  //평점검색
                ptr->getDatalist();    // 리스트반환
                cnt++;
            }
            ptr = ptr->getNext();
        }
        if (cnt == 0) {
            cout << "검색 결과가 없습니다";
        }
    }

    void deleteMovie() {
        string delmovie;
        int cnt = 1;               //일치하는 제목의 위치
        cout << ">> 삭제할 영화 : ";
        getline(cin, delmovie);
        MovieNode* delnode = new MovieNode, *node;
        delnode = head;
        for (int i = 0; i < size; i++) //제목 위치검색
        {
            if (delnode->getTitle() == delmovie) { // 제목검색
                break;
            }
            delnode = delnode->getNext();
            cnt++;                                   
        }
        delnode = head;
        if (cnt > size) {             // 검색 결과가 없을때
            cout << "검색결과가 없습니다.";
            return;
        }
        else if (cnt == 1) {          // 첫번째
            head = delnode->getNext();
            delete delnode;
            size--;
            cout << delmovie << " 영화를 삭제하였습니다." << endl;
            return;
        }
        else if (cnt == size) {  //마지막
            for (int i = 0; i < cnt - 2; i++) {               //delnode가 삭제할 노드 하나 앞으로 이동
                delnode = delnode->getNext();
            }
            delete delnode->getNext();
            tail = delnode;
            size--;
            cout << delmovie << " 영화를 삭제하였습니다." << endl;
            return;
        }
        else {        //중간에 위치할 때
            for (int i = 0; i < cnt - 2; i++) {               //delnode가 삭제할 노드 하나 앞으로 이동
                delnode = delnode->getNext();
            }
            node = delnode->getNext();
            delnode->setNext(delnode->getNext()->getNext());  //delnode가 다다음 노드를 가리킴
            delnode = node;                   //delnode를 다음 노드로 이동
            delete delnode;                                   //노드삭제
            size--;
            cout << delmovie << " 영화를 삭제하였습니다." << endl;
            return;
        }


    }

};





int main()
{
    MovieManager a;
    int menu;
    int end = 0;
    while (end == 0) {
        cout << ">>>>> 영화관리시스템 <<<<<" << endl;
        cout << "       1.영화등록" << endl;
        cout << "       2.영화목록" << endl;
        cout << "       3.영화검색(제목)" << endl;
        cout << "       4.영화검색(평점)" << endl;
        cout << "       5.영화삭제" << endl;
        cout << "       0.종료" << endl;
        cout << "메뉴 >> ";
        cin >> menu;
        cin.ignore();

        switch (menu) {
        case 1:
            a.addMovie();
            break;
        case 2:
            a.listMovie();
            break;
        case 3:
            a.findtitleMovie();
            break;
        case 4:
            a.findratingMovie();
            break;
        case 5:
            a.deleteMovie();
            break;
        case 0:
            end++;
            break;
        default : 
            cout << "잘못된 값입니다.";
            break;       
        }
    }

    return 0;



}

