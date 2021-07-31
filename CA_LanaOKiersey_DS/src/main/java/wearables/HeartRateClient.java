package grpc.wearables;

import java.util.concurrent.TimeUnit;

import grpc.wearables.HeartRateServiceGrpc.HeartRateServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HeartRateClient {

	public static void main(String[] args) throws InterruptedException {
		///Build a channel
		int port = 50051; //specifies where the service is running
		String host = "localhost";
		
		//specifies where the grpc is and how to contact it
		ManagedChannel newChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		//^^ all generic - may change port number
		
		//build our message
		HeartRate rate = HeartRate.newBuilder().setRate(78).build();
		
		//build code to use channel to send message
		//create a stub - blocking stub for unary?
		//pass the channel to the stub
		HeartRateServiceBlockingStub bstub = HeartRateServiceGrpc.newBlockingStub(newChannel);
		
		//stub -local representation of the local object
		//Calling the server and receiving a response
		Reply response = bstub.emergencyReport(rate);
		
		System.out.println(response.getReplyString());
		
		//shut down the channel
		newChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

}
