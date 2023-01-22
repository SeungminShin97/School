#include <LiquidCrystal.h>
#include <DS1302.h>
#include <MemoryFree.h>
LiquidCrystal lcd(12, 11, 5, 4, 3, 6);

//RTC
const int CEPin = 0;
const int IOPin = 1;
const int CLKPin = 10;
DS1302 rtc(CEPin, IOPin, CLKPin);
String dayAsString(const Time::Day day) { //날짜 설정
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
              };  //dot matrix를 90도 돌려서 사용, 기존 배열의 순서를 반대로 설정

int way = 0; // 뱀이 가는 방향 상,하,좌,우
enum {right = 1, left, up, down};
enum {gameset, gameready, gamestart, gameover}; // 게임 시작 상태
int gamemode = gameset; // 초기상태
unsigned int fx, fy; // 먹이의 좌표(x,y)
int foodnum = 0;  //먹이가 존재하면 1, 아니면 0
enum {empty, exist};  //먹이 유무
int Speed = 400;  //뱀 속도; 초기값 400
int dotX, dotY; //점의 좌표
int score = 0;

void setup() {
  lcd.begin(16,2);
  lcd.print("Game!!!");
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
    lcd.clear();
    lcd.setCursor(0,0);
    lcd.print("Game Start!!!");
    lcd.setCursor(0,1);
    lcd.print(way);
    if(!foodnum) food();
    //방향 설정
    if (xValue > 600) way = right;  
    else if (xValue < 300) way = left;
    else if (yValue > 600) way = up;
    else if (yValue < 300) way = down;
    //방향에 맞게 한칸이동
    if (way == right) dotX++; 
    else if (way == left) dotX--;
    else if (way == up) dotY++;
    else if (way == down) dotY--;
    if(dotX > 7 || dotX < 0 || dotY > 7 || dotY < 0)  // 점이 화면 밖으로 넘어갔을 경우 게임오버
      gamemode = gameover;
    if(gamemode != gameover){
      printDot();
      if(dotX == fx && dotY == fy){ //점이 먹이 위치랑 같아질 때
        foodnum = empty;  //먹이 초기화
        score++;
        Speed -= 25;
      }
    }
  }
  else{
    switch(gamemode){
      case gameset: 
        printTime();
        if(zValue == 0) {
          delay(100);
          gamemode = gameready;
        }
        break;
      case gameready:
        GameReady();
        if(zValue == 0) gamemode = gamestart;
        break;
      case gameover:
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Game Over!!!");
        lcd.setCursor(0,1);
        lcd.print("Score : ");
        lcd.setCursor(8,1);
        lcd.print(score);
        if(zValue == 0)
          gamemode = gameset;
        break;
      default:
        lcd.print("error");
        delay(1000);
        gamemode = gameset;
        break;
    }
  }
}

void GameReady(){ //게임 준비화면, 시작위치를 표시
  lcd.clear();
  lcd.setCursor(0,0);
  lcd.print("Ready to game");
  lcd.setCursor(0,1);
  lcd.print("Press the Button");
  digitalWrite(LatchPin, LOW);
  shiftOut(DataPin, ClockPin, LSBFIRST, row[3]);
  shiftOut(DataPin, ClockPin, LSBFIRST, col[3]);
  digitalWrite(LatchPin, HIGH);
  dotX = 3;
  dotY = 3;
  score = 0;
}

void food() {  // 먹이 생성
  int rnd;
  while (1) {
    randomSeed(analogRead(13));
    fx = random(8);
    fy = random(8);
    if (fx != dotX && fy != dotY){ 
      foodnum = exist;
      break;
    }
  }
}

void printDot() {  // 점 출력
  int delaynum = 0;
  while(delaynum < Speed){
    digitalWrite(LatchPin, LOW);
    shiftOut(DataPin, ClockPin, LSBFIRST, row[dotX]);
    shiftOut(DataPin, ClockPin, LSBFIRST, col[dotY]);
    digitalWrite(LatchPin, HIGH);
    delay(1);
    if(foodnum == exist && (delaynum / 150) % 2 == 0){  //먹이는 깜빡거리도록 설정
      digitalWrite(LatchPin, LOW);
      shiftOut(DataPin, ClockPin, LSBFIRST, row[fx]);
      shiftOut(DataPin, ClockPin, LSBFIRST, col[fy]);
      digitalWrite(LatchPin, HIGH);
    }
    delaynum++;
  }
}


void printTime(){ // 현재 시간 출력
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
