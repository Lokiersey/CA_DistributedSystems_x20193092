package grpc.wearables;

import java.io.IOException;

import grpc.wearables.HeartRateServiceGrpc.HeartRateServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;


public class HeartRateServer {
	
	private Server server;

	public static void main(String[] args) throws IOException, InterruptedException {
		//instantiate class
		final HeartRateServer ourServer = new HeartRateServer();
		ourServer.start();
	}

	private void start() throws IOException, InterruptedException {
		System.out.println("Server starting");
		
		int port = 50051; //dont go less than 1000
		// different ports for different services e.g. 50052
		
		server = ServerBuilder.forPort(port).addService(new NewService1Impl()).build().start();
		server.awaitTermination();
		
		System.out.println("Server running on port: " + port);
		
	}
	
	static class NewService1Impl extends HeartRateServiceImplBase {
		
		@Override
		public void emergencyReport(HeartRate request, StreamObserver<Reply> responseObserver) {
			
			int heartRate = request.getRate();
			System.out.println("Recieved heart rate: " + heartRate);
			Reply reply;
			//build our response
			if(heartRate<60 || heartRate > 100) {
				reply = Reply.newBuilder().setReplyString("Heart rate recieved: " +heartRate + " Please go to the doctor").build();
			}else {
				reply = Reply.newBuilder().setReplyString("Heart rate recieved: " +heartRate + " Heart rate normal").build();
			}
			
			//Send out message
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
		
		@Override
		public StreamObserver<HeartRate> report(StreamObserver<Reply> responseObserver) {
			return new StreamObserver<HeartRate>() {
				
				boolean heartRateDangerous = false;
				public void onNext(HeartRate value) {
					int heartRate = value.getRate();
					if(heartRate<60 || heartRate > 100) {
						heartRateDangerous = true;
					}
				}
				public void onError(Throwable t) {
					System.out.println("Error recieved " + t);
				}
				Reply reply;
				public void onCompleted() {
					if(heartRateDangerous) {
						reply = Reply.newBuilder().setReplyString("Your daily heart rate level is too high, please go to the doctor").build();
						responseObserver.onNext(reply);
						responseObserver.onCompleted();
					}else {
						reply = Reply.newBuilder().setReplyString("Your daily heart rate levels are normal").build();
						responseObserver.onNext(reply);
						responseObserver.onCompleted();
					}
				}
			};
	}
}
}
