package com.example.myimdb.utils;

import com.example.myimdb.grpc.recommendation.RecommendationProto;
import com.example.myimdb.grpc.recommendation.RecommendationServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecommendationClient implements DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(RecommendationClient.class);

    private final ManagedChannel channel;
    private final RecommendationServiceGrpc.RecommendationServiceBlockingStub blockingStub;

    private static final String HOST = "59.110.49.185";
//    private static final String HOST = "localhost";
    private static final int PORT = 50051;

    public RecommendationClient() {
        this.channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext()
                .build();
        this.blockingStub = RecommendationServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void destroy() {
        shutdown();
    }

    public void shutdown() {
        channel.shutdown();
    }

    public List<Integer> getRecommendations(int userId) {
        RecommendationProto.RecommendationRequest request = RecommendationProto.RecommendationRequest.newBuilder()
                .setUserId(userId)
                .build();

        logger.info("Recommendation request: {}", request.getUserId());

        RecommendationProto.RecommendationResponse response = blockingStub.getRecommendations(request);

        logger.info("Recommendation response: {}", response.getMovieIdsList());

        return response.getMovieIdsList();
    }
}
