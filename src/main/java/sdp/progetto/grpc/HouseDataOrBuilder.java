// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: anello.proto

package sdp.progetto.grpc;

public interface HouseDataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:HouseData)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string address = 2;</code>
   * @return The address.
   */
  java.lang.String getAddress();
  /**
   * <code>string address = 2;</code>
   * @return The bytes for address.
   */
  com.google.protobuf.ByteString
      getAddressBytes();

  /**
   * <code>int32 port = 3;</code>
   * @return The port.
   */
  int getPort();
}