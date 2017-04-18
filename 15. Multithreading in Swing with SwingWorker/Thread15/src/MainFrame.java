import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class MainFrame extends JFrame{
	private JLabel countLabel1 = new JLabel("0");
	
	private JLabel statusLabel = new JLabel("Task not completed! ");
	
	private JButton startButton = new JButton("Start");
	
	public MainFrame(String title){ // constructor
		super(title);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		add(countLabel1, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		add(statusLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		add(startButton, gc);
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				start();
			}
		});
		
		setSize(200, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	private void start(){
//		System.out.println("start");
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>(){

			@Override
			protected Boolean doInBackground() throws Exception {
				for(int i=0; i<30; i++){
					Thread.sleep(100);
					System.out.println("Hello: "+i);
					
					publish(i);
					
				}

				return false;
			}

			@Override
			protected void process(List<Integer> chunks) {
				int value = chunks.get(chunks.size()-1);
				countLabel1.setText("Current value: " + value);
			}

			@Override
			protected void done() {
//				System.out.println("Done.");
//				statusLabel.setText("Completed! ");
				
				try {
					Boolean status = get();
					statusLabel.setText("Completed with status "+ status);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		worker.execute();
	}
}
