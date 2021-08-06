package wearables;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import wearables.HeartRateServer.NewService1Impl;
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
		
		//jmDNS
		int port = 50051; 
		String service_type = "_grpc._tcp.local.";
		String service_name = "GrpcServer";
		jmDNS.ServiceRegistration ssr = new jmDNS.ServiceRegistration();
		ssr.run(port, service_type, service_name);
		
		//create server
		server = ServerBuilder.forPort(port).addService(new NewService1Impl()).build().start();
		server.awaitTermination();
		
		System.out.println("Server running on port: " + port);
		
	}
	
	static class NewService1Impl extends HeartRateServiceImplBase {
		
		//emergencyReport() method
		@Override
		public void emergencyReport(HeartRate request, StreamObserver<Reply> responseObserver) {
			
			int heartRate = request.getRate();
			System.out.println("Recieved heart rate: " + heartRate);
			Reply reply;
			//build response
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
			System.out.println("In the report method");
			return new StreamObserver<HeartRate>() {
				
				//Initialize booleans as false
				boolean heartRateTooHigh = false;
				boolean heartRateTooLow = false;
				@Override
				public void onNext(HeartRate value) {
					System.out.println("Heartrate recieved" + value.getRate());
					int heartRate = value.getRate();
					//compare received heart rate to 60 - below 60 is a low heart rate
					if(heartRate<60) {
						heartRateTooLow = true;
					//compare received heart rate to 100 - above 100 is a high heart rate
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
						//the users heart rate is too high
						reply = Reply.newBuilder().setReplyString("Your daily heart rate level is TOO HIGH, please go to the doctor").build();
						responseObserver.onNext(reply); //send reply
						responseObserver.onCompleted();
					}else if(heartRateTooLow) {
						//the users heart rate is too low
						reply = Reply.newBuilder().setReplyString("Your daily heart rate level is TOO LOW, please go to the doctor").build();
						responseObserver.onNext(reply); //send reply
						responseObserver.onCompleted();
					}else {
						//the users heart rate is normal
						reply = Reply.newBuilder().setReplyString("Your daily heart rate levels are normal").build();
						responseObserver.onNext(reply); //send reply
						responseObserver.onCompleted();
					}
				}
			};
		}
	}
}
