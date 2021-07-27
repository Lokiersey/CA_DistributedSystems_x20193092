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
			//build out response
			Reply reply = Reply.newBuilder().setReplyString("Heart rate recieved: " +heartRate).build();
			
			//Send out message
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
			
		}
	}
}
