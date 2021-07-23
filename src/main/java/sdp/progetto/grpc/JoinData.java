// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: anello.proto

package sdp.progetto.grpc;

/**
 * Protobuf type {@code JoinData}
 */
public final class JoinData extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:JoinData)
    JoinDataOrBuilder {
private static final long serialVersionUID = 0L;
  // Use JoinData.newBuilder() to construct.
  private JoinData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private JoinData() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new JoinData();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private JoinData(
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

            sender_ = input.readInt32();
            break;
          }
          case 18: {
            sdp.progetto.grpc.HouseData.Builder subBuilder = null;
            if (newcomer_ != null) {
              subBuilder = newcomer_.toBuilder();
            }
            newcomer_ = input.readMessage(sdp.progetto.grpc.HouseData.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(newcomer_);
              newcomer_ = subBuilder.buildPartial();
            }

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
    return sdp.progetto.grpc.Anello.internal_static_JoinData_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sdp.progetto.grpc.Anello.internal_static_JoinData_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sdp.progetto.grpc.JoinData.class, sdp.progetto.grpc.JoinData.Builder.class);
  }

  public static final int SENDER_FIELD_NUMBER = 1;
  private int sender_;
  /**
   * <code>int32 sender = 1;</code>
   * @return The sender.
   */
  @java.lang.Override
  public int getSender() {
    return sender_;
  }

  public static final int NEWCOMER_FIELD_NUMBER = 2;
  private sdp.progetto.grpc.HouseData newcomer_;
  /**
   * <code>.HouseData newcomer = 2;</code>
   * @return Whether the newcomer field is set.
   */
  @java.lang.Override
  public boolean hasNewcomer() {
    return newcomer_ != null;
  }
  /**
   * <code>.HouseData newcomer = 2;</code>
   * @return The newcomer.
   */
  @java.lang.Override
  public sdp.progetto.grpc.HouseData getNewcomer() {
    return newcomer_ == null ? sdp.progetto.grpc.HouseData.getDefaultInstance() : newcomer_;
  }
  /**
   * <code>.HouseData newcomer = 2;</code>
   */
  @java.lang.Override
  public sdp.progetto.grpc.HouseDataOrBuilder getNewcomerOrBuilder() {
    return getNewcomer();
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
    if (sender_ != 0) {
      output.writeInt32(1, sender_);
    }
    if (newcomer_ != null) {
      output.writeMessage(2, getNewcomer());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (sender_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, sender_);
    }
    if (newcomer_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getNewcomer());
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
    if (!(obj instanceof sdp.progetto.grpc.JoinData)) {
      return super.equals(obj);
    }
    sdp.progetto.grpc.JoinData other = (sdp.progetto.grpc.JoinData) obj;

    if (getSender()
        != other.getSender()) return false;
    if (hasNewcomer() != other.hasNewcomer()) return false;
    if (hasNewcomer()) {
      if (!getNewcomer()
          .equals(other.getNewcomer())) return false;
    }
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
    hash = (37 * hash) + SENDER_FIELD_NUMBER;
    hash = (53 * hash) + getSender();
    if (hasNewcomer()) {
      hash = (37 * hash) + NEWCOMER_FIELD_NUMBER;
      hash = (53 * hash) + getNewcomer().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sdp.progetto.grpc.JoinData parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sdp.progetto.grpc.JoinData parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sdp.progetto.grpc.JoinData parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sdp.progetto.grpc.JoinData parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sdp.progetto.grpc.JoinData parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sdp.progetto.grpc.JoinData parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sdp.progetto.grpc.JoinData parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sdp.progetto.grpc.JoinData parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sdp.progetto.grpc.JoinData parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sdp.progetto.grpc.JoinData parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sdp.progetto.grpc.JoinData parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sdp.progetto.grpc.JoinData parseFrom(
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
  public static Builder newBuilder(sdp.progetto.grpc.JoinData prototype) {
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
   * Protobuf type {@code JoinData}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:JoinData)
      sdp.progetto.grpc.JoinDataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sdp.progetto.grpc.Anello.internal_static_JoinData_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sdp.progetto.grpc.Anello.internal_static_JoinData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sdp.progetto.grpc.JoinData.class, sdp.progetto.grpc.JoinData.Builder.class);
    }

    // Construct using sdp.progetto.grpc.JoinData.newBuilder()
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
      sender_ = 0;

      if (newcomerBuilder_ == null) {
        newcomer_ = null;
      } else {
        newcomer_ = null;
        newcomerBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sdp.progetto.grpc.Anello.internal_static_JoinData_descriptor;
    }

    @java.lang.Override
    public sdp.progetto.grpc.JoinData getDefaultInstanceForType() {
      return sdp.progetto.grpc.JoinData.getDefaultInstance();
    }

    @java.lang.Override
    public sdp.progetto.grpc.JoinData build() {
      sdp.progetto.grpc.JoinData result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sdp.progetto.grpc.JoinData buildPartial() {
      sdp.progetto.grpc.JoinData result = new sdp.progetto.grpc.JoinData(this);
      result.sender_ = sender_;
      if (newcomerBuilder_ == null) {
        result.newcomer_ = newcomer_;
      } else {
        result.newcomer_ = newcomerBuilder_.build();
      }
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
      if (other instanceof sdp.progetto.grpc.JoinData) {
        return mergeFrom((sdp.progetto.grpc.JoinData)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sdp.progetto.grpc.JoinData other) {
      if (other == sdp.progetto.grpc.JoinData.getDefaultInstance()) return this;
      if (other.getSender() != 0) {
        setSender(other.getSender());
      }
      if (other.hasNewcomer()) {
        mergeNewcomer(other.getNewcomer());
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
      sdp.progetto.grpc.JoinData parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sdp.progetto.grpc.JoinData) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int sender_ ;
    /**
     * <code>int32 sender = 1;</code>
     * @return The sender.
     */
    @java.lang.Override
    public int getSender() {
      return sender_;
    }
    /**
     * <code>int32 sender = 1;</code>
     * @param value The sender to set.
     * @return This builder for chaining.
     */
    public Builder setSender(int value) {
      
      sender_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 sender = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSender() {
      
      sender_ = 0;
      onChanged();
      return this;
    }

    private sdp.progetto.grpc.HouseData newcomer_;
    private com.google.protobuf.SingleFieldBuilderV3<
        sdp.progetto.grpc.HouseData, sdp.progetto.grpc.HouseData.Builder, sdp.progetto.grpc.HouseDataOrBuilder> newcomerBuilder_;
    /**
     * <code>.HouseData newcomer = 2;</code>
     * @return Whether the newcomer field is set.
     */
    public boolean hasNewcomer() {
      return newcomerBuilder_ != null || newcomer_ != null;
    }
    /**
     * <code>.HouseData newcomer = 2;</code>
     * @return The newcomer.
     */
    public sdp.progetto.grpc.HouseData getNewcomer() {
      if (newcomerBuilder_ == null) {
        return newcomer_ == null ? sdp.progetto.grpc.HouseData.getDefaultInstance() : newcomer_;
      } else {
        return newcomerBuilder_.getMessage();
      }
    }
    /**
     * <code>.HouseData newcomer = 2;</code>
     */
    public Builder setNewcomer(sdp.progetto.grpc.HouseData value) {
      if (newcomerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        newcomer_ = value;
        onChanged();
      } else {
        newcomerBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.HouseData newcomer = 2;</code>
     */
    public Builder setNewcomer(
        sdp.progetto.grpc.HouseData.Builder builderForValue) {
      if (newcomerBuilder_ == null) {
        newcomer_ = builderForValue.build();
        onChanged();
      } else {
        newcomerBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.HouseData newcomer = 2;</code>
     */
    public Builder mergeNewcomer(sdp.progetto.grpc.HouseData value) {
      if (newcomerBuilder_ == null) {
        if (newcomer_ != null) {
          newcomer_ =
            sdp.progetto.grpc.HouseData.newBuilder(newcomer_).mergeFrom(value).buildPartial();
        } else {
          newcomer_ = value;
        }
        onChanged();
      } else {
        newcomerBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.HouseData newcomer = 2;</code>
     */
    public Builder clearNewcomer() {
      if (newcomerBuilder_ == null) {
        newcomer_ = null;
        onChanged();
      } else {
        newcomer_ = null;
        newcomerBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.HouseData newcomer = 2;</code>
     */
    public sdp.progetto.grpc.HouseData.Builder getNewcomerBuilder() {
      
      onChanged();
      return getNewcomerFieldBuilder().getBuilder();
    }
    /**
     * <code>.HouseData newcomer = 2;</code>
     */
    public sdp.progetto.grpc.HouseDataOrBuilder getNewcomerOrBuilder() {
      if (newcomerBuilder_ != null) {
        return newcomerBuilder_.getMessageOrBuilder();
      } else {
        return newcomer_ == null ?
            sdp.progetto.grpc.HouseData.getDefaultInstance() : newcomer_;
      }
    }
    /**
     * <code>.HouseData newcomer = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        sdp.progetto.grpc.HouseData, sdp.progetto.grpc.HouseData.Builder, sdp.progetto.grpc.HouseDataOrBuilder> 
        getNewcomerFieldBuilder() {
      if (newcomerBuilder_ == null) {
        newcomerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            sdp.progetto.grpc.HouseData, sdp.progetto.grpc.HouseData.Builder, sdp.progetto.grpc.HouseDataOrBuilder>(
                getNewcomer(),
                getParentForChildren(),
                isClean());
        newcomer_ = null;
      }
      return newcomerBuilder_;
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


    // @@protoc_insertion_point(builder_scope:JoinData)
  }

  // @@protoc_insertion_point(class_scope:JoinData)
  private static final sdp.progetto.grpc.JoinData DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sdp.progetto.grpc.JoinData();
  }

  public static sdp.progetto.grpc.JoinData getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<JoinData>
      PARSER = new com.google.protobuf.AbstractParser<JoinData>() {
    @java.lang.Override
    public JoinData parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new JoinData(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<JoinData> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<JoinData> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sdp.progetto.grpc.JoinData getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

