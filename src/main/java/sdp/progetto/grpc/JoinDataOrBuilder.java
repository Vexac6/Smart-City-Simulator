// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: anello.proto

package sdp.progetto.grpc;

public interface JoinDataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:JoinData)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 sender = 1;</code>
   * @return The sender.
   */
  int getSender();

  /**
   * <code>.HouseData newcomer = 2;</code>
   * @return Whether the newcomer field is set.
   */
  boolean hasNewcomer();
  /**
   * <code>.HouseData newcomer = 2;</code>
   * @return The newcomer.
   */
  sdp.progetto.grpc.HouseData getNewcomer();
  /**
   * <code>.HouseData newcomer = 2;</code>
   */
  sdp.progetto.grpc.HouseDataOrBuilder getNewcomerOrBuilder();
}