package wearables;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import wearables.HeartRateServiceGrpc.HeartRateServiceImplBase;


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
				
				boolean heartRateTooHigh = false;
				boolean heartRateTooLow = false;
				@Override
				public void onNext(HeartRate value) {
					System.out.println("Heartrate recieved" + value.getRate());
					int heartRate = value.getRate();
					if(heartRate<60) {
						heartRateTooLow = true;
					}else if(heartRate> 100) {
						heartRateTooHigh = true;
					}
				}
				@Override
				public void onError(Throwable t) {
					System.out.println("Error recieved " + t);
				}
				
				Reply reply;
				@Override
				public void onCompleted() {
					if(heartRateTooHigh) {
						reply = Reply.newBuilder().setReplyString("Your daily heart rate level is TOO HIGH, please go to the doctor").build();
						responseObserver.onNext(reply);
						responseObserver.onCompleted();
					}else if(heartRateTooLow) {
						reply = Reply.newBuilder().setReplyString("Your daily heart rate level is TOO LOW, please go to the doctor").build();
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
