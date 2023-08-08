package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

//CITATION; PART OF CODE INSPIRED FROM THE FOLLOWING:
// https://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java
// https://stackoverflow.com/questions/29708147/custom-graph-java-swing
// https://stackoverflow.com/questions/47366122/counter-and-accumulator-in-a-java-loop

// Displays interest return over time graph for the given portfolio
public class DrawGraph extends JPanel {
    private double maxScore;
    protected static final int initialWidth = 500;
    protected static final int initialHeight = 300;
    protected static final int borderGap = 80;
    protected static final Color graphColour = Color.red;
    protected static final Color pointColour = Color.red;
    protected static final Stroke graphStroke = new BasicStroke(2f);
    protected static final int pointWidth = 3;
    protected static final int yHatch = 2;
    protected final java.util.List<Double> initialCapital;
    protected static Double returnPercentage;
    protected String yaxislabel;
    protected String xaxisLabel;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    //CONSTRUCTOR
    //EFFECTS: constructs a new DrawGraph using the
    public DrawGraph(java.util.List<Double> initialCapital, Double returnPercentage) {
        this.initialCapital = initialCapital;
        this.returnPercentage = returnPercentage;
        this.maxScore = (calculateCompoundInterest(initialCapital.get(1), returnPercentage, 48));
    }

    //GETTERS
    public List<Double> getScores(DrawGraph drawGraph) {
        return this.initialCapital;
    }

    public Double getAmount(DrawGraph drawGraph) {
        return this.returnPercentage;
    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    //EFFECTS: Creates the graph components that will be displayed
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int scaler = (int) calculateCompoundInterest(initialCapital.get(1), returnPercentage, 50);
        double xscale = ((double) getWidth() - 2 * borderGap) / (initialCapital.size() - 1);
        double yscale = ((double) getHeight() - 2 * borderGap) / (scaler);

        Double amt = getAmount(this);

        java.util.List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < initialCapital.size(); i++) {
            int x1 = (int) (i * xscale + borderGap);
            int y1 = (int) (getHeight() - (calculateCompoundInterest(initialCapital.get(1),
                    returnPercentage, i) * yscale + borderGap));
            graphPoints.add(new Point(x1, y1));

        }

        // create x and y axes
        g2.drawLine(borderGap, getHeight() - borderGap, borderGap, borderGap);
        g2.drawLine(borderGap, getHeight() - borderGap, getWidth() - borderGap, getHeight() - borderGap);

        // create hatch marks and labels for y axis.
        for (int i = 0; i <= yHatch; i++) {
            int x0 = borderGap;
            int x1 = pointWidth + borderGap;
            int y0 = getHeight() - (((i) * (getHeight() - borderGap * 2)) / yHatch + borderGap);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);

            yaxislabel = df.format(i * (maxScore / 2));
            g2.drawString(yaxislabel, x0 - 50, y0);
        }

        // and for x-axis
        for (int i = 0; i <= initialCapital.size() - 1; i++) {
            if (i % 5 == 0) { // to display only for years 0, 5, 10, 15, ...
                int x0 = (i) * (getWidth() - borderGap * 2) / (initialCapital.size() - 1) + borderGap;
                int x1 = x0;
                int y0 = getHeight() - borderGap;
                int y1 = y0 - pointWidth;
                g2.drawLine(x0, y0, x1, y1);

                String xaxisLabel = Integer.toString(i);
                g2.drawString(xaxisLabel, x0, y0 + 15);
            }
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(graphColour);
        g2.setStroke(graphStroke);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColour);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }


        g2.setColor(Color.BLACK);
        g2.drawString("Years", getWidth() / 2, getHeight() - borderGap / 2 + 20);


        g2.rotate(-Math.PI / 2);
        g2.drawString("Amount ($)", -getHeight() / 2, borderGap / 2 - 20);
        g2.rotate(Math.PI / 2);
    }


    //EFFECTS: Calculates the compound interest for the portfolio
    public static double calculateCompoundInterest(Double principal, double rate, int time) {
        double amount = principal * Math.pow(1 + rate, time);
        return amount;
    }



    @Override
    //EFFECTS: Gets the dimensions of the graph
    public Dimension getPreferredSize() {
        return new Dimension(initialWidth, initialHeight);
    }

    //EFFECTS: Generates the graph to display to the user
    protected void createAndShowGui() {
        List<Double> scores = new ArrayList<>();
        int maxDataPoints = 50;

        for (int i = 1; i <= maxDataPoints; i++) {
            double compoundInterest = calculateCompoundInterest(this.initialCapital.get(0), this.returnPercentage, i);
            scores.add(compoundInterest);
        }
        DrawGraph mainPanel = new DrawGraph(scores, this.returnPercentage);

        JFrame frame = new JFrame("Value of Portfolio ($)");
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

}
