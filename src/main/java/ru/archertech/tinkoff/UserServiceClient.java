package ru.archertech.tinkoff;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import ru.tinkoff.piapi.contract.v1.GetAccountsRequest;
import ru.tinkoff.piapi.contract.v1.GetAccountsResponse;
import ru.tinkoff.piapi.contract.v1.UsersServiceGrpc;

import java.util.concurrent.TimeUnit;

public class UserServiceClient {
    private static final String TOKEN = "PUT_YOUR_TOKEN_HERE";
    private final UsersServiceGrpc.UsersServiceBlockingStub usersServiceBlockingStub;

    public UserServiceClient(Channel channel) {
        Metadata extraHeaders = new Metadata();
        Metadata.Key<String> authorization = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
        extraHeaders.put(authorization, "Bearer " + TOKEN);
        usersServiceBlockingStub = UsersServiceGrpc.newBlockingStub(channel)
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(extraHeaders));
    }

    public void getAccounts() {
        GetAccountsRequest request = GetAccountsRequest.newBuilder().build();
        GetAccountsResponse accounts = usersServiceBlockingStub.getAccounts(request);
        System.out.println(accounts);
    }

    public static void main(String[] args) throws Exception {
        String target = "invest-public-api.tinkoff.ru:443";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .build();
        try {
            UserServiceClient client = new UserServiceClient(channel);
            client.getAccounts();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
