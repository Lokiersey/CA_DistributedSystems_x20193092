// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service2.proto

package messaging;

public final class service2Impl {
  private service2Impl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_messaging_MessageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_messaging_MessageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_messaging_Message_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_messaging_Message_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016service2.proto\022\tmessaging\"\'\n\016MessageRe" +
      "quest\022\025\n\rstringRequest\030\001 \001(\t\" \n\007Message\022" +
      "\025\n\rstringMessage\030\001 \001(\t2\212\001\n\020MessagingServ" +
      "ice\022@\n\013allMessages\022\031.messaging.MessageRe" +
      "quest\032\022.messaging.Message\"\0000\001\0224\n\004chat\022\022." +
      "messaging.Message\032\022.messaging.Message\"\000(" +
      "\0010\001B\033\n\tmessagingB\014service2ImplP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_messaging_MessageRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_messaging_MessageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_messaging_MessageRequest_descriptor,
        new java.lang.String[] { "StringRequest", });
    internal_static_messaging_Message_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_messaging_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_messaging_Message_descriptor,
        new java.lang.String[] { "StringMessage", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
