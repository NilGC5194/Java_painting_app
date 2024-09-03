import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class DrawingControl extends JFrame implements ActionListener {

    private DrawingModel paintModel;
    private DrawingView paintView;

    public DrawingControl(){
        paintModel = new DrawingModel();
        paintView = new DrawingView(paintModel);


        paintView.black.addActionListener(this);
        paintView.red.addActionListener(this);
        paintView.green.addActionListener(this);
        paintView.blue.addActionListener(this);

        paintView.dot.addActionListener(this);
        paintView.oval.addActionListener(this);
        paintView.rect.addActionListener(this);

        paintView.save.addActionListener(this);
        paintView.undo.addActionListener(this);
        paintView.load.addActionListener(this);
        paintView.reset.addActionListener(this);


        paintView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String color = paintModel.getColor();
                String form = paintModel.getShape();
                int x = e.getX();
                int y = e.getY();
                Shape shape = new Shape(color, form, x, y);
                paintModel.addShape(shape);
                paintView.repaint();
            }
        });


        paintView.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                paintModel.mouseMoved(x,y);
                paintView.repaint();
            }
        });

        setVisible(true);
        add(paintView);
        pack();
        setLocationRelativeTo(null);
        //Will terminate the program when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Black":
                paintModel.setColor("black");
                System.out.println(paintModel.getColor());
                break;
            case "Red":
                paintModel.setColor("red");
                System.out.println(paintModel.getColor());
                break;
            case "Green":
                paintModel.setColor("green");
                System.out.println(paintModel.getColor());
                break;
            case "Blue":
                paintModel.setColor("blue");
                System.out.println(paintModel.getColor());
                break;
            case "Dot":
                paintModel.setShape("dot");
                System.out.println(paintModel.getShape());
                break;
            case "Oval":
                paintModel.setShape("oval");
                System.out.println(paintModel.getShape());
                break;
            case "Rectangle":
                paintModel.setShape("rectangle");
                System.out.println(paintModel.getShape());
                break;
            case "Save":
                try {
                    paintModel.save();
                    System.out.println("Save was successful");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Undo":
                paintModel.undo();
                paintView.repaint();
                System.out.println("Undid successfully");
                break;
            case "Load":
                try {
                    paintModel.load();
                    paintView.repaint();
                    System.out.println("Load was successful");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Reset":
                paintModel.setShapes(new ArrayList<>());
                paintView.repaint();
        }
        paintView.setMode();
    }
    public static void main(String[] args) {
        new DrawingControl();
    }

}
