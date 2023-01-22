/// 기말과제 : 사전
/// 사전기능
/// 1. 단어추가 : 저장은 소문자로 저장(대문자로 입력해도), 알파벳순 정렬
/// /// 단어, 뜻, 발음?
/// 2. 단어검색 : 뜻 or 단어
/// 3. 단어리스트 : 전체, 특정 알파벳
/// 4. 단어수정 : 단어또는 내용수정
/// 5. 단어시험 : 무작위 단어 표출 (뜻 or 단어)
/// 
/// class : menu, wordnode, add(노드생성), list, test, data(데이터설정), 
/// 
/// system("cls"); 

#include <iostream>
#include <string>
#include <ctime>

using namespace std;

class Data {
private:
    string word;
public:
    Data() {}

    void SetWord(string a) {
        word = a;
    }
    

    string GetWord() { return word; }
};

class Wordnode {
private:
    Data* data;
    Wordnode* frontnode = NULL, *backnode = NULL;
public:
    Wordnode() {}

    Data GoData() { // data 설정으로 이동
        return *data;
    }
    void Setfront(Wordnode* front) {
        frontnode = front;
    }
    void SetBack(Wordnode *next) {
        backnode = next;
    }
    
    Wordnode* GetFront() { return frontnode; }
    Wordnode* GetBack() { return backnode; }
    
    

};

class Initial {
private:
    char initial;
    Wordnode* link = NULL, *tail, *head;
public:
    Initial() {}

    void SetInitial(char a) {
        initial = a;
    }
    void SetLink(Wordnode *next) {
        link = next;
    }
    void SetHead(Wordnode* asd) {
        head = asd;
    }
    void SetTail(Wordnode* asdf) {
        tail = asdf;
    }
    
    

    char GetInitial() { return initial; }
    Wordnode* GetLink() { return link; }
    Wordnode* GetHead() { return head; }
    Wordnode* GetTail() { return tail; }
};


class Menu {
private:
    Wordnode* node;
    Initial alpha[26];
public:
    Menu() {
        char a = 'a';
        for (int i = 0; i < 26; i++) {
            alpha[i].SetInitial(a);
            a++;
        }
    }
    void AddWord() {
        string word;
        int cnt = 0;       // 입력받은 글자의 알파벳이 몇번째 알파벳인지, alpha[cnt] == 맨 앞글자
        int bigwordcnt = 0;  // 0이면 대문자가 없음, 1이면 대문자가 있어서 소문자로 바꿈
        cout << "단어 입력 : ";
        cin >> word;               
        // 입력받은 문자를 소문자로 변환
        for (int i = 0; i < word.length(); i++) {         
            if ((word.at(i) >= 65) && (word.at(i) <= 90)) {
                word.at(i) += 32;
                bigwordcnt = 1;
            }
        }
        //몇번째 알파벳인지 구함
        for (int i = 0; i < 26; i++) {    
            if (word.at(0) == alpha[i].GetInitial()) {
                break;
            }
            cnt++;
        }

        node = new Wordnode;      // 새로운 노드생성
        node->GoData().SetWord(word); // 입력한 단어를 data에 저장
        ////예문 입력받기

        //해당 알파벳의 단어가 비었을 때
        if (alpha[cnt].GetLink() == NULL) {
            alpha[cnt].SetHead(node);
            node->Setfront(alpha[cnt].GetHead());
            alpha[cnt].SetLink(node);
            alpha[cnt].SetTail(node);
        }
        else {
            /// <summary>
            /// 주의사항 
            /// existingword, addword 는 자기 단어의 길이를 넘어가면 안됨, 넘어가지 않는 선에서 계속 비교 
            /// 단어의 길이는 cnt가 추가될 때마다 새로 비교
            /// 만약 넘어가면, 일단 뒤에 있는 문자랑도 비교, 뒤에문자보다 작은면 그 자리에 추가, 뒤에 문자보다 클시 계속 비교
            /// zero랑 zerocola 는 zerocola가 후순위
            /// 
            /// 단어 추가하는 순서
            /// a=기존 단어, b=추가하는 단어 
            /// 1 a랑 b의 사이즈 비교
            /// 2 wordnum번째 단어 비교 
            ///   같으면 wordnum++ ->1
            ///   a가 크면 b를 a앞에 저장
            ///   b가 크면 a뒤로 이동 ->1
            /// 
            /// </summary>
            int wordnum = 1;          //비교할 자리수. 첫번째 자리는 같으므로 2번째 자리부터 비교
            int minsize;          //두 단어중 짧은 단어의 길이
            Wordnode* ptr = alpha[cnt].GetLink(); // ptr은 기존단어
            while (true) {
                //minsize 설정 : 두 단어중 짧은 단어의 길이 넣기
                minsize = (ptr->GoData().GetWord().at(wordnum) > word.at(wordnum)) ? word.at(wordnum) : ptr->GoData().GetWord().at(wordnum);
                
                // 단어의 최소길이가 비교하는wordnum보다 같거나 클때, 즉 서로 비교할게 있을 때
                if (minsize >= wordnum) { 
                    if (ptr->GoData().GetWord().at(wordnum) == word.at(wordnum)) {
                        wordnum++;
                    }
                    else if (ptr->GoData().GetWord().at(wordnum) < word.at(wordnum)) {// 추가한 단어가 클때
                        
                    }
                }


            }
        }
        
        
    }

};

int main()
{
    Menu a;

    a.AddWord();
    

    return 0;
}