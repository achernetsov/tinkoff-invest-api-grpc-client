# Tinkoff invest API grpc java client

Interact with Tinkoff invest API using grpc only (without SDK).

You will get same results as using Kreya, see documentation: https://tinkoff.github.io/investAPI/grpc/

## Proto files

src/main/proto copied from https://github.com/Tinkoff/investAPI/tree/main/src/docs/contracts

# How to run

1. mvn install (will generate sources using [rotobuf-maven-plugi](https://www.xolstice.org/protobuf-maven-plugin/usage.html))
2. setup your tinkoff token in UserServiceClient.TOKEN
3. Run UserServiceClient.main from IDE, it will output your accounts

# See also

- https://grpc.io/docs/languages/java/quickstart/
- https://www.xolstice.org/protobuf-maven-plugin/usage.html

## Troubleshooting
If idea can't compile UserServiceClient after mvn install, go to project structure - modules, and 
mark target/generated-sources/protobuf/grpc-java and target/generated-sources/protobuf/java as sources.
See https://stackoverflow.com/a/5171035/827704