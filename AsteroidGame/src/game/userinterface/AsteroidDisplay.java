package game.userinterface;

import game.logic.Asteroid;

import java.awt.*;

public class AsteroidDisplay extends Display{

    public Asteroid getSubject() {
        return subject;
    }

    private Asteroid subject;
    private boolean selected;
    private boolean roundoutline;
    private boolean neighbour;

    public AsteroidDisplay(Asteroid subject, int x, int y) {
        this.subject = subject;
        subject.AddDisplay(this);
        getShape().setBounds(x, y, 100, 100);
    }

    @Override
    public void Update(Graphics2D g2d) {
        if (subject.GetSunnearness()) {
            g2d.setColor(Color.getHSBColor(21, 100, 40));
        } else {
            g2d.setColor(Color.getHSBColor(21, 100, 17));
        }

        g2d.fillOval(getShape().x, getShape().y, getShape().width, getShape().height);
        g2d.setColor();
        g2d.drawOval(getShape().x, getShape().y, getShape().width, getShape().height);
    }

    @Override
    public void SelectOutline(Graphics2D g2d) {
        g2d.setColor(new Color(200, 22, 217));
    }

    @Override
    public void RoundOutline(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0));
    }

    public void NeighbourOutLine(Graphics2D g2d) {
        g2d.setColor(new Color(0, 200, 255));
    }

    @Override
    public void Clear() {
        DisplayManager.GetInstance().RemoveAsteroidDisplay(this);
    }

}
