// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: anello.proto

package sdp.progetto.grpc;

/**
 * Protobuf type {@code LeaveData}
 */
public final class LeaveData extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:LeaveData)
    LeaveDataOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LeaveData.newBuilder() to construct.
  private LeaveData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LeaveData() {
    emergencyAddress_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new LeaveData();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private LeaveData(
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
          case 16: {

            next_ = input.readInt32();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            emergencyAddress_ = s;
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
    return sdp.progetto.grpc.Anello.internal_static_LeaveData_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sdp.progetto.grpc.Anello.internal_static_LeaveData_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sdp.progetto.grpc.LeaveData.class, sdp.progetto.grpc.LeaveData.Builder.class);
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

  public static final int NEXT_FIELD_NUMBER = 2;
  private int next_;
  /**
   * <code>int32 next = 2;</code>
   * @return The next.
   */
  @java.lang.Override
  public int getNext() {
    return next_;
  }

  public static final int EMERGENCYADDRESS_FIELD_NUMBER = 3;
  private volatile java.lang.Object emergencyAddress_;
  /**
   * <code>string emergencyAddress = 3;</code>
   * @return The emergencyAddress.
   */
  @java.lang.Override
  public java.lang.String getEmergencyAddress() {
    java.lang.Object ref = emergencyAddress_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      emergencyAddress_ = s;
      return s;
    }
  }
  /**
   * <code>string emergencyAddress = 3;</code>
   * @return The bytes for emergencyAddress.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getEmergencyAddressBytes() {
    java.lang.Object ref = emergencyAddress_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      emergencyAddress_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (next_ != 0) {
      output.writeInt32(2, next_);
    }
    if (!getEmergencyAddressBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, emergencyAddress_);
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
    if (next_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, next_);
    }
    if (!getEmergencyAddressBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, emergencyAddress_);
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
    if (!(obj instanceof sdp.progetto.grpc.LeaveData)) {
      return super.equals(obj);
    }
    sdp.progetto.grpc.LeaveData other = (sdp.progetto.grpc.LeaveData) obj;

    if (getSender()
        != other.getSender()) return false;
    if (getNext()
        != other.getNext()) return false;
    if (!getEmergencyAddress()
        .equals(other.getEmergencyAddress())) return false;
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
    hash = (37 * hash) + NEXT_FIELD_NUMBER;
    hash = (53 * hash) + getNext();
    hash = (37 * hash) + EMERGENCYADDRESS_FIELD_NUMBER;
    hash = (53 * hash) + getEmergencyAddress().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sdp.progetto.grpc.LeaveData parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sdp.progetto.grpc.LeaveData parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sdp.progetto.grpc.LeaveData parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sdp.progetto.grpc.LeaveData parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sdp.progetto.grpc.LeaveData parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sdp.progetto.grpc.LeaveData parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sdp.progetto.grpc.LeaveData parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sdp.progetto.grpc.LeaveData parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sdp.progetto.grpc.LeaveData parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sdp.progetto.grpc.LeaveData parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sdp.progetto.grpc.LeaveData parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sdp.progetto.grpc.LeaveData parseFrom(
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
  public static Builder newBuilder(sdp.progetto.grpc.LeaveData prototype) {
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
   * Protobuf type {@code LeaveData}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:LeaveData)
      sdp.progetto.grpc.LeaveDataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sdp.progetto.grpc.Anello.internal_static_LeaveData_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sdp.progetto.grpc.Anello.internal_static_LeaveData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sdp.progetto.grpc.LeaveData.class, sdp.progetto.grpc.LeaveData.Builder.class);
    }

    // Construct using sdp.progetto.grpc.LeaveData.newBuilder()
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

      next_ = 0;

      emergencyAddress_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sdp.progetto.grpc.Anello.internal_static_LeaveData_descriptor;
    }

    @java.lang.Override
    public sdp.progetto.grpc.LeaveData getDefaultInstanceForType() {
      return sdp.progetto.grpc.LeaveData.getDefaultInstance();
    }

    @java.lang.Override
    public sdp.progetto.grpc.LeaveData build() {
      sdp.progetto.grpc.LeaveData result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sdp.progetto.grpc.LeaveData buildPartial() {
      sdp.progetto.grpc.LeaveData result = new sdp.progetto.grpc.LeaveData(this);
      result.sender_ = sender_;
      result.next_ = next_;
      result.emergencyAddress_ = emergencyAddress_;
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
      if (other instanceof sdp.progetto.grpc.LeaveData) {
        return mergeFrom((sdp.progetto.grpc.LeaveData)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sdp.progetto.grpc.LeaveData other) {
      if (other == sdp.progetto.grpc.LeaveData.getDefaultInstance()) return this;
      if (other.getSender() != 0) {
        setSender(other.getSender());
      }
      if (other.getNext() != 0) {
        setNext(other.getNext());
      }
      if (!other.getEmergencyAddress().isEmpty()) {
        emergencyAddress_ = other.emergencyAddress_;
        onChanged();
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
      sdp.progetto.grpc.LeaveData parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sdp.progetto.grpc.LeaveData) e.getUnfinishedMessage();
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

    private int next_ ;
    /**
     * <code>int32 next = 2;</code>
     * @return The next.
     */
    @java.lang.Override
    public int getNext() {
      return next_;
    }
    /**
     * <code>int32 next = 2;</code>
     * @param value The next to set.
     * @return This builder for chaining.
     */
    public Builder setNext(int value) {
      
      next_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 next = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearNext() {
      
      next_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object emergencyAddress_ = "";
    /**
     * <code>string emergencyAddress = 3;</code>
     * @return The emergencyAddress.
     */
    public java.lang.String getEmergencyAddress() {
      java.lang.Object ref = emergencyAddress_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        emergencyAddress_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string emergencyAddress = 3;</code>
     * @return The bytes for emergencyAddress.
     */
    public com.google.protobuf.ByteString
        getEmergencyAddressBytes() {
      java.lang.Object ref = emergencyAddress_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        emergencyAddress_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string emergencyAddress = 3;</code>
     * @param value The emergencyAddress to set.
     * @return This builder for chaining.
     */
    public Builder setEmergencyAddress(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      emergencyAddress_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string emergencyAddress = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearEmergencyAddress() {
      
      emergencyAddress_ = getDefaultInstance().getEmergencyAddress();
      onChanged();
      return this;
    }
    /**
     * <code>string emergencyAddress = 3;</code>
     * @param value The bytes for emergencyAddress to set.
     * @return This builder for chaining.
     */
    public Builder setEmergencyAddressBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      emergencyAddress_ = value;
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


    // @@protoc_insertion_point(builder_scope:LeaveData)
  }

  // @@protoc_insertion_point(class_scope:LeaveData)
  private static final sdp.progetto.grpc.LeaveData DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sdp.progetto.grpc.LeaveData();
  }

  public static sdp.progetto.grpc.LeaveData getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<LeaveData>
      PARSER = new com.google.protobuf.AbstractParser<LeaveData>() {
    @java.lang.Override
    public LeaveData parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new LeaveData(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<LeaveData> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LeaveData> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sdp.progetto.grpc.LeaveData getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

