package messaging;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import messaging.MessagingServiceGrpc.MessagingServiceImplBase;

public class MessagingServer {
	
	private Server server;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		final MessagingServer ourServer = new MessagingServer();
		ourServer.start();
	}
	
	public void start() throws IOException, InterruptedException {
		System.out.println("Server Starting");
		
		int port = 50052;
		
		server = ServerBuilder.forPort(port).addService(new NewService2Impl()).build().start();
		server.awaitTermination();
		
		System.out.println("Server running on port " + port);
	}
	
	static class NewService2Impl extends MessagingServiceImplBase{
		
		@Override
		public void allMessages(MessageRequest request, StreamObserver<Message> responseObserver) {
			String messageReq = request.getStringRequest();
			System.out.println("Reqeust recieved: " + messageReq);
			Message reply = Message.newBuilder().setStringMessage("Message1").build();
			responseObserver.onNext(reply);
			reply = Message.newBuilder().setStringMessage("Message2").build();
			responseObserver.onNext(reply);
			reply = Message.newBuilder().setStringMessage("Message3").build();
			responseObserver.onNext(reply);
			reply = Message.newBuilder().setStringMessage("Message4").build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
		
		public StreamObserver<Message> chat(StreamObserver<Message> responseObserver){
			return new StreamObserver<Message> () {

				@Override
				public void onNext(Message value) {
					String messageReq = value.getStringMessage();
					System.out.println("Message recieved: " + messageReq);
					Message reply = Message.newBuilder().setStringMessage(messageReq).build();
					responseObserver.onNext(reply);
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
