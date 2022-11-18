package org.example;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        // канал для передачи данных
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();

        // stub - это специальный объект, на котором можно делать удаленные запросы, удаленные вызовы процедур
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder()
                .setName("Artur")
                .build();

        GreetingServiceOuterClass.HelloResponse response = stub.greeting(request); // по сети придет ответ от сервера

        System.out.println(response);

        channel.shutdownNow(); // закрываем канал после того, как получили ответ
    }
}
