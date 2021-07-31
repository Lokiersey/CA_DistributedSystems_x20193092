package wearables;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class HeartRateClient {
	private static HeartRateServiceGrpc.HeartRateServiceBlockingStub blockingStub;
	private static HeartRateServiceGrpc.HeartRateServiceStub asynStub; //used for streaming methods

	public static void main(String[] args) throws InterruptedException {
		///Build a channel
		int port = 50051; //specifies where the service is running
		String host = "localhost";
		
		//specifies where the grpc is and how to contact it
		ManagedChannel newChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		blockingStub = HeartRateServiceGrpc.newBlockingStub(newChannel);
		asynStub = HeartRateServiceGrpc.newStub(newChannel);
		//^^ all generic - may change port number
		
		emergencyReport();
		report();
		
		//shut down the channel
		newChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	public static void emergencyReport() {
		HeartRate rate = HeartRate.newBuilder().setRate(78).build();
		Reply response = blockingStub.emergencyReport(rate);
		System.out.println(response.getReplyString());
	}
	
	public static void report() {
		StreamObserver<Reply> responseObserver = new StreamObserver<Reply>() {
			public void onNext(Reply value){
				System.out.println("Value received: " + value.getReplyString());
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
			requestObserver.onNext(HeartRate.newBuilder().setRate(60).build());
			requestObserver.onNext(HeartRate.newBuilder().setRate(72).build());
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
			Thread.sleep(new Random().nextInt(1000)+500);
		}catch(RuntimeException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}