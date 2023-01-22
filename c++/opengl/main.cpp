#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <GL\glut.h>
#include <gl\glut.h>
#include <gl\GL.h>
#include <gl\GLU.h>
#include <math.h>

#define ESCAPE 27

GLint window;
GLint window2;
GLint Xsize = 1000;
GLint Ysize = 800;
float i, theta;
GLint nml = 0, day = 1;

char name3[] = "����������������������N";

GLfloat xt = 0.0, yt = 0.0, zt = 0.0, xw = 0.0;
GLfloat xs = 1.0, ys = 1.0, zs = 1.0;
GLfloat xangle = 0.0, yangle = 0.0, zangle = 0.0, angle = 0.0;
GLfloat front = 0.0, side = 0.0;

GLfloat r = 0, g = 0, b = 1;       // �� ��
GLint light = 1;
int count = 1, flg = 1;
int view = 0;
int background = 0;         // ���ȭ��(�ٴ�)
int night = 1;         
int drive = 0;
GLUquadricObj* t;

void SetZero() {
    xt = 0;
    yt = 0;
    zt = 0;
    xw = 0;
    xs = 1.0;
    ys = 1.0;
    zs = 1.0;
    xangle = 0.0;
    yangle = 0.0;
    zangle = 0.0;
    zangle = 0.0;
    front = 0.0;
    side = 0.0;
}

GLvoid Transform(GLfloat Width, GLfloat Height)
{
    glViewport(0, 0, Width, Height);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(45.0, Width / Height, 0.1, 100.0);
    glMatrixMode(GL_MODELVIEW);
}
GLvoid InitGL(GLfloat Width, GLfloat Height)
{
    glClearColor(1.0, 1.0, 1.0, 1.0);
    glLineWidth(2.0);              /* Add line width,   ditto */
    Transform(Width, Height); /* Perform the transformation */
    t = gluNewQuadric();
    gluQuadricDrawStyle(t, GLU_FILL);

    glEnable(GL_LIGHTING);

    glEnable(GL_LIGHT0);

    GLfloat ambientLight[] = { 0.2f, 0.2f, 0.2f, 1.0f };
    GLfloat diffuseLight[] = { 0.8f, 0.8f, 0.8, 1.0f };
    GLfloat specularLight[] = { 0.5f, 0.5f, 0.5f, 1.0f };
    GLfloat position[] = { 1.5f, 1.0f, 4.0f, 1.0f };

    glLightfv(GL_LIGHT0, GL_AMBIENT, ambientLight);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuseLight);
    glLightfv(GL_LIGHT0, GL_SPECULAR, specularLight);
    glLightfv(GL_LIGHT0, GL_POSITION, position);

}
void init()
{
    glClearColor(0, 0, 0, 0);
    glPointSize(5.0);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(0.0, 900.0, 0.0, 600.0, 50.0, -50.0);
    glutPostRedisplay();
}
void display_string(int x, int y, char* string, int font)
{
    int len, i;
    glColor3f(0.8, 0.52, 1.0);
    glRasterPos2f(x, y);
    len = (int)strlen(string);
    for (i = 0; i < len; i++) {
        if (font == 1)
            glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, string[i]);
        if (font == 2)
            glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, string[i]);
        if (font == 3)
            glutBitmapCharacter(GLUT_BITMAP_HELVETICA_12, string[i]);
        if (font == 4)
            glutBitmapCharacter(GLUT_BITMAP_HELVETICA_10, string[i]);
    }

}

void display1(void)
{
    glClearColor(1.0, 1.0, 1.0, 1.0);
    glClear(GL_COLOR_BUFFER_BIT);
    display_string(190, 540, "Computer Graphics Final Assignment", 1);
    display_string(500, 470, "20161324 Shin Seung Min", 2);
    display_string(10, 450, "driving mode", 2);
    display_string(10, 410, "W - A - S - D", 3);
    display_string(10, 370, "look around", 2);
    display_string(10, 340, "W - A - S - D - Q - E : Rotate", 3);
    display_string(10, 280 + 30, "NumPad 4,5,6,8 : move ", 3);
    display_string(10, 250 + 30, "NumPad 7,9 : Up, Down", 3);
    display_string(10, 220 + 30, "Escape To Exit", 3);
    display_string(250, 150 + 30, "Press Space Bar To Enter", 2);
    glutPostRedisplay();
    glutSwapBuffers();

}

//īƮ �ٵ�
GLvoid DrawCartBody() {
    glBegin(GL_QUADS);

    // īƮ �ٵ�//////////////////////////
    // �ٴ� �Ʒ�
    glColor3f(r, g, b);
    glVertex3f(1.0, 0.0, 1.0);
    glVertex3f(1.5, 0.0, 1.0);
    glVertex3f(1.5, 0.0, 0.2);
    glVertex3f(1.0, 0.0, 0.2);
    //�ٴ� ��
    glVertex3f(1.0, 0.07, 1.0);
    glVertex3f(1.5, 0.07, 1.0);
    glVertex3f(1.5, 0.07, 0.4);
    glVertex3f(1.0, 0.07, 0.4);
    //�ٴ� ���� ������ ���
    glVertex3f(1.0, 0.07, 0.4);
    glVertex3f(1.5, 0.07, 0.4);
    glVertex3f(1.5, 0.14, 0.35);
    glVertex3f(1.0, 0.14, 0.35);
    //�ٴ� �� ��
    glVertex3f(1.0, 0.14, 0.35);
    glVertex3f(1.5, 0.14, 0.35);
    glVertex3f(1.5, 0.14, 0.2);
    glVertex3f(1.0, 0.14, 0.2);
    //���� ���� �Ʒ�
    glVertex3f(1.0, 0.035, 1.035);
    glVertex3f(1.5, 0.035, 1.035);
    glVertex3f(1.5, 0.0, 1.0);
    glVertex3f(1.0, 0.0, 1.0);
    //���� ���� ��
    glVertex3f(1.0, 0.035, 1.035);
    glVertex3f(1.5, 0.035, 1.035);
    glVertex3f(1.5, 0.07, 1.0);
    glVertex3f(1.0, 0.07, 1.0);
    //��
    glVertex3f(1.0, 0.0, 0.2);
    glVertex3f(1.5, 0.0, 0.2);
    glVertex3f(1.5, 0.14, 0.2);
    glVertex3f(1.0, 0.14, 0.2);
    //�� ���� ����
    glVertex3f(1.0, 0.0, 1.0);
    glVertex3f(1.0, 0.035, 1.035);
    glVertex3f(1.0, 0.07, 1.0);
    glVertex3f(1.0, 0.085, 1.0);
    //�� ���� �� �簢��
    glVertex3f(1.0, 0.0, 1.0);
    glVertex3f(1.0, 0.07, 1.0);
    glVertex3f(1.0, 0.07, 0.2);
    glVertex3f(1.0, 0.0, 0.2);
    //�� ���� ��ٸ���
    glVertex3f(1.0, 0.07, 0.4);
    glVertex3f(1.0, 0.14, 0.35);
    glVertex3f(1.0, 0.14, 0.2);
    glVertex3f(1.0, 0.07, 0.2);
    //�� ������ ����
    glVertex3f(1.5, 0.0, 1.0);
    glVertex3f(1.5, 0.035, 1.035);
    glVertex3f(1.5, 0.07, 1.0);
    glVertex3f(1.5, 0.085, 1.0);
    //�� ������ �� �簢��
    glVertex3f(1.5, 0.0, 1.0);
    glVertex3f(1.5, 0.07, 1.0);
    glVertex3f(1.5, 0.07, 0.2);
    glVertex3f(1.5, 0.0, 0.2);
    //�� ������ ��ٸ���
    glVertex3f(1.5, 0.07, 0.4);
    glVertex3f(1.5, 0.14, 0.35);
    glVertex3f(1.5, 0.14, 0.2);
    glVertex3f(1.5, 0.07, 0.2);
    // īƮ �ٵ� ////////////////////////////////////
    glEnd();
}  
//īƮ ����
GLvoid DrawCartChair() {
    glBegin(GL_QUADS);
    // ���� ������ ��
    glColor3f(0.4, 0.4, 0.4);
    glVertex3f(1.05, 0.07, 0.75);
    glVertex3f(1.45, 0.07, 0.75);
    glVertex3f(1.45, 0.1, 0.75);
    glVertex3f(1.05, 0.1, 0.75);
    //���� ������ ��
    glVertex3f(1.05, 0.1, 0.75);
    glVertex3f(1.45, 0.1, 0.75);
    glVertex3f(1.45, 0.1, 0.45);
    glVertex3f(1.05, 0.1, 0.45);
    //���� ������ ����
    glVertex3f(1.05, 0.07, 0.75);
    glVertex3f(1.05, 0.1, 0.75);
    glVertex3f(1.05, 0.1, 0.42);
    glVertex3f(1.05, 0.07, 0.42);
    //���� ������ ������
    glVertex3f(1.45, 0.07, 0.75);
    glVertex3f(1.45, 0.1, 0.75);
    glVertex3f(1.45, 0.1, 0.42);
    glVertex3f(1.45, 0.07, 0.42);

    //���� ����� ��
    glVertex3f(1.05, 0.1, 0.45);
    glVertex3f(1.45, 0.1, 0.45);
    glVertex3f(1.45, 0.4, 0.38);
    glVertex3f(1.05, 0.4, 0.38);
    //���� ����� ��
    glVertex3f(1.05, 0.4, 0.38);
    glVertex3f(1.45, 0.4, 0.38);
    glVertex3f(1.45, 0.39, 0.35);
    glVertex3f(1.05, 0.39, 0.35);
    //���� ����� ��
    glVertex3f(1.05, 0.07, 0.42);
    glVertex3f(1.45, 0.07, 0.42);
    glVertex3f(1.45, 0.39, 0.35);
    glVertex3f(1.05, 0.39, 0.35);
    //���� ����� ����
    glVertex3f(1.05, 0.07, 0.42);
    glVertex3f(1.05, 0.1, 0.45);
    glVertex3f(1.05, 0.4, 0.38);
    glVertex3f(1.05, 0.39, 0.35);
    //���� ����� ������
    glVertex3f(1.45, 0.07, 0.42);
    glVertex3f(1.45, 0.1, 0.45);
    glVertex3f(1.45, 0.4, 0.38);
    glVertex3f(1.45, 0.39, 0.35);
    glEnd();
}
//īƮ ������
GLvoid DrawCartHandle() {
    glBegin(GL_QUADS);
    glColor3f(0.6, 0.6, 0.6);
    //�ﰢ�� ��
    glVertex3f(1.1, 0.07, 0.95);
    glVertex3f(1.4, 0.07, 0.95);
    glVertex3f(1.4, 0.14, 0.88);
    glVertex3f(1.1, 0.14, 0.88);
    //�ﰢ�� ��
    glVertex3f(1.1, 0.14, 0.88);
    glVertex3f(1.4, 0.14, 0.88);
    glVertex3f(1.4, 0.07, 0.81);
    glVertex3f(1.1, 0.07, 0.81);
    //�ﰢ�� ����
    glVertex3f(1.1, 0.07, 0.95);
    glVertex3f(1.1, 0.14, 0.88);
    glVertex3f(1.1, 0.07, 0.81);
    glVertex3f(1.1, 0.07, 0.88);
    //�ﰢ�� ������
    glVertex3f(1.4, 0.07, 0.95);
    glVertex3f(1.4, 0.14, 0.88);
    glVertex3f(1.4, 0.07, 0.81);
    glVertex3f(1.4, 0.07, 0.88);

    //��� ������ü ��
    glColor3f(0.2, 0.2, 0.2);
    glVertex3f(1.2, 0.07, 0.97);
    glVertex3f(1.3, 0.07, 0.97);
    glVertex3f(1.3, 0.25, 0.79);
    glVertex3f(1.2, 0.25, 0.79);
    //��� ������ü ��
    glVertex3f(1.2, 0.07, 0.83);
    glVertex3f(1.3, 0.07, 0.83);
    glVertex3f(1.3, 0.19, 0.72);
    glVertex3f(1.2, 0.19, 0.72);
    //��� ������ü ��
    glVertex3f(1.2, 0.25, 0.79);
    glVertex3f(1.3, 0.25, 0.79);
    glVertex3f(1.3, 0.19, 0.72);
    glVertex3f(1.2, 0.19, 0.72);
    //��� ������ü ����
    glVertex3f(1.2, 0.07, 0.97);
    glVertex3f(1.2, 0.25, 0.79);
    glVertex3f(1.2, 0.19, 0.72);
    glVertex3f(1.2, 0.07, 0.83);
    //��� ������ü ������
    glVertex3f(1.3, 0.07, 0.97);
    glVertex3f(1.3, 0.25, 0.79);
    glVertex3f(1.3, 0.19, 0.72);
    glVertex3f(1.3, 0.07, 0.83);

    //��� ������ 
    glColor3f(0, 0, 0);
    for (int i = 0; i < 3; i++) {
        glVertex3f(1.2251, 0.071 + (0.03 * (2 * i)), 0.971 - (0.03 * (2 * i)));
        glVertex3f(1.2751, 0.071 + (0.03 * (2 * i)), 0.971 - (0.03 * (2 * i)));
        glVertex3f(1.2751, 0.101 + (0.03 * (2 * i)), 0.941 - (0.03 * (2 * i)));
        glVertex3f(1.2251, 0.101 + (0.03 * (2 * i)), 0.941 - (0.03 * (2 * i)));
    }
    glColor3f(1, 1, 0);
    for (int i = 0; i < 2; i++) {
        glVertex3f(1.2251, 0.071 + (0.03 * (2 * i + 1)), 0.971 - (0.03 * (2 * i + 1)));
        glVertex3f(1.2751, 0.071 + (0.03 * (2 * i + 1)), 0.971 - (0.03 * (2 * i + 1)));
        glVertex3f(1.2751, 0.101 + (0.03 * (2 * i + 1)), 0.941 - (0.03 * (2 * i + 1)));
        glVertex3f(1.2251, 0.101 + (0.03 * (2 * i + 1)), 0.941 - (0.03 * (2 * i + 1)));
    }

    //�ڵ� ��� ��
    glColor3f(0.2, 0.2, 0.2);
    glVertex3f(1.235, 0.235, 0.705);
    glVertex3f(1.265, 0.235, 0.705);
    glVertex3f(1.265, 0.265, 0.735);
    glVertex3f(1.235, 0.265, 0.735);
    //�ڵ� ��� ��
    glVertex3f(1.235, 0.265, 0.735);
    glVertex3f(1.265, 0.265, 0.735);
    glVertex3f(1.265, 0.205, 0.795);
    glVertex3f(1.235, 0.205, 0.795);
    //�ڵ� ��� ��
    glVertex3f(1.235, 0.235, 0.705);
    glVertex3f(1.265, 0.235, 0.705);
    glVertex3f(1.265, 0.175, 0.765);
    glVertex3f(1.235, 0.175, 0.765);
    //�ڵ� ��� �ަU
    glVertex3f(1.235, 0.265, 0.735);
    glVertex3f(1.235, 0.205, 0.795);
    glVertex3f(1.235, 0.175, 0.765);
    glVertex3f(1.235, 0.235, 0.705);
    //�ڵ� ��� ������
    glVertex3f(1.265, 0.265, 0.735);
    glVertex3f(1.265, 0.205, 0.795);
    glVertex3f(1.265, 0.175, 0.765);
    glVertex3f(1.265, 0.235, 0.705);

    //�ڵ� ���� ��
    glColor3f(0, 0, 0);
    glVertex3f(1.18, 0.24, 0.71);
    glVertex3f(1.32, 0.24, 0.71);
    glVertex3f(1.32, 0.26, 0.73);
    glVertex3f(1.18, 0.26, 0.73);
    glEnd();

    glPushMatrix();
    glTranslatef(1.25, 0.25, 0.72);
    glRotatef(45, 1, 0, 0);
    glColor3f(0, 0, 1);
    glutSolidTorus(0.01, 0.07, 10, 30);
    glPopMatrix();

    
}
//īƮ ����
GLvoid DrawCartEtc() {
    //����
    glBegin(GL_QUADS);
    glColor3f(0, 0, 0);
    // �չ��� ������
    glVertex3f(1.6, 0.028, 1);
    glVertex3f(1.6, 0.028, 0.86);
    glVertex3f(1.6, 0.042, 0.86);
    glVertex3f(1.6, 0.042, 1);

    glVertex3f(1.5, 0.028, 0.86);
    glVertex3f(1.5, 0.028, 1);
    glVertex3f(1.6, 0.028, 1);
    glVertex3f(1.6, 0.028, 0.86);

    glVertex3f(1.5, 0.042, 0.86);
    glVertex3f(1.5, 0.042, 1);
    glVertex3f(1.6, 0.042, 1);
    glVertex3f(1.6, 0.042, 0.86);

    glVertex3f(1.5, 0.042, 1);
    glVertex3f(1.5, 0.028, 1);
    glVertex3f(1.6, 0.028, 1);
    glVertex3f(1.6, 0.042, 1);

    glVertex3f(1.5, 0.042, 0.86);
    glVertex3f(1.5, 0.028, 0.86);
    glVertex3f(1.6, 0.028, 0.86);
    glVertex3f(1.6, 0.042, 0.86);
    //�չ��� ����
    glVertex3f(0.9, 0.028, 1);
    glVertex3f(0.9, 0.028, 0.86);
    glVertex3f(0.9, 0.042, 0.86);
    glVertex3f(0.9, 0.042, 1);

    glVertex3f(1, 0.028, 0.86);
    glVertex3f(1, 0.028, 1);
    glVertex3f(0.9, 0.028, 1);
    glVertex3f(0.9, 0.028, 0.86);

    glVertex3f(1, 0.042, 0.86);
    glVertex3f(1, 0.042, 1);
    glVertex3f(0.9, 0.042, 1);
    glVertex3f(0.9, 0.042, 0.86);

    glVertex3f(1, 0.042, 1);
    glVertex3f(1, 0.028, 1);
    glVertex3f(0.9, 0.028, 1);
    glVertex3f(0.9, 0.042, 1);

    glVertex3f(1, 0.042, 0.86);
    glVertex3f(1, 0.028, 0.86);
    glVertex3f(0.9, 0.028, 0.86);
    glVertex3f(0.9, 0.042, 0.86);

    //�޹��� ��

    glVertex3f(1.65, 0.028, 0.65);
    glVertex3f(1.65, 0.028, 0.47);
    glVertex3f(1.65, 0.042, 0.47);
    glVertex3f(1.65, 0.042, 0.65);

    glVertex3f(1.5, 0.028, 0.47);
    glVertex3f(1.5, 0.028, 0.65);
    glVertex3f(1.65, 0.028, 0.65);
    glVertex3f(1.65, 0.028, 0.47);

    glVertex3f(1.5, 0.042, 0.47);
    glVertex3f(1.5, 0.042, 0.65);
    glVertex3f(1.65, 0.042, 0.65);
    glVertex3f(1.65, 0.042, 0.47);

    glVertex3f(1.5, 0.042, 0.65);
    glVertex3f(1.5, 0.028, 0.65);
    glVertex3f(1.65, 0.028, 0.65);
    glVertex3f(1.65, 0.042, 0.65);

    glVertex3f(1.5, 0.042, 0.47);
    glVertex3f(1.5, 0.028, 0.47);
    glVertex3f(1.65, 0.028, 0.47);
    glVertex3f(1.65, 0.042, 0.47);

    //�޹��� ��
    glVertex3f(0.85, 0.028, 0.65);
    glVertex3f(0.85, 0.028, 0.47);
    glVertex3f(0.85, 0.042, 0.47);
    glVertex3f(0.85, 0.042, 0.65);

    glVertex3f(1, 0.028, 0.47);
    glVertex3f(1, 0.028, 0.65);
    glVertex3f(0.85, 0.028, 0.65);
    glVertex3f(0.85, 0.028, 0.47);

    glVertex3f(1, 0.042, 0.47);
    glVertex3f(1, 0.042, 0.65);
    glVertex3f(0.85, 0.042, 0.65);
    glVertex3f(0.85, 0.042, 0.47);

    glVertex3f(1, 0.042, 0.65);
    glVertex3f(1, 0.028, 0.65);
    glVertex3f(0.85, 0.028, 0.65);
    glVertex3f(0.85, 0.042, 0.65);

    glVertex3f(1, 0.042, 0.47);
    glVertex3f(1, 0.028, 0.47);
    glVertex3f(0.85, 0.028, 0.47);
    glVertex3f(0.85, 0.042, 0.47);

    glEnd();
}
//īƮ ����
GLvoid DrawCartWheel() {
    //�չ��� ������
    glPushMatrix();
    glTranslatef(0.975, 0, 0.75);
    glRotatef(90, 0, 1, 0);
    glColor3f(0, 0, 0);
    glutSolidTorus(0.04, 0.04, 10, 30);
    glPopMatrix();

    //�չ��� ����
    glPushMatrix();
    glTranslatef(1.525, 0, 0.75);
    glRotatef(90, 0, 1, 0);
    glColor3f(0, 0, 0);
    glutSolidTorus(0.04, 0.04, 10, 30);
    glPopMatrix();

    //�޹��� ������
    glPushMatrix();
    glTranslatef(0.95, 0.02, 0.325);
    glRotatef(90, 0, 1, 0);
    glColor3f(0, 0, 0);
    glutSolidTorus(0.05, 0.05, 10, 30);
    glPopMatrix();

    // �޹��� ����
    glPushMatrix();
    glTranslatef(1.55, 0.02, 0.325);
    glRotatef(90, 0, 1, 0);
    glColor3f(0, 0, 0);
    glutSolidTorus(0.05, 0.05, 10, 30);
    glPopMatrix();
}

//ȭ�� �׸���
GLvoid DrawGLScene()
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    if (view == 0)
    {
        init();
        display1();
    }
    else
    {
        if (count == 1)
            InitGL(Xsize, Ysize);
        if (night == 1)
            glClearColor(1, 1, 1, 1);
        else
            glClearColor(0.1, 0.1, 0.1, 0);

        glPushMatrix();
        
        glLoadIdentity();
        glTranslatef(-1.0, 0.0, -3.5);
        glRotatef(xangle, 1.0, 0.0, 0.0);
        glRotatef(yangle, 0.0, 1.0, 0.0);
        glRotatef(zangle, 0.0, 0.0, 1.0);
        glTranslatef(xt, yt, zt);
        glScalef(xs, ys, zs);
        glEnable(GL_COLOR_MATERIAL);
        
        
        //īƮ �׸���
        glPushMatrix();
        if (!drive) {
            DrawCartBody();
            DrawCartChair();
            DrawCartHandle();
            DrawCartEtc();
            DrawCartWheel();
        }
        else {
            glRotatef(180, 0, 1, 0);
            DrawCartBody();
            DrawCartChair();
            DrawCartHandle();
            DrawCartEtc();
            DrawCartWheel();
        }
        glPopMatrix();
        
        if (drive) {
            glTranslatef(side, 0, front);
            
        }
        glBegin(GL_QUADS);
        //*****************************  ���  ***********************************
        if (background)
        {
            glPushMatrix();
            for (int i = -500; i < 500; i += 10) { // ����
                for (int j = -500; j < 500; j += 10) { // ����
                    glColor3f(1, 1, 1);
                    glVertex3f(j, -0.1, i);
                    glVertex3f(j, -0.1, i + 5);
                    glVertex3f(j + 5, -0.1, i + 5);
                    glVertex3f(j + 5, -0.1, i);

                    glColor3f(0.4, 0.8, 0.4);
                    glVertex3f(j+5, -0.1, i);
                    glVertex3f(j+5, -0.1, i + 5);
                    glVertex3f(j + 10, -0.1, i + 5);
                    glVertex3f(j + 10, -0.1, i);

                    glColor3f(0.4, 0.8, 0.4);
                    glVertex3f(j, -0.1, i + 5);
                    glVertex3f(j, -0.1, i + 10);
                    glVertex3f(j + 5, -0.1, i + 10);
                    glVertex3f(j + 5, -0.1, i + 5);

                    glColor3f(1, 1, 1);
                    glVertex3f(j+5, -0.1, i +5);
                    glVertex3f(j+5, -0.1, i + 10);
                    glVertex3f(j + 10, -0.1, i + 10);
                    glVertex3f(j + 10, -0.1, i+5);
                }
                
            }
            glPopMatrix();
        }
        glEnd();
        glPopMatrix();
        glEnable(GL_DEPTH_TEST);
        glutPostRedisplay();
        
        glutSwapBuffers();
    }
}

//ȭ�� �����̱�
void NormalKey(GLubyte key, GLint x, GLint y)
{
    if (!drive) {
        switch (key) {
        case ESCAPE: printf("escape pressed. exit.\n");
            glutDestroyWindow(window);
            exit(0);
            break;

        case ' ':view = 1;
            DrawGLScene();
            break;
        case 's': 
            xangle += 1.0;
            glutPostRedisplay();
            break;

        case 'w':
            xangle -= 1.0;
            glutPostRedisplay();
            break;

        case 'a':
            yangle += 1.0;
            glutPostRedisplay();
            break;

        case 'd':
            yangle -= 1.0;
            glutPostRedisplay();
            break;

        case 'q':
            zangle += 1.0;
            glutPostRedisplay();
            break;

        case 'e':
            zangle -= 1.0;
            glutPostRedisplay();
            break;

        case '9':
            yt += 0.1;
            glutPostRedisplay();
            break;

        case '7':
            yt -= 0.1;
            glutPostRedisplay();
            break;

        case '8':
            zt += 0.1;
            glutPostRedisplay();
            break;

        case '5':
            zt -= 0.1;
            glutPostRedisplay();
            break;

        case '4':
            xt += 0.1;
            glutPostRedisplay();
            break;

        case '6':
            xt -= 0.1;
            glutPostRedisplay();
            break;

        default:
            break;
        }
    }
    else {
        switch (key) {
        case 's': 
            front -= 1.0;
            glutPostRedisplay();
            break;

        case 'w':
            front += 1.0;
            glutPostRedisplay();
            break;

        case 'a':
            side += 1.0;
            glutPostRedisplay();
            break;

        case 'd':
            side -= 1.0;
            glutPostRedisplay();
            break;
        }
    }
}
// �޴�
void myMenu(int id)
{
    if (id == 1)
    {
        SetZero();
        background = 0;
        drive = 0;
        glPushMatrix();
        glutPostRedisplay();
        glPopMatrix();
    }
    if (id == 2)
    {
        SetZero();
        background = 1;
        drive = 1;
        yt -= 1;
        xt += 2.1;
        xangle += 20;
        glutPostRedisplay();
    }

    if (id == 12)
    {
        night = 1;
        day = 1;
        glClearColor(1, 1, 1, 1);
        glDisable(GL_FOG);
        glutPostRedisplay();
    }

    if (id == 13)
    {
        night = 0;
        day = 0;

        glClearColor(0.1, 0.1, 0.1, 0);
        GLfloat fogcolour[4] = { 0.0,0.0,0.0,1.0 };

        glFogfv(GL_FOG_COLOR, fogcolour);
        glFogf(GL_FOG_DENSITY, 0.5);
        glFogi(GL_FOG_MODE, GL_EXP);
        glHint(GL_FOG_HINT, GL_FASTEST);
        glEnable(GL_FOG);

        glutPostRedisplay();
    }
}
// �� ���� �ٲٱ� �޴�
void colorMenu(int id)
{
    if (id == 6)
    {
        r = g = 0;
        b = 1;
        glutPostRedisplay();

    }
    if (id == 7)
    {
        r = 0.8;
        b = g = 0;
        glutPostRedisplay();
    }
    if (id == 8)
    {
        g = 1;
        r = b = 0;
        glutPostRedisplay();
    }
    if (id == 9)
    {
        r = b = g = 0;
        glutPostRedisplay();
    }
    if (id == 10)
    {
        b = 0;
        r = g = 1;
        glutPostRedisplay();
    }
    if (id == 11)
    {
        b = r = g = .7;
        glutPostRedisplay();
    }

}

int main(int argc, char** argv)
{


    glutInit(&argc, argv);

    glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);
    glutInitWindowSize(Xsize, Ysize);
    glutInitWindowPosition(50, 50);
    glutCreateWindow("3D CAR ANIMATION");
    glutDisplayFunc(DrawGLScene);
    glutKeyboardFunc(NormalKey);
    InitGL(Xsize, Ysize);
    int submenu = glutCreateMenu(colorMenu);
    glutAddMenuEntry("�Ķ���", 6);
    glutAddMenuEntry("������", 7);
    glutAddMenuEntry("�ʷϻ�", 8);
    glutAddMenuEntry("������", 9);
    glutAddMenuEntry("�����", 10);
    glutAddMenuEntry("ȸ��", 11);
    glutCreateMenu(myMenu);
    glutAddMenuEntry("�ѷ�����", 1);
    glutAddMenuEntry("�����ϱ�", 2);
    glutAddSubMenu("�� ����", submenu);
    glutAddMenuEntry("��", 12);
    glutAddMenuEntry("��", 13);
    glutAttachMenu(GLUT_RIGHT_BUTTON);


    glutMainLoop();
    return 1;
}