package kata6.app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import kata6.control.Command;
import kata6.control.DownCommand;
import kata6.control.LeftCommand;
import kata6.control.RightCommand;
import kata6.control.UpCommand;
import kata6.model.Block;
import kata6.view.BlockDisplay;

public class Main extends JFrame{
    
    private Block block;
    private BlockDisplay blockDisplay;
    private Map<String,Command> commands;
    
    public static void main(String[] args){
        new Main().execute();
        
    }
    
    public Main(){
        this.block=new Block();
        this.setTitle("Block shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 722);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        this.commands = createCommand();
    }
    
    public void execute(){
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel panel = new BlockPanel();
        this.blockDisplay = panel;
        panel.display(block);
        this.block.register(panel);
        return panel;
    }

    private JMenuBar toolbar() {
       JMenuBar jMenuBar = new JMenuBar();
       jMenuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
       jMenuBar.add(button("D"));
       jMenuBar.add(button("L"));
       jMenuBar.add(button("R"));
       jMenuBar.add(button("U"));
       return jMenuBar;
    }

    private Map<String, Command> createCommand() {
        Map <String,Command> commands = new HashMap<>();
        commands.put("U", new UpCommand(block));
        commands.put("D", new DownCommand(block));
        commands.put("L", new LeftCommand(block));
        commands.put("R", new RightCommand(block));
        return commands;
        
    }

    private JButton button(String command) {
        JButton button = new JButton(command);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                commands.get(command).execute();
            }
        });
        return button;
    }

    
            
            
    
    
    
}
