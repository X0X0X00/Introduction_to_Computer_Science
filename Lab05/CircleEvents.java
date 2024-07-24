/*
zhenhao zhang zzh133@u.rochester.edu 32277234
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//Circle
class Circle extends Component {

    // x  y 坐标
    public int x;
    public int y;
    public Color the_Color;

    //constructor with （x，y，color）
    public Circle(int x, int y, Color the_Color) {
        this.x = x;
        this.y = y;
        this.the_Color = the_Color;
        setFocusable(true);
    }

    //get the distance
    double distance(int x_new_point, int y_new_point) {
        return Math.sqrt(Math.pow(x_new_point - x, 2) + Math.pow(y_new_point - y, 2));
    }

    //create new x,y
    public void setNew(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


// Canvas
class EventLabCanvas extends JPanel{

    // radius of the circle
    static final int radius = 5;
    public static Graphics Graphics;
    public Color THE_Color = Color.BLACK;
    public ArrayList<Circle> circles = new ArrayList<>();
    public int selected = -1;

    // Create the canvas
    public EventLabCanvas(JFrame J){
        J.setSize(800,  1000);
        J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        J.add(this);
        J.setVisible(true);
        J.addKeyListener(new key());
        addMouseListener(new ML());
        addMouseMotionListener(new MML());
        Graphics = getGraphics();
    }

    //draw circles
    public void drawCircle(Circle circle, int radius, Color color){
        Color previous_color = Graphics.getColor();
        Graphics.setColor(color);
        Graphics.fillOval(circle.x-radius, circle.y-radius, radius*2, radius*2);
        Graphics.setColor(previous_color);
    }

    //draw lines
    public void drawLine(Circle circle1, Circle circle2, Color color){
        Color previous_color = Graphics.getColor();
        Graphics.setColor(color);
        Graphics.drawLine(circle1.x, circle1.y, circle2.x, circle2.y);
        Graphics.setColor(previous_color);
    }

    //鼠标单击
    class ML implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // get the location of x,y
            System.out.print("Click detected at" + "("+e.getX() +"," + e.getY()+")"+"\n");
            if(e.getButton() == MouseEvent.BUTTON1){
                // add a new circle to the list
                circles.add(new Circle(e.getX(), e.getY(), THE_Color));
                // repaint the canvas
                paintComponent(Graphics);
            }
        }
        @Override
        public void mousePressed(MouseEvent ME) {
            // 按下鼠标将某个画好的圆选中(在拖动之前)
            for(int i = 0; i < circles.size(); i++){
                // 判断distance, 如果小于radius, 则表明鼠标在圆内，选中该圆
                if(circles.get(i).distance(ME.getX(), ME.getY()) <= radius){
                    selected = i;
                    System.out.println("The Selected circle is: " + selected);
                    break;
                }
            }
        }
        @Override
        public void mouseReleased(MouseEvent ME) {
            // 释放鼠标, 重置selected为未选择
            selected = -1;
        }
        @Override
        public void mouseEntered(MouseEvent ME) {
        }
        @Override
        public void mouseExited(MouseEvent ME) {
        }
    }

    // 检测Mouse拖拽
    class MML implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent ME) {
            System.out.print("Drag detected at" +"("+ ME.getX()+","+ ME.getY()+")"+"\n");
            if(selected != -1){
                // 如果selected != -1, 代表有圆被选中
                // First 先利用背景色getBackground()重新画一遍需要擦掉的圆和线，当做移除
                drawCircle(circles.get(selected), radius, getBackground()); // 擦掉被选中的圆
                // 擦掉被选中的圆连接的两条直线，这里需要判断圆在序列里是不是第一个或者最后一个，因为第一个和最后一个只连了一条线
                if(selected != circles.size()-1){
                    drawLine(circles.get(selected), circles.get(selected+1), getBackground());
                }
                if(selected != 0){
                    drawLine(circles.get(selected-1), circles.get(selected), getBackground());
                }
                // 2. 更新被拖动的圆的位置
                circles.get(selected).setNew(ME.getX(), ME.getY());
                // 3. 重新画整个canvas
                paintComponent(Graphics);
            }
        }
        @Override
        public void mouseMoved(MouseEvent ME) {
        }
    }
    // 检测键盘
    class key implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            // 当键盘被按下时，根据按下的键改变画笔的颜色
            if(e.getKeyChar() == 'r'){
                THE_Color = Color.RED;
                System.out.println("Color changed to Red!");
            }
            else if(e.getKeyChar() == 'l'){
                THE_Color = Color.BLACK;
                System.out.println("Color changed to Black!");
            }
            else if(e.getKeyChar() == 'b'){
                THE_Color = Color.BLUE;
                System.out.println("Color changed to Blue!");
            }
            else if(e.getKeyChar() == 'g'){
                THE_Color = Color.GREEN;
                System.out.println("Color changed to Green!");
            }

        }
        @Override
        public void keyPressed(KeyEvent e) {
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    @Override
    // 画圆和线
    public void paintComponent(Graphics g){
        // paint all the components, including circles and lines
        if(circles.isEmpty()){
            // if there is no circle, do nothing
            return;
        }
        // 先画一个圆
        drawCircle(circles.get(0), radius, circles.get(0).the_Color);
        for(int i = 1; i < circles.size(); i++){
            // for the rest circles, draw the line and the circle
            drawCircle(circles.get(i), radius, circles.get(i).the_Color);
            drawLine(circles.get(i-1), circles.get(i), Color.BLACK);
        }
    }
}

// main class
public class CircleEvents {
    public static void main(String[] args){
        // 所以这里就在main method中创建了一个JFrame
        JFrame JF = new JFrame();
        EventLabCanvas J = new EventLabCanvas(JF);
    }
}
