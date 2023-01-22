#include <LiquidCrystal.h>
#include <DS1302.h>
#include <MemoryFree.h>
LiquidCrystal lcd(12, 11, 5, 4, 3, 6);

//RTC
const int CEPin = 0;
const int IOPin = 1;
const int CLKPin = 10;
DS1302 rtc(CEPin, IOPin, CLKPin);
String dayAsString(const Time::Day day) {
  switch(day) {
    case Time::kSunday: return "Sunday";
    case Time::kMonday: return "Monday";
    case Time::kTuesday: return "Tuesday";
    case Time::kWednesday: return "Wednesday";
    case Time::kThursday: return "Thursday";
    case Time::kFriday: return "Friday";
    case Time::kSaturday: return "Saturday";
  }
  return "(unknown day)";
}
//조이스틱
const int xAxisPin = 0;
const int yAxisPin = 1;
const int zAxisPin = 2;
//DotMatrix
const int DataPin = 7;
const int LatchPin = 8;
const int ClockPin = 9;
byte row[8] = {~B00000001, ~B00000010, ~B00000100, ~B00001000, ~B00010000,
               ~B00100000, ~B01000000, ~B10000000
              };
byte col[8] = {B10000000, B01000000, B00100000, B00010000, B00001000,
               B00000100, B00000010, B00000001
              };

int Map[8][8] = { 0 }; // 뱀의 몸통은 1, 빈공간은 0

int way = 0; // 뱀이 가는 방향 상,하,좌,우
enum {right = 1, left, up, down};
enum {gameset, gameready, gamestart}; // 게임 시작 상태
int gamemode = gameset; // 초기상태
int slength = 1; // 뱀의 길이
unsigned int fx, fy; // 먹이의 좌표(x,y)
int foodnum = 0;  //먹이가 존재하면 1, 아니면 0
enum {empty, exist};  //먹이 유무
int SnakeSpeed = 400;  //뱀 속도; 초기값 600

struct Snake { //뱀의 몸통은 링크드 리스트로 구현
  struct Snake *next = NULL;
  int x, y;
  void setX(int n) {x = n;}
  void setY(int m) {y = m;}
  void setNext(Snake *a){next = a;}
  int getX() {return x;}
  int getY() {return y;}
  Snake* getNext() {return next;}
} *head = NULL, *ptr = NULL, *node = NULL;

void setup() {
  Serial.begin(9600);
  lcd.begin(16,2);
  lcd.print("Snake Game!!!");
  pinMode(zAxisPin, INPUT_PULLUP);
  pinMode(LatchPin, OUTPUT);
  pinMode(ClockPin, OUTPUT);
  pinMode(DataPin, OUTPUT);
  rtc.writeProtect(false);
  rtc.halt(false);
  Time t(2022,6,17,00,00,00,Time::kSaturday);
  rtc.time(t);
}

void loop() {
  
  int xValue = analogRead(xAxisPin);
  int yValue = analogRead(yAxisPin);
  int zValue = digitalRead(zAxisPin);  // 누르면 0

  int xDisplay = map(xValue, 0, 1023, 6, 15);
  int yDisplay = map(yValue, 0, 1023, 6, 15);
  int zDisplay = map(zValue, 0, 1023, 6, 15);

  if(gamemode == gamestart){    
    if(!foodnum) food();  //먹이생성
    Serial.print("메모리 상황 : ");
    Serial.println(freeMemory());
    Serial.print("뱀의 길이 : ");
    Serial.println(slength);
    Serial.print("fx : ");
    Serial.println(fx);
    Serial.print("fy : ");
    Serial.println(fy);
    ptr = head;
    for(int i = 0; i < slength; ++i){
      Serial.print(i);
      Serial.print("번째 x좌표 : ");
      Serial.println(ptr->getX());
      Serial.print(i);
      Serial.print("번째 y좌표 : ");
      Serial.println(ptr->getY());
      ptr = ptr->getNext();
    }
    Serial.print("방향 : ");
    Serial.println(way);
    Serial.println();
    Serial.println();
    
    lcd.clear();
    lcd.setCursor(0,0);
    lcd.print("Game Start!!!");
    lcd.setCursor(0,1);
    lcd.print(fx);
    lcd.print(" : ");
    lcd.print(fy);
    //lcd.print(slength);
    //방향 설정
    if (xValue > 600) way = right;  
    else if (xValue < 300) way = left;
    else if (yValue > 600) way = up;
    else if (yValue < 300) way = down;
    //방향에 맞게 한칸이동
    SnakeMove();
    delay(1000);
    //printDot(way);
    if(head->getX() == fx && head->getY() == fy){ //뱀의 머리가 먹이 위치랑 같아질 때
      NewBody();  //뱀 몸통 추가
      foodnum = empty;  //먹이 초기화
      Map[fx][fy] = 0;
    }
  }
  else{
    switch(gamemode){
      case gameset:
        PrintTime();
        if(zValue == 0) {
          delay(100);
          gamemode = gameready;
        }
        break;
      case gameready:
        GameReady();
        if(zValue == 0) gamemode = gamestart;
        break;
      default:
        lcd.print("error");
        delay(1000);
        gamemode = gameset;
        break;
    }
  }
}

void GameReady(){ // 뱀 몸통 동적할당 후 게임 시작모드로 변경
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("Ready to game");
  lcd.setCursor(0,1);
  lcd.print("Press the Button");
  digitalWrite(LatchPin, LOW);
  shiftOut(DataPin, ClockPin, LSBFIRST, row[3]);
  shiftOut(DataPin, ClockPin, LSBFIRST, col[3]);
  digitalWrite(LatchPin, HIGH);

  if(head == NULL){
    node = (Snake*)malloc(sizeof(Snake)); //머리 생성
    node->setX(3); // 시작좌표 3,3
    node->setY(3); // 시작좌표 3,3
    head = node;   // 머리를 가리키는 포인터 head
    Map[3][3] = 1;
  }
}

void food() {  // 뱀의 먹이 생성
  int rnd;
  while (1) {
    randomSeed(analogRead(13));
    rnd = random(8);
    fx = rnd % 8;
    rnd = random(8);
    fy = rnd % 8;
    if (Map[fx][fy] == 0){ 
      foodnum = exist;
      break;
    }
  }
}

void SnakeMove(){
  int headX, headY;
  ptr = head;
  headX = head->getX();
  headY = head->getY();
  if(slength > 1){
    int beforeX, beforeY, afterX, afterY;
    beforeX = headX;
    beforeY = headY;
    for(int i = 0; i < (slength - 1); ++i){
      ptr = ptr->getNext();
      afterX = ptr->getX();
      afterY = ptr->getY();
      swap(afterX, beforeX);
      swap(afterY, beforeY);
      ptr->setX(beforeX);
      ptr->setY(beforeY);
    }
    head->setX(headX);
    head->setY(headY);
  }
  if (way == right) head->setX(head->getX() + 1); 
  else if (way == left) head->setX(head->getX() - 1);
  else if (way == up) head->setY(head->getY() + 1);
  else if (way == down) head->setY(head->getY() - 1);
  if (head->getX() > 7) head->setX(0);
  else if (head->getX() < 0) head->setX(7);
  else if (head->getY() > 7) head->setY(0);
  else if (head->getY() < 0) head->setY(7);
}

void NewBody(){ // 몸통 하나 추가
  node = (Snake*)malloc(sizeof(Snake));
  ptr = head;
  for(int i = 0; i < (slength - 1); ++i)
    ptr = ptr->getNext();
  ptr->setNext(node);
  node->setX(ptr->getX());
  node->setY(ptr->getY());
  slength += 1;  // 전체 뱀의 길이++
}

void printDot(int a) {  // 점 출력(뱀 출력)
  int delaynum = 0;
  ptr = head;
  **Map = {0};/*
  while(delaynum < SnakeSpeed){
    /*    digitalWrite(LatchPin, LOW);
    shiftOut(DataPin, ClockPin, LSBFIRST, row[ptr->getX()]);
    shiftOut(DataPin, ClockPin, LSBFIRST, col[ptr->getY()]);
    digitalWrite(LatchPin, HIGH);
    delay(1);

    Map[ptr->getX()][ptr->getY()] = 1;
    if(slength > 1){
      if(ptr->getNext() == NULL)
        ptr = head;
      else
        ptr = ptr->getNext();
    }
    if(foodnum == exist && (delaynum / 150) % 2 == 0){
      digitalWrite(LatchPin, LOW);
      shiftOut(DataPin, ClockPin, LSBFIRST, row[fx]);
      shiftOut(DataPin, ClockPin, LSBFIRST, col[fy]);
      digitalWrite(LatchPin, HIGH);
    }
    delaynum++;
  }*/
  for(int i = 0; i < slength; ++i){
    Map[ptr->getX()][ptr->getY()] = 1;
    ptr = ptr->getNext();
  }
  delay(1000);
}

void swap(int *a, int *b){
  int *temp;
  *temp = *a;
  *a = *b;
  *b = *a;
}

void PrintTime(){ // 현재 시간 출력
  lcd.setCursor(0,1);
  Time t = rtc.time();
  const String day = dayAsString(t.day);
  lcd.print(t.yr);
  lcd.print("-");
  lcd.print(t.mon);
  lcd.print("-");
  lcd.print(t.date);
  lcd.print(" ");
  lcd.print(t.min);
  lcd.print(":");
  lcd.println(t.sec);
  if(t.min < 10) lcd.setCursor((t.sec >= 10)?14:13,1);
  else lcd.setCursor((t.sec >= 10)?15:14,1);
  lcd.print("  ");
  delay(100);
}
