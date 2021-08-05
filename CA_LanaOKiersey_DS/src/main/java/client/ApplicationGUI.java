package client;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import messaging.Message;
import messaging.MessageRequest;
import messaging.MessagingServiceGrpc;
import wearables.HeartRate;
import wearables.HeartRateServiceGrpc;
import wearables.Reply;

public class ApplicationGUI implements ActionListener{
	
	//Creating Emergency Heart Rate button
	private JPanel getService1JPanel() {

		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		JButton button = new JButton("Send Emergency Heart Rate");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.setLayout(boxlayout);

		return panel;
	}
	
	//Creating daily Heart Rate button
	private JPanel getService2JPanel() {

		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		JButton button = new JButton("Send Daily Heart Rates");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.setLayout(boxlayout);
		
		return panel;
	}
	
	//Creating show all messages button
	private JPanel getService3JPanel() {

		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		JButton button = new JButton("Show all messages from doctor");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.setLayout(boxlayout);

		return panel;
	}
	
	//Creating send and receive button
	private JPanel getService4JPanel() {

		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		JButton button = new JButton("Send and receive messages");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.setLayout(boxlayout);

		return panel;
	}
	
	//main method
	public static void main(String[] args) {
		
		//create an instance of the ApplicationGUI
		ApplicationGUI gui = new ApplicationGUI();
		
		//call build() method
		gui.build();
	}
	
	private void build() {
		JFrame frame = new JFrame("Service Controller Sample");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Set the BoxLayout to be X_AXIS: from left to right
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));
	
		panel.add( getService1JPanel() );
		panel.add( getService2JPanel() );
		panel.add( getService3JPanel() );
		panel.add( getService4JPanel() );

		// Set size for the frame
		frame.setSize(300, 300);

		// Set the window to be visible as the default to be false
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String label = button.getActionCommand();  
		
		//emergencyHeartRate() client
		if(label.equals("Send Emergency Heart Rate")) {
			System.out.println("Sending emergency heart rate ...");
			
			int port = 50051; //specifies where the service is running
			String host = "localhost";
			
			//specifies where the grpc is and how to contact it
			ManagedChannel newChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
			HeartRateServiceGrpc.HeartRateServiceBlockingStub blockingStub = HeartRateServiceGrpc.newBlockingStub(newChannel);
			wearables.HeartRate rate = wearables.HeartRate.newBuilder().setRate(78).build(); //setting heart rate to '78'
			wearables.Reply response = blockingStub.emergencyReport(rate); //passing heart rate to the server
			System.out.println(response.getReplyString());
			JOptionPane.showMessageDialog(null, response.getReplyString()); //displaying the reply on a JOptionPane
			
		//dailyHeartRate() client
		}else if (label.equals("Send Daily Heart Rates")) {
			System.out.println("Sending daily heart rates ...");
			
			int port = 50051; //specifies where the service is running
			String host = "localhost";
			
			//specifies where the grpc is and how to contact it
			ManagedChannel newChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
			HeartRateServiceGrpc.HeartRateServiceStub asynStub = HeartRateServiceGrpc.newStub(newChannel);
			
			StreamObserver<Reply> responseObserver = new StreamObserver<Reply>() {
				public void onNext(Reply value){
					System.out.println("Value received: " + value.getReplyString());
					JOptionPane.showMessageDialog(null, value.getReplyString()); //displaying the reply on a JOptionPane
				}
				public void onError(Throwable t) {
					System.out.println("Error recived " + t);
				}
				public void onCompleted() {
					System.out.println("Operation complete");
				}
			};
			StreamObserver<HeartRate> requestObserver = asynStub.report(responseObserver);
			try {
				//12 heart rates for half a day, one heart rate per hour
				requestObserver.onNext(HeartRate.newBuilder().setRate(60).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(50).build()); //low heart rate
				requestObserver.onNext(HeartRate.newBuilder().setRate(75).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(69).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(89).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(85).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(83).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(76).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(79).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(80).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(74).build());
				requestObserver.onNext(HeartRate.newBuilder().setRate(71).build());
				
				requestObserver.onCompleted();
				Thread.sleep(new Random().nextInt(1000)+500); //let client sleep for a small amount of time
			}catch(RuntimeException t) {
				t.printStackTrace();
			}catch(InterruptedException t) {
				t.printStackTrace();
			}
		
		//allMessages() client
		}else if (label.equals("Show all messages from doctor")) {
			System.out.println("Receiving all messages from doctor ...");
			
			int port = 50052; //specifies where the service is running
			String host = "localhost";
			
			//specifies where the grpc is and how to contact it
			ManagedChannel newChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
			MessagingServiceGrpc.MessagingServiceStub asynStub = MessagingServiceGrpc.newStub(newChannel);
			
			StreamObserver<Message> responseObserver = new StreamObserver<Message>() {
				
				//initializing a result String to hold all the values received form the server
				String result = "Messages received: ";
				@Override
				public void onNext(Message value) {
					System.out.println("Message received: " + value.getStringMessage());
					result = result + "\n '" + value.getStringMessage() + "'"; //adding the reply to the result String
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
				}

				@Override
				public void onCompleted() {
					System.out.println("message stream is completed");
					JOptionPane.showMessageDialog(null, result); //printing the result string on a JOptionPane
				}
			};
			
			//sending the request to the server
			MessageRequest request = MessageRequest.newBuilder().setStringRequest("Get all messages from doctor").build();
			asynStub.allMessages(request, responseObserver);
			try {
				Thread.sleep(15000);
			} catch (InterruptedException t) {
				t.printStackTrace();
			}	
		
		//chat() client
		}else if (label.equals("Send and receive messages")) {
			System.out.println("Sending + receiving messages ...");
			
			int port = 50052; //specifies where the service is running
			String host = "localhost";
			
			//specifies where the grpc is and how to contact it
			ManagedChannel newChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
			MessagingServiceGrpc.MessagingServiceStub asynStub = MessagingServiceGrpc.newStub(newChannel);
			
			StreamObserver<Message> responseObserver = new StreamObserver<Message>() {
				
				//initializing a result String to hold all the values received form the server
				String result = "All Messages Received: ";
				@Override
				public void onNext(Message value) {
					System.out.println("Message received: " + value.getStringMessage());
					result = result + "\n '" + value.getStringMessage() + "'"; //adding the reply to the result String
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
				}

				@Override
				public void onCompleted() {
					System.out.println("Stream completed");
					JOptionPane.showMessageDialog(null, result);
				}
			};
			
			StreamObserver<Message> requestObserver = asynStub.chat(responseObserver);

		
			try {
				//sending messages to the server
				requestObserver.onNext(Message.newBuilder().setStringMessage("Hi, could I get my perscription sent on to me?").build());
				requestObserver.onNext(Message.newBuilder().setStringMessage("Is there an appointment for tomorrow at 12?").build());
				requestObserver.onNext(Message.newBuilder().setStringMessage("How is my heart rate?").build());
				requestObserver.onNext(Message.newBuilder().setStringMessage("Thank you!").build());
					
				requestObserver.onCompleted();
					
				Thread.sleep(new Random().nextInt(1000) + 500);
			} catch (RuntimeException t) {
				t.printStackTrace();
			} catch (InterruptedException t) {			
				t.printStackTrace();
			}
		
		}else {
			
		}	
	}
}