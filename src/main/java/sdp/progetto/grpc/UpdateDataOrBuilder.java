// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: anello.proto

package sdp.progetto.grpc;

public interface UpdateDataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:UpdateData)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 sender = 1;</code>
   * @return The sender.
   */
  int getSender();

  /**
   * <code>repeated .HouseData houses = 2;</code>
   */
  java.util.List<sdp.progetto.grpc.HouseData> 
      getHousesList();
  /**
   * <code>repeated .HouseData houses = 2;</code>
   */
  sdp.progetto.grpc.HouseData getHouses(int index);
  /**
   * <code>repeated .HouseData houses = 2;</code>
   */
  int getHousesCount();
  /**
   * <code>repeated .HouseData houses = 2;</code>
   */
  java.util.List<? extends sdp.progetto.grpc.HouseDataOrBuilder> 
      getHousesOrBuilderList();
  /**
   * <code>repeated .HouseData houses = 2;</code>
   */
  sdp.progetto.grpc.HouseDataOrBuilder getHousesOrBuilder(
      int index);
}
