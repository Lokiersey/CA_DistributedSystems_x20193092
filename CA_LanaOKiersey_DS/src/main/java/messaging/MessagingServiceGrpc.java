package messaging;

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
    comments = "Source: service2.proto")
public final class MessagingServiceGrpc {

  private MessagingServiceGrpc() {}

  public static final String SERVICE_NAME = "messaging.MessagingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<messaging.MessageRequest,
      messaging.Message> getAllMessagesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "allMessages",
      requestType = messaging.MessageRequest.class,
      responseType = messaging.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<messaging.MessageRequest,
      messaging.Message> getAllMessagesMethod() {
    io.grpc.MethodDescriptor<messaging.MessageRequest, messaging.Message> getAllMessagesMethod;
    if ((getAllMessagesMethod = MessagingServiceGrpc.getAllMessagesMethod) == null) {
      synchronized (MessagingServiceGrpc.class) {
        if ((getAllMessagesMethod = MessagingServiceGrpc.getAllMessagesMethod) == null) {
          MessagingServiceGrpc.getAllMessagesMethod = getAllMessagesMethod = 
              io.grpc.MethodDescriptor.<messaging.MessageRequest, messaging.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "messaging.MessagingService", "allMessages"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messaging.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messaging.Message.getDefaultInstance()))
                  .setSchemaDescriptor(new MessagingServiceMethodDescriptorSupplier("allMessages"))
                  .build();
          }
        }
     }
     return getAllMessagesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<messaging.Message,
      messaging.Message> getChatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "chat",
      requestType = messaging.Message.class,
      responseType = messaging.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<messaging.Message,
      messaging.Message> getChatMethod() {
    io.grpc.MethodDescriptor<messaging.Message, messaging.Message> getChatMethod;
    if ((getChatMethod = MessagingServiceGrpc.getChatMethod) == null) {
      synchronized (MessagingServiceGrpc.class) {
        if ((getChatMethod = MessagingServiceGrpc.getChatMethod) == null) {
          MessagingServiceGrpc.getChatMethod = getChatMethod = 
              io.grpc.MethodDescriptor.<messaging.Message, messaging.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "messaging.MessagingService", "chat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messaging.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messaging.Message.getDefaultInstance()))
                  .setSchemaDescriptor(new MessagingServiceMethodDescriptorSupplier("chat"))
                  .build();
          }
        }
     }
     return getChatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MessagingServiceStub newStub(io.grpc.Channel channel) {
    return new MessagingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MessagingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MessagingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MessagingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MessagingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MessagingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void allMessages(messaging.MessageRequest request,
        io.grpc.stub.StreamObserver<messaging.Message> responseObserver) {
      asyncUnimplementedUnaryCall(getAllMessagesMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<messaging.Message> chat(
        io.grpc.stub.StreamObserver<messaging.Message> responseObserver) {
      return asyncUnimplementedStreamingCall(getChatMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAllMessagesMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                messaging.MessageRequest,
                messaging.Message>(
                  this, METHODID_ALL_MESSAGES)))
          .addMethod(
            getChatMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                messaging.Message,
                messaging.Message>(
                  this, METHODID_CHAT)))
          .build();
    }
  }

  /**
   */
  public static final class MessagingServiceStub extends io.grpc.stub.AbstractStub<MessagingServiceStub> {
    private MessagingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessagingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessagingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessagingServiceStub(channel, callOptions);
    }

    /**
     */
    public void allMessages(messaging.MessageRequest request,
        io.grpc.stub.StreamObserver<messaging.Message> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getAllMessagesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<messaging.Message> chat(
        io.grpc.stub.StreamObserver<messaging.Message> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getChatMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class MessagingServiceBlockingStub extends io.grpc.stub.AbstractStub<MessagingServiceBlockingStub> {
    private MessagingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessagingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessagingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessagingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<messaging.Message> allMessages(
        messaging.MessageRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getAllMessagesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MessagingServiceFutureStub extends io.grpc.stub.AbstractStub<MessagingServiceFutureStub> {
    private MessagingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessagingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessagingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessagingServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_ALL_MESSAGES = 0;
  private static final int METHODID_CHAT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MessagingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MessagingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ALL_MESSAGES:
          serviceImpl.allMessages((messaging.MessageRequest) request,
              (io.grpc.stub.StreamObserver<messaging.Message>) responseObserver);
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
        case METHODID_CHAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.chat(
              (io.grpc.stub.StreamObserver<messaging.Message>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MessagingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MessagingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return messaging.service2Impl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MessagingService");
    }
  }

  private static final class MessagingServiceFileDescriptorSupplier
      extends MessagingServiceBaseDescriptorSupplier {
    MessagingServiceFileDescriptorSupplier() {}
  }

  private static final class MessagingServiceMethodDescriptorSupplier
      extends MessagingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MessagingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MessagingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MessagingServiceFileDescriptorSupplier())
              .addMethod(getAllMessagesMethod())
              .addMethod(getChatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
