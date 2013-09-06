
import javax.swing.JLabel;
import javax.swing.JPanel;


class MenuPanel extends JPanel{
    
    private JLabel menu= new JLabel(
            "<html>" +
            "Welcome to Speed<BR>" +
            "Please select an item from the menu above"
            );
    
    public MenuPanel() {
        add(menu);
    }
    
}
