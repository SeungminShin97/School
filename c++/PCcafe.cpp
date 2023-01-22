/// <summary>
/// system("cls");
/// Signup-setUserinfo 생년월일, 전화번호에 숫자말고 다른거 입력할 때
/// □■
/// </summary>
#include <iostream>
#include <string>
#include <fstream>
#include <locale>
#include <Windows.h>
#include <time.h>

using namespace std;

class Userinfo { // 손님정보
private:
    string id;
    string password;
    int admin = 0;   //관리자는 1, 손님은 0
    string name;
    int birth;
    int phonenumber;
    int time = 0;
    int totalmoney = 0;
    Userinfo* link;
    int seatx = 0, seaty = 0;
public:
    Userinfo() {}
    Userinfo(string i, string p, string n, int a) {
        id = i; 
        password = p;
        name = n;
        admin = a;
    }

    void setId(string a) {
        id = a;
    }
    void setPassword(string a) {
        password = a;
    }
    void setName(string a) {
        name = a;
    }
    void setBirth(int a) {
        birth = a;
    }
    void setPhonenumber(int a) {
        phonenumber = a;
    }
    void setTotalmoney(int a) {
        totalmoney += a;
    }
    void setAdmin() {
        admin = 1;
    }
    void setTIme(int a) {
        time += a;
    }

    void setLink(Userinfo* a) {
        link = a;
    }
    void setSeatXY(int x, int y) {
        seatx = x;
        seaty = y;
    }
    void initiallizeSeatXY() {
        seatx = 0;
        seaty = 0;
    }

    int getTime() { return time; }
    int getSeatX() { return seatx; }
    int getSeatY() { return seaty; }
    string getId() { return id; }
    string getName() { return name; }
    int getTotalmoney() { return totalmoney; }
    Userinfo *getLink() { return link; }
    string getPassword() { return password; }
    // 관리자는 1, 손님은 0
    int getAdmin() { return admin; }
};

class Signup {
private:
    int usercnt = 0;
    Userinfo* userinfo;
    Userinfo* head = NULL, * tail = NULL, * ptr;
public:
    Signup() {}

    //회원가입
    void setUserinfo() {
        string id, password;
        while (true) {
            cout << " 사용할 아이디를 입력해주세요 : ";
            cin >> id;
            if (contrastName(id) == true) {
                cout << " 사용할 비밀번호를 입력해주세요 : ";
                cin >> password;
                break;
            }
            else
                cout << " 중복된 아이디입니다. 다른아이디를 사용해 주세요" << endl;
        }

        userinfo = new Userinfo;
        userinfo->setId(id);
        userinfo->setPassword(password);
        string name;
        cout << " 이름 : ";
        cin >> name;
        userinfo->setName(name);
        int birth, phonenumber;
        cout << " 생년월일(YYMMDD) : ";
        cin >> birth;
        userinfo->setBirth(birth);
        cout << " 전화번호(숫자만) : ";
        cin >> phonenumber;
        userinfo->setPhonenumber(phonenumber);

        if (usercnt == 0) {// 첫번째 아이디는 관리자 아이디
            userinfo->setAdmin();
        }

        if (head == NULL) {
            head = userinfo;
            tail = userinfo;
        }
        else {
            ptr = head;
            for (int i = 0; i < usercnt - 1; i++) {
                ptr = ptr->getLink();
            }
            ptr->setLink(userinfo);
            tail = userinfo;
        }
        usercnt++;
    }
    //노드삭제(회원탈퇴)
    void deleteUserinfo(string id) {
        ptr = head;
        int cnt = 0;
        while (true) {
            if (ptr->getId() == id) {
                break;
            }
            else{
                cnt++;
                ptr = ptr->getLink();
            }
        }
        if (cnt == 1) {// 2번째를 삭제할때, 첫번째는 관리자아이디
            ptr = head->getLink();
            head = ptr;
            delete ptr;
        }
        else {// 3번째~
            for (int i = 0; i < cnt - 1; i++) {
                ptr = ptr->getLink();
            }
            if (ptr->getLink()->getLink() == NULL) {//마지막 노드를 없앨때
                head = ptr;
                ptr = ptr->getLink();
                delete ptr;
            }
            else {//중간 노드를 없앨때
                ptr->setLink(ptr->getLink()->getLink());
                ptr = ptr->getLink();
                delete ptr;
            }
        }
    }
    //중복되는 아이디가 있나 찾는 함수, 중복되면 false, 중복안되면 true을 반환
    bool contrastName(string name) {
        int cnt = 0;
        ptr = head;
        if (ptr == NULL) { 
            return true;
        }
        if (ptr->getId() == name) {
            return false;
        }
        else {
            while (ptr->getLink() != NULL) {//2번째 이후 검사
                if (ptr->getId() == name) {
                    return false;
                }
                ptr = ptr->getLink();
            }
            if (ptr->getId() == name) {
                return false;
            }
        }

        return true;
    }
    //아이디 검색
    Userinfo* searchUserinfo(string asdf) {
        ptr = head;
        for (int i = 0; i < usercnt - 1; i++) {
            if (ptr->getId() == asdf) {
                return ptr;
            }
            ptr = ptr->getLink();
        }
        return ptr;
    }
    int getUsercnt() { return usercnt; }
    Userinfo* listUser() {
        return head;
    }

    void setUserinfoauto(string id, string password, string name , int admin) {
        userinfo = new Userinfo(id, password, name, admin);
        if (head == NULL) {
            head = userinfo;
            tail = userinfo;
        }
        else {
            ptr = head;
            while (ptr->getLink() != NULL) {
                ptr = ptr->getLink();
            }
            ptr->setLink(userinfo);
            tail = userinfo;
        }
    }
};

class Seat {
private:
    string info; // used = 사용중, empty = 빈자리, none = 빈공간
    int a;
public:
    Seat() {}
    //사용중
    void setUsed() {
        info = "used";
        a = 1;
    }
    //빈자리
    void setEmpty() {
        info = "empty";
        a = 2;
    }
    void setNone() {
        info = "none";
        a = 3;
    }
    //used = 사용중, empty = 빈자리, none = 빈공간
    string getInfo() { return info; }
    int getcnt() { return a; }
};

class Seatnode {
private:
    string seatinfo;
    Seatnode* link;
public:
    Seatnode() {}
    Seatnode(string asdf) {
        seatinfo = asdf;
    }
    void setSeatnode(string asdf) {
        seatinfo = asdf;
    }
    void setLink(Seatnode* node) {
        link = node;
    }

    string getSeatinfo() { return seatinfo; }
    Seatnode* getLink() { return link; }
};

class Seatinfo {
private:
    int seatline = 0;//자리의 행 
    int maxlength = 0; // 자리의 열 
    Seat** seat;
    Seatnode* node, * head = NULL, * tail = NULL, * ptr;
    int usepeople = 0;
public:
    Seatinfo() {}
    //직접 자리설정
    void setSeatinfo() {
        cout << " ┌-----------------------------------------------------┐" << endl;
        cout << " │ 좌석 설정하는 법                                    │" << endl;
        cout << " │ e = 빈공간, s=컴퓨터자리                            │" << endl;
        cout << " │ 다음줄을 추가할때에는 끝에 'n'을 붙이세요           │" << endl;
        cout << " │ 예시 :                                              │" << endl;
        cout << " │ ssssssssssn                                         │" << endl;
        cout << " │ eeeeeeeeeen                                         │" << endl;
        cout << " │ ssssssesss            <---끝에 n 이 없으므로 종료   │" << endl;
        cout << " └-----------------------------------------------------┘" << endl;
        seatline = 0;
        maxlength = 0;
        while (true) {
            string seatinfo;
            cout << "입력 : ";
            cin >> seatinfo;
            node = new Seatnode(seatinfo);
            if (head == NULL) {
                head = node;
                tail = node;
            }
            else {
                ptr = head;
                for (int i = 0; i < seatline - 1; i++) {
                    ptr = ptr->getLink();
                }
                ptr->setLink(node);
                tail = node;
            }
            seatline++;
            int seatinfolength = seatinfo.length();//경고뜨길래 unsigned int에서 int로 바꿔줌
            if (seatinfolength >= maxlength) // 가장 긴 길이 구하기
                maxlength = seatinfolength;
            if (seatinfo.back() != 'n') //맨뒤의 글자가 'ㅇ'가 아니면 입력종료
                break;
        }
        ptr = head;
        for (int i = 0; i < seatline; i++) { // 가로의 길이가 다를경우 가장 긴 가로에 맞춤
            string asdf = ptr->getSeatinfo();
            asdf.resize(maxlength, 'e');
            ptr->setSeatnode(asdf);
            ptr = ptr->getLink();
        }
        seat = new Seat* [seatline];//3차원 동적배열 생성= 자리정보
        for (int i = 0; i < seatline; i++) {
            seat[i] = new Seat[maxlength];
        }
        ptr = head;
        for (int i = 0; i < seatline; i++) {
            for (int j = 0; j < maxlength; j++) {
                if (ptr->getSeatinfo()[j] == 's')
                    seat[i][j].setEmpty();
                else
                    seat[i][j].setNone();
            }
            ptr = ptr->getLink();
        }
        for (int i = 0; i < seatline; i++) {// 자리만들려고 생성한 노드 삭제
            ptr = head;
            head = head->getLink();
            delete ptr;
        }
    }
    //자리 출력
    void showSeatinfo() {
        cout << " ┌--";
        for (int i = 0; i < maxlength - 1; i++) {
            cout << "---";
        }
        cout << "--┐" << endl;
        cout << " │    ";
        for (int i = 0; i < maxlength - 1; i++) {
            cout.width(2);
            cout << i + 1 << " ";
        }
        cout << "│";
        cout << endl;
        for (int i = 0; i < seatline; i++) {
            cout << " │  " << i + 1 << " ";
            for (int j = 0; j < maxlength - 1; j++) {
                if (seat[i][j].getInfo() == "used") {
                    cout << "■" << " ";
                }
                else if (seat[i][j].getInfo() == "empty") {
                    cout << "□" << " ";
                }
                else
                    cout << "  " << " ";
            }
            cout << "│" << endl;
        }
        cout << " └--";
        for (int i = 0; i < maxlength - 1; i++) {
            cout << "---";
        }
        cout << "--┘\n\n";
    }
    
    string goSeat(int x, int y) {
        if (seat[x][y].getInfo() == "empty") {
            return "empty";
        }
        else if (seat[x][y].getInfo() == "used") {
            return "used";
        }
        else
            return "none";
    }
    Seat** goSeat() {
        return seat;
    }
    void emptySeat(int x, int y) {
        seat[x][y].setEmpty();
    }
    

    int getMaxlength() { return maxlength; }
    int getSeatline() { return seatline; }
    int getUsepeople() { return usepeople; }
};

class Menu {
private:
    Menu* link = NULL;
    string name;
    int cnt = 0;
    int price;
public:
    Menu() {}
    Menu(string n, int p, int c) {
        name = n;
        price = p;
        cnt = c;
    }
    Menu(string asdf){
        name = asdf;
    }

    void setName(string asdf) {
        name = asdf;
    }
    void setPrice(int asdf) {
        price = asdf;
    }
    void setLink(Menu* asdf) {
        link = asdf;
    }
    void setCnt(int a) {
        cnt = a;
    }

    int getCnt() { return cnt; }
    string getName() { return name;}
    int getPrice() { return price; }
    Menu* getLink() { return link; }
};

class Category {
private:
    Category* link = NULL;
    Menu* menulink = NULL, *ptr; // menulink == Menu* link 
    Menu* menu;
    string name;
    int menucnt = 0;
public:
    Category() {}
    Category(string asdf) {
        name = asdf;
    }
    // 3. 세부메뉴 설정 -->> 1. 메뉴추가
    void setMenu(Category *asdf) {
        string name;
        int price;
        cout << " 메뉴 이름 : ";
        cin >> name;
        if (contrastMenu(name) == true) {
            menu = new Menu(name);
            cout << " 가격 : ";
            cin >> price;
            menu->setPrice(price);
            if (asdf->getMenulink() == NULL) {
                menulink = menu;
            }
            else {
                ptr = asdf->getMenulink();
                while (ptr->getLink() != NULL) {
                    ptr = ptr->getLink();
                }
                ptr->setLink(menu);
            }
            cout << " (" << name << ") 이/가 생성되었습니다. " << endl;
        }
        menucnt++;
    }

   
   
    // 메뉴확인 있으면 true 없으면 false
    bool contrastMenu(string asdf) {
        ptr = menulink;
        for (int i = 0; i < menucnt; i++) {
            if (ptr->getName() == asdf) {
                return false;
            }
            ptr = ptr->getLink();
        }
        return true;
    }
    // 메뉴를 반환하는 함수, 메뉴명 입력
    Menu* getMenuName(string asdf) {
        ptr = menulink;
        for (int i = 0; i < menucnt; i++) {
            if (ptr->getName() == asdf) {
                return ptr;
            }
            ptr = ptr->getLink();
        }
        return ptr;
    }

    void setLink(Category* asdf) {
        link = asdf;
    }
    void setMenulink(Menu* asdf) {
        menulink = asdf;
    }

    Menu* getMenulink() { return menulink; }
    string getCategoryName() { return name; }
    Category* getLink() { return link; }
    int getMenucnt() { return menucnt; }
};

class Product {
private:
    Category* category;
    Category* ptr,* head =NULL;
    Menu* time,* mptr;
    Menu* orderlist, * orderlisthead = NULL;
    int orderlistcnt = 0;
    int ordertime = 0;
    int categorycnt = 0;
    
public:
    Product() {}
    // 1. 상품종류 추가
    void setCategory() {
        string title;
        cout << " 추가할 상품 종류를 입력하세요 " << endl;
        cout << " 입력 : ";
        cin >> title;
        if (contrastCategory(title) == true) {
            cout << " 입력하신 음식 종류가 이미 존재합니다." << endl;
        }
        else {
            category = new Category(title);
            if (categorycnt == 0) {
                head = category;
            }
            else {
                ptr = head;
                for (int i = 0; i < categorycnt - 1; i++) {
                    ptr = ptr->getLink();
                }
                ptr->setLink(category);
            }
            cout << " (" << title << ")이/가 추가되었습니다. \n" << endl;
        }
        categorycnt++;
    }
    // 2. 상품종류 삭제
    void deleteCategory() {
        string title;
        cout << " 삭제할 상품 이름을 입력하세요 : ";
        cin >> title;
        if (contrastCategory(title) == true) {
            ptr = head;
            Category* delcate,* cptr = head;
            Menu* delmenu, *delcopy;
            if (categorycnt == 1) {          //카테고리가 1개일 때
                if (ptr->getMenucnt() == 0) {// 메뉴가 없을때
                    delete ptr;
                    head = NULL;
                }
                else { //메뉴가 있을때
                    delmenu = ptr->getMenulink();
                    for (int i = 0; i < ptr->getMenucnt(); i++) {
                        delcopy = delmenu;
                        delmenu = delmenu->getLink();
                        delete delcopy;
                    }
                    delete ptr;
                    head = NULL;
                }
            }
            else if (categorycnt > 1) {//카테고리가 1개 이상일때
                if (cptr == getCategoryName(title)) { // 첫번째꺼
                    head = cptr->getLink();
                    if (cptr->getMenucnt() == 0) {
                        delete cptr;
                        
                    }
                    else {
                        delmenu = cptr->getMenulink();
                        for (int i = 0; i < cptr->getMenucnt(); i++) {
                            delcopy = delmenu;
                            delmenu = delmenu->getLink();
                            delete delcopy;
                        }
                        head = cptr->getLink();
                        delete ptr;
                        
                    }
                }
                else {
                    delcate = head;
                    while (delcate->getLink() != getCategoryName(title)) {
                        delcate = delcate->getLink();
                    }
                    if (getCategoryName(title)->getLink() == NULL) {
                        if (getCategoryName(title)->getMenucnt() == 0) {// 메뉴가 없을때
                            delete delcate->getLink();
                            delcate->setLink(NULL);
                        }
                        else { //메뉴가 있을때
                            delmenu = getCategoryName(title)->getMenulink();
                            for (int i = 0; i < delcate->getMenucnt(); i++) {
                                delcopy = delmenu;
                                delmenu = delmenu->getLink();
                                delete delcopy;
                            }
                            delete delcate->getLink();
                            delcate->setLink(NULL);
                        }
                    }
                    else{
                        if (getCategoryName(title)->getMenucnt() == 0) {
                            cptr = delcate->getLink();
                            delcate->setLink(cptr->getLink());
                            delete cptr;
                        }
                        else {
                            delmenu = delcate->getMenulink();
                            for (int i = 0; i < delcate->getMenucnt(); i++) {
                                delcopy = delmenu;
                                delmenu = delmenu->getLink();
                                delete delcopy;
                            }
                            cptr = delcate->getLink();
                            delcate->setLink(cptr->getLink());
                            delete cptr;
                        }
                    }
                }
            }
        }
        categorycnt--;
    }
    // 3. 세부메뉴 설정
    void setMenu() {
        string category;
        cout << " 메뉴를 설정할 상품 : ";
        cin >> category;
        if (contrastCategory(category) == true) {
            getCategoryName(category)->setMenu(getCategoryName(category));
        }
        
    }
    // 시간설정
    void setTime() {
        int price;
        cout << " 시간당 금액 설정" << endl;
        cout << " 1시간(원) : ";
        cin >> price;
        time = new Menu;
        time->setName("1시간");
        time->setPrice(price);
    }

    //상품목록
    void showProduct() {
        ptr = head;
        for (int i = 0; i < categorycnt; i++) {
            cout.width(14);
            cout << ptr->getCategoryName();
            cout << " : ";
            mptr = ptr->getMenulink();
            for (int i = 0; i < ptr->getMenucnt(); i++) {
                cout.width(14);
                cout << mptr->getName();
                mptr = mptr->getLink();
            }
            cout << endl;
            mptr = ptr->getMenulink();
            cout.width(14);
            cout << "가격";
            cout << " : ";
            for (int i = 0; i < ptr->getMenucnt(); i++) {
                cout.width(14);
                cout << mptr->getPrice();
                mptr = mptr->getLink();
            }
            cout << endl;
            ptr = ptr->getLink();
        }
        cout << endl;
    }
    // 상품주문
    void orderProduct(Userinfo* asdf) {
        showProduct();
        cout << " 주문하실 상품을 입력해주세요 : " << endl;
        string menu;
        cout << " 입력 >> ";
        cin >> menu;
        ptr = head;
        Menu* node = NULL;
        for (int i = 0; i < categorycnt; i++) {
            mptr = ptr->getMenulink();
            for (int j = 0; j < ptr->getMenucnt(); j++) {
                if (mptr->getName() == menu) {
                    node = mptr;
                }
                mptr = mptr->getLink();
            }
            ptr = ptr->getLink();
        }
        if (node == NULL) {
            cout << " 입력하신 상품을 찾을 수 없습니다." << endl;
        }
        else {
            cout << " " << node->getName() << "의 가격은 " << node->getPrice() << "입니다." << endl;
            cout << " 구매하실 개수 : ";
            int cnt;
            cout << " 입력 >> ";
            cin >> cnt;
            cout << " 가격은 " << node->getPrice() * cnt << "원 입니다." << endl;
            asdf->setTotalmoney(node->getPrice() * cnt);

            orderlist = new Menu(node->getName(), node->getPrice(), cnt);
            if (orderlistcnt == 0) {
                orderlisthead = orderlist;
            }
            else {
                mptr = orderlisthead;
                for (int i = 0; i < orderlistcnt - 1; i++) {
                    mptr = mptr->getLink();
                }
                mptr->setLink(orderlist);
                orderlistcnt++;
            }
        }
    }
    //시간추가
    void orderTime(Userinfo* asdf) {
        cout << " 시간당 금액은 " << time->getPrice() << "원 입니다." << endl;
        cout << " 구매할 시간 : ";
        int cnt;
        cin >> cnt;
        cout << " " << cnt << "시간이 충전되었습니다." << endl;
        asdf->setTIme(cnt);
        asdf->setTotalmoney(cnt * time->getPrice());
        ordertime += cnt;
    }

    //상품을 반환하는 함수, 상품명 입력
    Category* getCategoryName(string asdf) {
        ptr = head;
        for (int i = 0; i < categorycnt; i++) {
            if (ptr->getCategoryName() == asdf) {
                return ptr;
            }
            ptr = ptr->getLink();
        }
        return ptr;
    }
    //상품종류 확인 있으면true 없으면 false
    bool contrastCategory(string asdf) {
        ptr = head;
        for (int i = 0; i < categorycnt; i++) {
            if (ptr->getCategoryName() == asdf) {
                return true;
            }
            ptr = ptr->getLink();
        }
        return false;
    }
    Menu* getTime() { return time; }
    int getOrderlistcnt() { return orderlistcnt; }
    Menu* getOrderlist() {
        return orderlist;
    }
};

void save(Signup* signup) {
    Userinfo* asdf = signup->listUser();
    fstream myfile;
    myfile.open("client.txt", ios::out);
    myfile << signup->getUsercnt() << endl;
    for (int i = 0; i < signup->getUsercnt(); i++) {
        myfile << asdf->getId()<< " " << asdf->getPassword() << " " << asdf->getName() << " " << asdf->getAdmin() << endl;
        asdf = asdf->getLink();
    }
    myfile.close();
}

void load(Signup* signup) {
    fstream myfile;
    myfile.open("client.txt", ios::in);
    int cnt;
    myfile >> cnt;
    string id, password, name;
    int admin;
    for (int i = 0; i < cnt; i++) {
        myfile >> id >> password >> name >> admin;
        signup->setUserinfoauto(id, password, name, admin);
    }
}


class Login {
protected:
    Userinfo *userinfo = NULL;
    Seatinfo *seatinfo;
    Signup *signup;
    Product* product;
public:
    Login() {
        seatinfo = new Seatinfo;
        signup = new Signup;
        product = new Product;
    }
    
    // 로그인하기
    void login() {
        string id, password;
        while (true) {
            cout << "\n\n" << "   아이디 : ";
            cin >> id;
            if (signup->contrastName(id) == true) {
                cout << " 존재하지 않는 아이디입니다. 다시 입력해주세요" << endl;
            }
            else {
                cout << " 비밀번호 : ";
                cin >> password;
                if (password == signup->searchUserinfo(id)->getPassword()) {//로그인
                    logmain(signup->searchUserinfo(id));
                    break;
                }
                else {
                    cout << " 비밀번호가 틀렸습니다. 다시 입력해주세요" << endl;
                }
            }
        }
        userinfo = NULL;
    }
    //로그인후 화면
    void logmain(Userinfo* asdf) {
        userinfo = asdf;
        if (asdf->getAdmin() == 0) {
            cout << " 환영합니다! " << userinfo->getName() << "님 " << endl;
            if (userinfo->getSeatX() == 0) {
                cout << " 먼저 자리를 선택해주세요 " << endl;
                seatinfo->showSeatinfo();
                int x, y;
                while (true) {
                    cout << " x : ";
                    cin >> x;
                    if (x <= seatinfo->getMaxlength() - 1) {
                        break;
                    }
                    else
                        cout << " 1 ~" << seatinfo->getMaxlength() - 1 << " 사이의 숫자를 입력해주세요" << endl;
                }
                while (true) {
                    cout << " y : ";
                    cin >> y;
                    if (y <= seatinfo->getSeatline()) {
                        break;
                    }
                    else cout << " 1 ~" << seatinfo->getSeatline() << " 사이의 숫자를 입력해주세요" << endl;
                }
                seatinfo->goSeat(y, x);                
                if (seatinfo->goSeat(y - 1,x - 1) == "empty") {
                    seatinfo->goSeat()[y - 1][x - 1].setUsed();
                    asdf->setSeatXY(y, x);
                }
                else
                    cout << " 사용중인 좌석입니다." << endl;
            
            }
            cout << " 현재 자리는 (" << asdf->getSeatX() << ", " << asdf->getSeatY() << ") 입니다. " << endl;
            
            int outmenu = 0;
            while (outmenu == 0) {
                cout << " 1. 상품 주문하기" << endl;
                cout << " 2. 시간충전" << endl;
                cout << " 3. 개인정보 수정하기" << endl;
                cout << " 9. 사용종료" << endl;
                cout << " 0. 로그아웃" << endl;
                cout << " 입력 >> ";
                int menu;
                cin >> menu;
                if (menu == 1) {
                    product->orderProduct(asdf);
                }
                else if (menu == 2) {
                    product->orderTime(asdf);
                }
                else if (menu == 3) {
                    cout << " 1. 비밀번호 변경" << endl;
                    cout << " 2. 이름 변경" << endl;
                    cout << " 3. 생일 변경" << endl;
                    cout << " 4. 전화번호 변경" << endl;
                    int cnt;
                    cout << " 입력 >> ";
                    cin >> cnt;
                    if (cnt == 1) {
                        cout << " 새로운 비밀번호";
                        string p;
                        cin >> p;
                        asdf->setPassword(p);
                        cout << " 비밀번호를 변경했습니다. " << endl;
                    }
                    else if (cnt == 2) {
                        cout << " 새로운 이름 : ";
                        string n;
                        cin >> n;
                        asdf->setName(n);
                        cout << " 이름을 변경했습니다. " << endl;
                    }
                    else if (cnt == 3) {
                        cout << " 새로운 생일 : ";
                        int b;
                        cin >> b;
                        asdf->setBirth(b);
                        cout << " 생일을 변경했습니다. " << endl;
                    }
                    else if (cnt == 4) {
                        cout << " 새로운 번호 : ";
                        int p;
                        cin >> p;
                        asdf->setPhonenumber(p);
                    }
                    else
                        cout << " 잘못된 값입니다.";

                }
                else if (menu == 9) {
                    seatinfo->emptySeat(asdf->getSeatX() - 1, asdf->getSeatY() - 1);
                    outmenu++;
                }
                else if (menu == 0) {
                    outmenu++;
                }
                else
                    cout << "잘못된 값입니다." << endl;
                
            }
        }
        else {
            int menucnt = 0;
            while (menucnt == 0) {
                cout << " 관리자 설정" << endl;
                cout << " 1. 자리배치(자리초기화)" << endl;
                cout << " 2. 상품편집" << endl;
                cout << " 3. 시간당 금액 수정" << endl;
                cout << " 4. 회원정보검색" << endl;
                cout << " 5. 주문확인" << endl;
                cout << " 0. 로그아웃" << endl;
                cout << " 입력 >> ";
                int menu;
                cin >> menu;
                if (menu == 1) { // 1.자리배치(자리초기화)
                    cout << " 정말 초기화 하시겟습니까?" << endl;
                    cout << " 1. 네     2. 아니오" << endl;
                    cout << " 입력 >>" << endl;
                    int a;
                    cin >> a;
                    if (a == 1) {
                        if (seatinfo->getUsepeople() == 0) {
                            seatinfo->setSeatinfo();
                            seatinfo->showSeatinfo();
                        }
                        else
                            cout << " 사용중인 사람이 있을 경우 자리배치 설정을 이용할 수 없습니다. " << endl;
                    }
                }
                else if (menu == 2) { // 2. 상품편집
                    product->showProduct();
                    cout << " 1. 상품종류 추가" << endl;
                    cout << " 2. 상품종류 삭제" << endl;
                    cout << " 3. 세부메뉴 설정" << endl;
                    cout << " 0. 종료" << endl;
                    cout << " 입력 >> ";
                    int menu;
                    cin >> menu;
                    if (menu == 1) {
                        product->setCategory();
                    }
                    else if (menu == 2) {
                        product->deleteCategory();
                    }
                    else if (menu == 3) {
                        product->setMenu();
                    }
                    else if (menu == 0) {

                    }
                    else
                        cout << " 잘못된 값입니다." << endl;
                }
                else if (menu == 3) { // 3. 시간당금액 설정
                    product->setTime();
                }
                else if (menu == 4) {
                    string name;
                    cout << " 검색할 회원의 이름을 입력하세요 : ";
                    cin >> name;
                    if (goSignup()->contrastName(name) == false) {
                        cout << "   아이디 : " << goSignup()->searchUserinfo(name)->getId() << endl;
                        cout << "     이름 : " << goSignup()->searchUserinfo(name)->getName() << endl;
                        cout << " 비밀번호 : " << goSignup()->searchUserinfo(name)->getPassword() << endl;
                        cout << " 사용금액 : " << goSignup()->searchUserinfo(name)->getTotalmoney() << endl;
                    }
                    else
                        cout << " 입력하신 " << name << " 회원님은 존재하지 않습니다.";
                }
                else if (menu == 5) {
                    cout << " 주문내역" << endl;
                    for (int i = 0; i < product->getOrderlist()->getCnt(); i++) {
                        cout << product->getOrderlist()->getName() << " : " << product->getOrderlist()->getPrice() << endl;

                    }

                }
                else if (menu == 0) {
                    menucnt++;
                    userinfo = NULL;
                }
                else
                cout << " 잘못된 값입니다." << endl;
            }
        }
    }




    //자리출력
    void showSeatinfo() {
        seatinfo->showSeatinfo();
    }

    Seatinfo* goSeatinfo() { return seatinfo; }
    Signup* goSignup() { return signup; }
    Product* goproduct() { return product; }

};



int main()
{

    Login login;
    cout << " 환영합니다!! PC방 관리 프로그램입니다." << endl;
    cout << " 설정에 앞서 관리자 아이디를 만들어주세요." << endl;
    cout << " ---------------------------------------------" << endl;
    login.goSignup()->setUserinfo();
    login.goSeatinfo()->setSeatinfo();
    load(login.goSignup());
    cout << " 기본설정이 끝났습니다." << endl;
    cout << " 로그인 후 상품설정과 시간당 금액을 설정해 주세요" << endl;
    while (true) {
        login.showSeatinfo();
        int menu;
        cout << " ┌----------------------------┐" << endl;
        cout << " │  1. 회원가입    2. 로그인  │" << endl;
        cout << " └----------------------------┘" << endl;
        cout << " 입력 >> ";
        cin >> menu;
        if (menu == 1) {
            login.goSignup()->setUserinfo();
        }
        else if (menu == 2) {
            login.login();
        }
        else{
            save(login.goSignup());
            break;
        }
    }





    return 0;
}
