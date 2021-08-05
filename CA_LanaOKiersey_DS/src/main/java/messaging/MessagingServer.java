package messaging;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import messaging.MessagingServer.NewService2Impl;
import messaging.MessagingServiceGrpc.MessagingServiceImplBase;

public class MessagingServer {
	
	private Server server;
	
	//main method
	public static void main(String[] args) throws IOException, InterruptedException {
		
		final MessagingServer ourServer = new MessagingServer();
		ourServer.start();
	}
	
	public void start() throws IOException, InterruptedException {
		System.out.println("Server Starting");
		
		//creating the port
		int port = 50052;
		//creating the server
		server = ServerBuilder.forPort(port).addService(new NewService2Impl()).build().start();
		server.awaitTermination();
		
		System.out.println("Server running on port " + port);
	}
	
	static class NewService2Impl extends MessagingServiceImplBase{
		
		//allMessages() method
		@Override
		public void allMessages(MessageRequest request, StreamObserver<Message> responseObserver) {
			String messageReq = request.getStringRequest();
			System.out.println("Reqeust recieved: " + messageReq);
			//4 replies created and sent to the client 
			Message reply = Message.newBuilder().setStringMessage("Your perscription has been sent to your email").build();
			responseObserver.onNext(reply);
			reply = Message.newBuilder().setStringMessage("Yes there is an appointment available").build();
			responseObserver.onNext(reply);
			reply = Message.newBuilder().setStringMessage("Your heart rate is normal").build();
			responseObserver.onNext(reply);
			reply = Message.newBuilder().setStringMessage("No problem!").build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
		
		public StreamObserver<Message> chat(StreamObserver<Message> responseObserver){
			return new StreamObserver<Message> () {
				//count initialized to keep count on how many messages are received from the client
				int count=0;
				@Override
				public void onNext(Message value) {
					String messageReq = value.getStringMessage();
					System.out.println("Message recieved: " + messageReq);
					Message reply = null;
					if(count == 0) {
						reply = Message.newBuilder().setStringMessage("Your perscription has been sent to your email").build();
					}else if(count == 1) {
						reply = Message.newBuilder().setStringMessage("Yes there is an appointment available").build();
					}else if(count == 2) {
						reply = Message.newBuilder().setStringMessage("Your heart rate is normal").build();
					}else if(count == 3) {
						reply = Message.newBuilder().setStringMessage("No problem!").build();
					}else {
						reply = Message.newBuilder().setStringMessage("").build();
					}
					responseObserver.onNext(reply); //send reply to the client
					count++; //increment count
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
					
				}

				@Override
				public void onCompleted() {
					System.out.println("Chat completed");
					responseObserver.onCompleted();
					
				}
			};
		}
	}
}
