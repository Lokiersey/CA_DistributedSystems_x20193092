package wearables;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service1.proto")
public final class HeartRateServiceGrpc {

  private HeartRateServiceGrpc() {}

  public static final String SERVICE_NAME = "wearables.HeartRateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<wearables.HeartRate,
      wearables.Reply> getReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "report",
      requestType = wearables.HeartRate.class,
      responseType = wearables.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<wearables.HeartRate,
      wearables.Reply> getReportMethod() {
    io.grpc.MethodDescriptor<wearables.HeartRate, wearables.Reply> getReportMethod;
    if ((getReportMethod = HeartRateServiceGrpc.getReportMethod) == null) {
      synchronized (HeartRateServiceGrpc.class) {
        if ((getReportMethod = HeartRateServiceGrpc.getReportMethod) == null) {
          HeartRateServiceGrpc.getReportMethod = getReportMethod = 
              io.grpc.MethodDescriptor.<wearables.HeartRate, wearables.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "wearables.HeartRateService", "report"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  wearables.HeartRate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  wearables.Reply.getDefaultInstance()))
                  .setSchemaDescriptor(new HeartRateServiceMethodDescriptorSupplier("report"))
                  .build();
          }
        }
     }
     return getReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<wearables.HeartRate,
      wearables.Reply> getEmergencyReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "emergencyReport",
      requestType = wearables.HeartRate.class,
      responseType = wearables.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<wearables.HeartRate,
      wearables.Reply> getEmergencyReportMethod() {
    io.grpc.MethodDescriptor<wearables.HeartRate, wearables.Reply> getEmergencyReportMethod;
    if ((getEmergencyReportMethod = HeartRateServiceGrpc.getEmergencyReportMethod) == null) {
      synchronized (HeartRateServiceGrpc.class) {
        if ((getEmergencyReportMethod = HeartRateServiceGrpc.getEmergencyReportMethod) == null) {
          HeartRateServiceGrpc.getEmergencyReportMethod = getEmergencyReportMethod = 
              io.grpc.MethodDescriptor.<wearables.HeartRate, wearables.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "wearables.HeartRateService", "emergencyReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  wearables.HeartRate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  wearables.Reply.getDefaultInstance()))
                  .setSchemaDescriptor(new HeartRateServiceMethodDescriptorSupplier("emergencyReport"))
                  .build();
          }
        }
     }
     return getEmergencyReportMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HeartRateServiceStub newStub(io.grpc.Channel channel) {
    return new HeartRateServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HeartRateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HeartRateServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HeartRateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HeartRateServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class HeartRateServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<wearables.HeartRate> report(
        io.grpc.stub.StreamObserver<wearables.Reply> responseObserver) {
      return asyncUnimplementedStreamingCall(getReportMethod(), responseObserver);
    }

    /**
     */
    public void emergencyReport(wearables.HeartRate request,
        io.grpc.stub.StreamObserver<wearables.Reply> responseObserver) {
      asyncUnimplementedUnaryCall(getEmergencyReportMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getReportMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                wearables.HeartRate,
                wearables.Reply>(
                  this, METHODID_REPORT)))
          .addMethod(
            getEmergencyReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                wearables.HeartRate,
                wearables.Reply>(
                  this, METHODID_EMERGENCY_REPORT)))
          .build();
    }
  }

  /**
   */
  public static final class HeartRateServiceStub extends io.grpc.stub.AbstractStub<HeartRateServiceStub> {
    private HeartRateServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HeartRateServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeartRateServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HeartRateServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<wearables.HeartRate> report(
        io.grpc.stub.StreamObserver<wearables.Reply> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void emergencyReport(wearables.HeartRate request,
        io.grpc.stub.StreamObserver<wearables.Reply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEmergencyReportMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HeartRateServiceBlockingStub extends io.grpc.stub.AbstractStub<HeartRateServiceBlockingStub> {
    private HeartRateServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HeartRateServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeartRateServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HeartRateServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public wearables.Reply emergencyReport(wearables.HeartRate request) {
      return blockingUnaryCall(
          getChannel(), getEmergencyReportMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HeartRateServiceFutureStub extends io.grpc.stub.AbstractStub<HeartRateServiceFutureStub> {
    private HeartRateServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HeartRateServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeartRateServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HeartRateServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<wearables.Reply> emergencyReport(
        wearables.HeartRate request) {
      return futureUnaryCall(
          getChannel().newCall(getEmergencyReportMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EMERGENCY_REPORT = 0;
  private static final int METHODID_REPORT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HeartRateServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HeartRateServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EMERGENCY_REPORT:
          serviceImpl.emergencyReport((wearables.HeartRate) request,
              (io.grpc.stub.StreamObserver<wearables.Reply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REPORT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.report(
              (io.grpc.stub.StreamObserver<wearables.Reply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HeartRateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HeartRateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return wearables.service1Impl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HeartRateService");
    }
  }

  private static final class HeartRateServiceFileDescriptorSupplier
      extends HeartRateServiceBaseDescriptorSupplier {
    HeartRateServiceFileDescriptorSupplier() {}
  }

  private static final class HeartRateServiceMethodDescriptorSupplier
      extends HeartRateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HeartRateServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HeartRateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HeartRateServiceFileDescriptorSupplier())
              .addMethod(getReportMethod())
              .addMethod(getEmergencyReportMethod())
              .build();
        }
      }
    }
    return result;
  }
}
