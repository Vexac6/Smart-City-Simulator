// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: anello.proto

package sdp.progetto.grpc;

public interface TokenDataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TokenData)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>map&lt;int32, .Statistics&gt; stats = 1;</code>
   */
  int getStatsCount();
  /**
   * <code>map&lt;int32, .Statistics&gt; stats = 1;</code>
   */
  boolean containsStats(
      int key);
  /**
   * Use {@link #getStatsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.Integer, sdp.progetto.grpc.Statistics>
  getStats();
  /**
   * <code>map&lt;int32, .Statistics&gt; stats = 1;</code>
   */
  java.util.Map<java.lang.Integer, sdp.progetto.grpc.Statistics>
  getStatsMap();
  /**
   * <code>map&lt;int32, .Statistics&gt; stats = 1;</code>
   */

  sdp.progetto.grpc.Statistics getStatsOrDefault(
      int key,
      sdp.progetto.grpc.Statistics defaultValue);
  /**
   * <code>map&lt;int32, .Statistics&gt; stats = 1;</code>
   */

  sdp.progetto.grpc.Statistics getStatsOrThrow(
      int key);
}