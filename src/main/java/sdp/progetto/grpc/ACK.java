// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: anello.proto

package sdp.progetto.grpc;

/**
 * Protobuf type {@code ACK}
 */
public final class ACK extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ACK)
    ACKOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ACK.newBuilder() to construct.
  private ACK(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ACK() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ACK();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ACK(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            oK_ = input.readBool();
            break;
          }
          case 16: {

            nextNodeID_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sdp.progetto.grpc.Anello.internal_static_ACK_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sdp.progetto.grpc.Anello.internal_static_ACK_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sdp.progetto.grpc.ACK.class, sdp.progetto.grpc.ACK.Builder.class);
  }

  public static final int OK_FIELD_NUMBER = 1;
  private boolean oK_;
  /**
   * <code>bool OK = 1;</code>
   * @return The oK.
   */
  @java.lang.Override
  public boolean getOK() {
    return oK_;
  }

  public static final int NEXTNODEID_FIELD_NUMBER = 2;
  private int nextNodeID_;
  /**
   * <code>int32 nextNodeID = 2;</code>
   * @return The nextNodeID.
   */
  @java.lang.Override
  public int getNextNodeID() {
    return nextNodeID_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (oK_ != false) {
      output.writeBool(1, oK_);
    }
    if (nextNodeID_ != 0) {
      output.writeInt32(2, nextNodeID_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (oK_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, oK_);
    }
    if (nextNodeID_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, nextNodeID_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof sdp.progetto.grpc.ACK)) {
      return super.equals(obj);
    }
    sdp.progetto.grpc.ACK other = (sdp.progetto.grpc.ACK) obj;

    if (getOK()
        != other.getOK()) return false;
    if (getNextNodeID()
        != other.getNextNodeID()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + OK_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getOK());
    hash = (37 * hash) + NEXTNODEID_FIELD_NUMBER;
    hash = (53 * hash) + getNextNodeID();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sdp.progetto.grpc.ACK parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sdp.progetto.grpc.ACK parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sdp.progetto.grpc.ACK parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sdp.progetto.grpc.ACK parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sdp.progetto.grpc.ACK parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sdp.progetto.grpc.ACK parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sdp.progetto.grpc.ACK parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sdp.progetto.grpc.ACK parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sdp.progetto.grpc.ACK parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sdp.progetto.grpc.ACK parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sdp.progetto.grpc.ACK parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sdp.progetto.grpc.ACK parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(sdp.progetto.grpc.ACK prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code ACK}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ACK)
      sdp.progetto.grpc.ACKOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sdp.progetto.grpc.Anello.internal_static_ACK_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sdp.progetto.grpc.Anello.internal_static_ACK_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sdp.progetto.grpc.ACK.class, sdp.progetto.grpc.ACK.Builder.class);
    }

    // Construct using sdp.progetto.grpc.ACK.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      oK_ = false;

      nextNodeID_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sdp.progetto.grpc.Anello.internal_static_ACK_descriptor;
    }

    @java.lang.Override
    public sdp.progetto.grpc.ACK getDefaultInstanceForType() {
      return sdp.progetto.grpc.ACK.getDefaultInstance();
    }

    @java.lang.Override
    public sdp.progetto.grpc.ACK build() {
      sdp.progetto.grpc.ACK result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sdp.progetto.grpc.ACK buildPartial() {
      sdp.progetto.grpc.ACK result = new sdp.progetto.grpc.ACK(this);
      result.oK_ = oK_;
      result.nextNodeID_ = nextNodeID_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof sdp.progetto.grpc.ACK) {
        return mergeFrom((sdp.progetto.grpc.ACK)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sdp.progetto.grpc.ACK other) {
      if (other == sdp.progetto.grpc.ACK.getDefaultInstance()) return this;
      if (other.getOK() != false) {
        setOK(other.getOK());
      }
      if (other.getNextNodeID() != 0) {
        setNextNodeID(other.getNextNodeID());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      sdp.progetto.grpc.ACK parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sdp.progetto.grpc.ACK) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private boolean oK_ ;
    /**
     * <code>bool OK = 1;</code>
     * @return The oK.
     */
    @java.lang.Override
    public boolean getOK() {
      return oK_;
    }
    /**
     * <code>bool OK = 1;</code>
     * @param value The oK to set.
     * @return This builder for chaining.
     */
    public Builder setOK(boolean value) {
      
      oK_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool OK = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearOK() {
      
      oK_ = false;
      onChanged();
      return this;
    }

    private int nextNodeID_ ;
    /**
     * <code>int32 nextNodeID = 2;</code>
     * @return The nextNodeID.
     */
    @java.lang.Override
    public int getNextNodeID() {
      return nextNodeID_;
    }
    /**
     * <code>int32 nextNodeID = 2;</code>
     * @param value The nextNodeID to set.
     * @return This builder for chaining.
     */
    public Builder setNextNodeID(int value) {
      
      nextNodeID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 nextNodeID = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearNextNodeID() {
      
      nextNodeID_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:ACK)
  }

  // @@protoc_insertion_point(class_scope:ACK)
  private static final sdp.progetto.grpc.ACK DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sdp.progetto.grpc.ACK();
  }

  public static sdp.progetto.grpc.ACK getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ACK>
      PARSER = new com.google.protobuf.AbstractParser<ACK>() {
    @java.lang.Override
    public ACK parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ACK(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ACK> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ACK> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sdp.progetto.grpc.ACK getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

