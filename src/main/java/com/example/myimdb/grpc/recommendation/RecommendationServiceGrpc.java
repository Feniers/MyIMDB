package com.example.myimdb.grpc.recommendation;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.41.3)",
    comments = "Source: recommendation.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RecommendationServiceGrpc {

  private RecommendationServiceGrpc() {}

  public static final String SERVICE_NAME = "recommendation.RecommendationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<RecommendationProto.RecommendationRequest,
      RecommendationProto.RecommendationResponse> getGetRecommendationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRecommendations",
      requestType = RecommendationProto.RecommendationRequest.class,
      responseType = RecommendationProto.RecommendationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<RecommendationProto.RecommendationRequest,
      RecommendationProto.RecommendationResponse> getGetRecommendationsMethod() {
    io.grpc.MethodDescriptor<RecommendationProto.RecommendationRequest, RecommendationProto.RecommendationResponse> getGetRecommendationsMethod;
    if ((getGetRecommendationsMethod = RecommendationServiceGrpc.getGetRecommendationsMethod) == null) {
      synchronized (RecommendationServiceGrpc.class) {
        if ((getGetRecommendationsMethod = RecommendationServiceGrpc.getGetRecommendationsMethod) == null) {
          RecommendationServiceGrpc.getGetRecommendationsMethod = getGetRecommendationsMethod =
              io.grpc.MethodDescriptor.<RecommendationProto.RecommendationRequest, RecommendationProto.RecommendationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRecommendations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RecommendationProto.RecommendationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RecommendationProto.RecommendationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RecommendationServiceMethodDescriptorSupplier("GetRecommendations"))
              .build();
        }
      }
    }
    return getGetRecommendationsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RecommendationServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RecommendationServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RecommendationServiceStub>() {
        @Override
        public RecommendationServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RecommendationServiceStub(channel, callOptions);
        }
      };
    return RecommendationServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RecommendationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RecommendationServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RecommendationServiceBlockingStub>() {
        @Override
        public RecommendationServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RecommendationServiceBlockingStub(channel, callOptions);
        }
      };
    return RecommendationServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RecommendationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RecommendationServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RecommendationServiceFutureStub>() {
        @Override
        public RecommendationServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RecommendationServiceFutureStub(channel, callOptions);
        }
      };
    return RecommendationServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RecommendationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRecommendations(RecommendationProto.RecommendationRequest request,
                                   io.grpc.stub.StreamObserver<RecommendationProto.RecommendationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRecommendationsMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRecommendationsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                RecommendationProto.RecommendationRequest,
                RecommendationProto.RecommendationResponse>(
                  this, METHODID_GET_RECOMMENDATIONS)))
          .build();
    }
  }

  /**
   */
  public static final class RecommendationServiceStub extends io.grpc.stub.AbstractAsyncStub<RecommendationServiceStub> {
    private RecommendationServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RecommendationServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RecommendationServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRecommendations(RecommendationProto.RecommendationRequest request,
                                   io.grpc.stub.StreamObserver<RecommendationProto.RecommendationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetRecommendationsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RecommendationServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<RecommendationServiceBlockingStub> {
    private RecommendationServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RecommendationServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RecommendationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public RecommendationProto.RecommendationResponse getRecommendations(RecommendationProto.RecommendationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetRecommendationsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RecommendationServiceFutureStub extends io.grpc.stub.AbstractFutureStub<RecommendationServiceFutureStub> {
    private RecommendationServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RecommendationServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RecommendationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<RecommendationProto.RecommendationResponse> getRecommendations(
        RecommendationProto.RecommendationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetRecommendationsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_RECOMMENDATIONS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RecommendationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RecommendationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RECOMMENDATIONS:
          serviceImpl.getRecommendations((RecommendationProto.RecommendationRequest) request,
              (io.grpc.stub.StreamObserver<RecommendationProto.RecommendationResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RecommendationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RecommendationServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return RecommendationProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RecommendationService");
    }
  }

  private static final class RecommendationServiceFileDescriptorSupplier
      extends RecommendationServiceBaseDescriptorSupplier {
    RecommendationServiceFileDescriptorSupplier() {}
  }

  private static final class RecommendationServiceMethodDescriptorSupplier
      extends RecommendationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RecommendationServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RecommendationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RecommendationServiceFileDescriptorSupplier())
              .addMethod(getGetRecommendationsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
