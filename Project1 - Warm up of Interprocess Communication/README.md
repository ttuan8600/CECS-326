# Network Programming Examples

This repository contains several examples of network programming in Java:

- `DateServer`: A server that listens on port 6017 and returns the current date to clients.
- `QuoteServer`: A server that listens on port 6017 and returns a random quote of the day to clients.
- `EchoServer`: A server that listens on port 6007 and echoes back any data sent by clients.
- `EchoClient`: A client that connects to the EchoServer on port 6007 and sends data to the server.

## DateServer

The `DateServer` class is a simple server that listens on port 6017 and returns the current date to clients that connect to it. The server runs in an infinite loop, accepting connections from clients and sending the current date to each client that connects.

## QuoteServer

The `QuoteServer` class is a simple server that listens on port 6017 and returns a random quote of the day to clients that connect to it. The server reads a file of quotes on startup and chooses a random quote to return to each client that connects.

## EchoServer

The `EchoServer` class is a simple server that listens on port 6007 and echoes back any data sent by clients that connect to it. The server runs in an infinite loop, accepting connections from clients and echoing back any data sent by each client that connects.

## EchoClient

The `EchoClient` class is a simple client that connects to the `EchoServer` on port 6007 and sends data to the server. The client reads input from the user and sends it to the server, and then prints any data sent back by the server.

To run the examples, you can compile the Java files using `javac` and then run the classes using `java`. 

For example:

