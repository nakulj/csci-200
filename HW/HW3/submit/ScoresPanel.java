
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.PriorityQueue;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ScoresPanel extends JPanel{
    
    PriorityQueue<Score> queue= new PriorityQueue<Score>();
    JLabel out= new JLabel("<HTML>High Scores:");
    int callerstate= 0;
    String outtext;
    
    public ScoresPanel() {
        try{
            if(new File("scores.save").exists()) {
                ObjectInputStream get=
                        new ObjectInputStream(new FileInputStream("scores.save"));
                queue= (PriorityQueue<Score>) get.readObject();
                refresh();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        add(out);
    }
    
    public void addScore(int score, String name) {
        queue.add(new Score(score, name));
        refresh();
        try {
            ObjectOutput out=
                    new ObjectOutputStream(new FileOutputStream("scores.save"));
            out.writeObject(queue);
            out.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void refresh() {
        PriorityQueue<Score> copy= new PriorityQueue(queue);
        outtext="<HTML>High Scores<BR><BR><table>";
        for(int i=1; i<=10; i++) {
            Score top= copy.poll();
            if(top == null)
                break;
            outtext+=("<tr><td>"+i+".</td><td>"+top.name+"</td><td>"+top.score+"</td></tr>");
        }
        outtext+=("</table");
        out.setText(outtext);
    }
    
    class Score implements Comparable<Score>, Serializable{
        int score;
        String name;
        
        public Score(int score, String name) {
            this.score= score;
            this.name= name;
        }
        
        public int compareTo(Score s) {
            return s.score-score;
        }
        
    }
    
}