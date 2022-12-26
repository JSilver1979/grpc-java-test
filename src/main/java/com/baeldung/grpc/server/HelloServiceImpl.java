package com.baeldung.grpc.server;

import com.baeldung.grpc.HelloRequest;
import com.baeldung.grpc.HelloResponse;
import com.baeldung.grpc.HelloServiceGrpc.HelloServiceImplBase;

import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceImplBase {

    @Override
    public void hello(
      HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);

        String greeting = new StringBuilder().append("Hello, ")
            .append(request.getFirstName())
            .append(" ")
            .append(request.getLastName())
            .toString();
        int sum = request.getFirstNum() + request.getSecondNum();

        HelloResponse response = HelloResponse.newBuilder()
            .setGreeting(greeting)
                .setSum(sum)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
