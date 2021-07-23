package sdp.progetto.grpc;

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
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: anello.proto")
public final class RingNetworkGrpc {

  private RingNetworkGrpc() {}

  public static final String SERVICE_NAME = "RingNetwork";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sdp.progetto.grpc.JoinData,
      sdp.progetto.grpc.ACK> getJoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Join",
      requestType = sdp.progetto.grpc.JoinData.class,
      responseType = sdp.progetto.grpc.ACK.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sdp.progetto.grpc.JoinData,
      sdp.progetto.grpc.ACK> getJoinMethod() {
    io.grpc.MethodDescriptor<sdp.progetto.grpc.JoinData, sdp.progetto.grpc.ACK> getJoinMethod;
    if ((getJoinMethod = RingNetworkGrpc.getJoinMethod) == null) {
      synchronized (RingNetworkGrpc.class) {
        if ((getJoinMethod = RingNetworkGrpc.getJoinMethod) == null) {
          RingNetworkGrpc.getJoinMethod = getJoinMethod =
              io.grpc.MethodDescriptor.<sdp.progetto.grpc.JoinData, sdp.progetto.grpc.ACK>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Join"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.JoinData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.ACK.getDefaultInstance()))
              .setSchemaDescriptor(new RingNetworkMethodDescriptorSupplier("Join"))
              .build();
        }
      }
    }
    return getJoinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sdp.progetto.grpc.LeaveData,
      sdp.progetto.grpc.ACK> getLeaveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Leave",
      requestType = sdp.progetto.grpc.LeaveData.class,
      responseType = sdp.progetto.grpc.ACK.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sdp.progetto.grpc.LeaveData,
      sdp.progetto.grpc.ACK> getLeaveMethod() {
    io.grpc.MethodDescriptor<sdp.progetto.grpc.LeaveData, sdp.progetto.grpc.ACK> getLeaveMethod;
    if ((getLeaveMethod = RingNetworkGrpc.getLeaveMethod) == null) {
      synchronized (RingNetworkGrpc.class) {
        if ((getLeaveMethod = RingNetworkGrpc.getLeaveMethod) == null) {
          RingNetworkGrpc.getLeaveMethod = getLeaveMethod =
              io.grpc.MethodDescriptor.<sdp.progetto.grpc.LeaveData, sdp.progetto.grpc.ACK>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Leave"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.LeaveData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.ACK.getDefaultInstance()))
              .setSchemaDescriptor(new RingNetworkMethodDescriptorSupplier("Leave"))
              .build();
        }
      }
    }
    return getLeaveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sdp.progetto.grpc.UpdateData,
      sdp.progetto.grpc.ACK> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Update",
      requestType = sdp.progetto.grpc.UpdateData.class,
      responseType = sdp.progetto.grpc.ACK.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sdp.progetto.grpc.UpdateData,
      sdp.progetto.grpc.ACK> getUpdateMethod() {
    io.grpc.MethodDescriptor<sdp.progetto.grpc.UpdateData, sdp.progetto.grpc.ACK> getUpdateMethod;
    if ((getUpdateMethod = RingNetworkGrpc.getUpdateMethod) == null) {
      synchronized (RingNetworkGrpc.class) {
        if ((getUpdateMethod = RingNetworkGrpc.getUpdateMethod) == null) {
          RingNetworkGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<sdp.progetto.grpc.UpdateData, sdp.progetto.grpc.ACK>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.UpdateData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.ACK.getDefaultInstance()))
              .setSchemaDescriptor(new RingNetworkMethodDescriptorSupplier("Update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sdp.progetto.grpc.ElectionData,
      sdp.progetto.grpc.ACK> getElectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Election",
      requestType = sdp.progetto.grpc.ElectionData.class,
      responseType = sdp.progetto.grpc.ACK.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sdp.progetto.grpc.ElectionData,
      sdp.progetto.grpc.ACK> getElectionMethod() {
    io.grpc.MethodDescriptor<sdp.progetto.grpc.ElectionData, sdp.progetto.grpc.ACK> getElectionMethod;
    if ((getElectionMethod = RingNetworkGrpc.getElectionMethod) == null) {
      synchronized (RingNetworkGrpc.class) {
        if ((getElectionMethod = RingNetworkGrpc.getElectionMethod) == null) {
          RingNetworkGrpc.getElectionMethod = getElectionMethod =
              io.grpc.MethodDescriptor.<sdp.progetto.grpc.ElectionData, sdp.progetto.grpc.ACK>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Election"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.ElectionData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.ACK.getDefaultInstance()))
              .setSchemaDescriptor(new RingNetworkMethodDescriptorSupplier("Election"))
              .build();
        }
      }
    }
    return getElectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sdp.progetto.grpc.TokenData,
      sdp.progetto.grpc.ACK> getPassTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PassToken",
      requestType = sdp.progetto.grpc.TokenData.class,
      responseType = sdp.progetto.grpc.ACK.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sdp.progetto.grpc.TokenData,
      sdp.progetto.grpc.ACK> getPassTokenMethod() {
    io.grpc.MethodDescriptor<sdp.progetto.grpc.TokenData, sdp.progetto.grpc.ACK> getPassTokenMethod;
    if ((getPassTokenMethod = RingNetworkGrpc.getPassTokenMethod) == null) {
      synchronized (RingNetworkGrpc.class) {
        if ((getPassTokenMethod = RingNetworkGrpc.getPassTokenMethod) == null) {
          RingNetworkGrpc.getPassTokenMethod = getPassTokenMethod =
              io.grpc.MethodDescriptor.<sdp.progetto.grpc.TokenData, sdp.progetto.grpc.ACK>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PassToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.TokenData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sdp.progetto.grpc.ACK.getDefaultInstance()))
              .setSchemaDescriptor(new RingNetworkMethodDescriptorSupplier("PassToken"))
              .build();
        }
      }
    }
    return getPassTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RingNetworkStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RingNetworkStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RingNetworkStub>() {
        @java.lang.Override
        public RingNetworkStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RingNetworkStub(channel, callOptions);
        }
      };
    return RingNetworkStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RingNetworkBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RingNetworkBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RingNetworkBlockingStub>() {
        @java.lang.Override
        public RingNetworkBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RingNetworkBlockingStub(channel, callOptions);
        }
      };
    return RingNetworkBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RingNetworkFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RingNetworkFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RingNetworkFutureStub>() {
        @java.lang.Override
        public RingNetworkFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RingNetworkFutureStub(channel, callOptions);
        }
      };
    return RingNetworkFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RingNetworkImplBase implements io.grpc.BindableService {

    /**
     */
    public void join(sdp.progetto.grpc.JoinData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinMethod(), responseObserver);
    }

    /**
     */
    public void leave(sdp.progetto.grpc.LeaveData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnimplementedUnaryCall(getLeaveMethod(), responseObserver);
    }

    /**
     */
    public void update(sdp.progetto.grpc.UpdateData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    public void election(sdp.progetto.grpc.ElectionData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnimplementedUnaryCall(getElectionMethod(), responseObserver);
    }

    /**
     */
    public void passToken(sdp.progetto.grpc.TokenData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnimplementedUnaryCall(getPassTokenMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getJoinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sdp.progetto.grpc.JoinData,
                sdp.progetto.grpc.ACK>(
                  this, METHODID_JOIN)))
          .addMethod(
            getLeaveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sdp.progetto.grpc.LeaveData,
                sdp.progetto.grpc.ACK>(
                  this, METHODID_LEAVE)))
          .addMethod(
            getUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sdp.progetto.grpc.UpdateData,
                sdp.progetto.grpc.ACK>(
                  this, METHODID_UPDATE)))
          .addMethod(
            getElectionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sdp.progetto.grpc.ElectionData,
                sdp.progetto.grpc.ACK>(
                  this, METHODID_ELECTION)))
          .addMethod(
            getPassTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sdp.progetto.grpc.TokenData,
                sdp.progetto.grpc.ACK>(
                  this, METHODID_PASS_TOKEN)))
          .build();
    }
  }

  /**
   */
  public static final class RingNetworkStub extends io.grpc.stub.AbstractAsyncStub<RingNetworkStub> {
    private RingNetworkStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RingNetworkStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RingNetworkStub(channel, callOptions);
    }

    /**
     */
    public void join(sdp.progetto.grpc.JoinData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void leave(sdp.progetto.grpc.LeaveData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLeaveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(sdp.progetto.grpc.UpdateData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void election(sdp.progetto.grpc.ElectionData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void passToken(sdp.progetto.grpc.TokenData request,
        io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPassTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RingNetworkBlockingStub extends io.grpc.stub.AbstractBlockingStub<RingNetworkBlockingStub> {
    private RingNetworkBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RingNetworkBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RingNetworkBlockingStub(channel, callOptions);
    }

    /**
     */
    public sdp.progetto.grpc.ACK join(sdp.progetto.grpc.JoinData request) {
      return blockingUnaryCall(
          getChannel(), getJoinMethod(), getCallOptions(), request);
    }

    /**
     */
    public sdp.progetto.grpc.ACK leave(sdp.progetto.grpc.LeaveData request) {
      return blockingUnaryCall(
          getChannel(), getLeaveMethod(), getCallOptions(), request);
    }

    /**
     */
    public sdp.progetto.grpc.ACK update(sdp.progetto.grpc.UpdateData request) {
      return blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public sdp.progetto.grpc.ACK election(sdp.progetto.grpc.ElectionData request) {
      return blockingUnaryCall(
          getChannel(), getElectionMethod(), getCallOptions(), request);
    }

    /**
     */
    public sdp.progetto.grpc.ACK passToken(sdp.progetto.grpc.TokenData request) {
      return blockingUnaryCall(
          getChannel(), getPassTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RingNetworkFutureStub extends io.grpc.stub.AbstractFutureStub<RingNetworkFutureStub> {
    private RingNetworkFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RingNetworkFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RingNetworkFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sdp.progetto.grpc.ACK> join(
        sdp.progetto.grpc.JoinData request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sdp.progetto.grpc.ACK> leave(
        sdp.progetto.grpc.LeaveData request) {
      return futureUnaryCall(
          getChannel().newCall(getLeaveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sdp.progetto.grpc.ACK> update(
        sdp.progetto.grpc.UpdateData request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sdp.progetto.grpc.ACK> election(
        sdp.progetto.grpc.ElectionData request) {
      return futureUnaryCall(
          getChannel().newCall(getElectionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sdp.progetto.grpc.ACK> passToken(
        sdp.progetto.grpc.TokenData request) {
      return futureUnaryCall(
          getChannel().newCall(getPassTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN = 0;
  private static final int METHODID_LEAVE = 1;
  private static final int METHODID_UPDATE = 2;
  private static final int METHODID_ELECTION = 3;
  private static final int METHODID_PASS_TOKEN = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RingNetworkImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RingNetworkImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_JOIN:
          serviceImpl.join((sdp.progetto.grpc.JoinData) request,
              (io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK>) responseObserver);
          break;
        case METHODID_LEAVE:
          serviceImpl.leave((sdp.progetto.grpc.LeaveData) request,
              (io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((sdp.progetto.grpc.UpdateData) request,
              (io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK>) responseObserver);
          break;
        case METHODID_ELECTION:
          serviceImpl.election((sdp.progetto.grpc.ElectionData) request,
              (io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK>) responseObserver);
          break;
        case METHODID_PASS_TOKEN:
          serviceImpl.passToken((sdp.progetto.grpc.TokenData) request,
              (io.grpc.stub.StreamObserver<sdp.progetto.grpc.ACK>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RingNetworkBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RingNetworkBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sdp.progetto.grpc.Anello.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RingNetwork");
    }
  }

  private static final class RingNetworkFileDescriptorSupplier
      extends RingNetworkBaseDescriptorSupplier {
    RingNetworkFileDescriptorSupplier() {}
  }

  private static final class RingNetworkMethodDescriptorSupplier
      extends RingNetworkBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RingNetworkMethodDescriptorSupplier(String methodName) {
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
      synchronized (RingNetworkGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RingNetworkFileDescriptorSupplier())
              .addMethod(getJoinMethod())
              .addMethod(getLeaveMethod())
              .addMethod(getUpdateMethod())
              .addMethod(getElectionMethod())
              .addMethod(getPassTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
