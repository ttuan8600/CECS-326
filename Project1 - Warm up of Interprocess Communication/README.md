# Project1 - Warm up of Interprocess Commnunication

## Description

This project is a Java implementation of several servers and clients in Interprocess Communication:

## Files

- `QuoteClient.java`: The Java source code for the `QuoteClient`.
- `QuoteServer.java`: The Java source code for the `QuoteServer`.
- `EchoServer.java`: The Java source code for the `EchoServer`.
- `EchoClient.java`: The Java source code for the `EchoClient`.

### QuoteClient

The `QuoteClient` class is a simple client that connects to `QuoteServer` on port 6017 and requests a quote. The client reads the quote returned by the server and prints it to the console.

### QuoteServer

The `QuoteServer` class is a simple server that listens to port 6017 and responds to client connections by sending a quote. The quotes are printable ASCII characters and should contain fewer than 512 characters, although multiple lines are allowed.

### EchoServer

The `EchoServer` class is a simple server that listens to port 6007 and echoes back whatever the client sends to it. This server reads from the client and then writes back the result.

### EchoClient

The `EchoClient` class is a simple client that connects to the `EchoServer` on port 6007 and sends data to the server. The client reads input from the user and sends it to the server, and then prints any data sent back by the server.

## Instructions

1. Compile the servers and clients code seperately using the `javac` command.
For example:

```ruby
$ javac QuoteClient.java
$ javac QuoteServer.java
$ javac EchoClient.java
$ javac EchoServer.java

```

2. Start the servers by running the following command in seperate terminals:

```ruby
$ java QuoteClient
$ java QuoteServer
$ java EchoClient
$ java EchoServer
```

3. Entry any data in the client terminal and press enter. The server will echo back the data.
4. To exit, close the client and server terminals using `Ctrl+C`

## Contributors
### Twan Tran
### Bharath 

