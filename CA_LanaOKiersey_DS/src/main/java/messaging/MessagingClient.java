package messaging;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class MessagingClient {
	private static MessagingServiceGrpc.MessagingServiceBlockingStub blockingStub;
	private static MessagingServiceGrpc.MessagingServiceStub asynStub; //used for streaming methods

	public static void main(String[] args) throws InterruptedException {
		///Build a channel
		int port = 50052; //specifies where the service is running
		String host = "localhost";
		
		ManagedChannel newChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		blockingStub = MessagingServiceGrpc.newBlockingStub(newChannel);
		asynStub = MessagingServiceGrpc.newStub(newChannel);
		
		allMessages();
		chat();
		
		newChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	public static void allMessages() {
		StreamObserver<Message> responseObserver = new StreamObserver<Message>() {

			@Override
			public void onNext(Message value) {
				System.out.println("Message received: " + value.getStringMessage());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("message stream is completed");
			}
		};
		
		MessageRequest request = MessageRequest.newBuilder().setStringRequest("Get all messages from doctor").build();
		asynStub.allMessages(request, responseObserver);
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void chat() {
		StreamObserver<Message> responseObserver = new StreamObserver<Message>() {

			@Override
			public void onNext(Message value) {
				System.out.println("Message received: " + value.getStringMessage());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("Stream completed");
			}
		};
		
		StreamObserver<Message> requestObserver = asynStub.chat(responseObserver);

	
		try {
			requestObserver.onNext(Message.newBuilder().setStringMessage("Hi, could I get my perscription sent on to me?").build());
			requestObserver.onNext(Message.newBuilder().setStringMessage("Is there an appointment for tomorrow at 12?").build());
			requestObserver.onNext(Message.newBuilder().setStringMessage("How is my heart rate?").build());
			requestObserver.onNext(Message.newBuilder().setStringMessage("Thank you!").build());
				
			requestObserver.onCompleted();
				
			Thread.sleep(new Random().nextInt(1000) + 500);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
}
