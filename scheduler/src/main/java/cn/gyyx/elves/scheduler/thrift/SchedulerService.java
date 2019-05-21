package cn.gyyx.elves.scheduler.thrift;
/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Generated;
import java.util.*;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-11-11")
public class SchedulerService {

  public interface Iface {

    public String dataTransport(Reinstruct reins) throws TException;

  }

  public interface AsyncIface {

    public void dataTransport(Reinstruct reins, AsyncMethodCallback resultHandler) throws TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public String dataTransport(Reinstruct reins) throws TException
    {
      send_dataTransport(reins);
      return recv_dataTransport();
    }

    public void send_dataTransport(Reinstruct reins) throws TException
    {
      dataTransport_args args = new dataTransport_args();
      args.setReins(reins);
      sendBase("dataTransport", args);
    }

    public String recv_dataTransport() throws TException
    {
      dataTransport_result result = new dataTransport_result();
      receiveBase(result, "dataTransport");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "dataTransport failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void dataTransport(Reinstruct reins, AsyncMethodCallback resultHandler) throws TException {
      checkReady();
      dataTransport_call method_call = new dataTransport_call(reins, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class dataTransport_call extends org.apache.thrift.async.TAsyncMethodCall {
      private Reinstruct reins;
      public dataTransport_call(Reinstruct reins, AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.reins = reins;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("dataTransport", org.apache.thrift.protocol.TMessageType.CALL, 0));
        dataTransport_args args = new dataTransport_args();
        args.setReins(reins);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public String getResult() throws TException {
        if (getState() != State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_dataTransport();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("dataTransport", new dataTransport());
      return processMap;
    }

    public static class dataTransport<I extends Iface> extends org.apache.thrift.ProcessFunction<I, dataTransport_args> {
      public dataTransport() {
        super("dataTransport");
      }

      public dataTransport_args getEmptyArgsInstance() {
        return new dataTransport_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public dataTransport_result getResult(I iface, dataTransport_args args) throws TException {
        dataTransport_result result = new dataTransport_result();
        result.success = iface.dataTransport(args.reins);
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("dataTransport", new dataTransport());
      return processMap;
    }

    public static class dataTransport<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, dataTransport_args, String> {
      public dataTransport() {
        super("dataTransport");
      }

      public dataTransport_args getEmptyArgsInstance() {
        return new dataTransport_args();
      }

      public AsyncMethodCallback<String> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<String>() {
          public void onComplete(String o) {
            dataTransport_result result = new dataTransport_result();
            result.success = o;
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            dataTransport_result result = new dataTransport_result();
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, dataTransport_args args, AsyncMethodCallback<String> resultHandler) throws TException {
        iface.dataTransport(args.reins,resultHandler);
      }
    }

  }

  public static class dataTransport_args implements org.apache.thrift.TBase<dataTransport_args, dataTransport_args._Fields>, java.io.Serializable, Cloneable, Comparable<dataTransport_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("dataTransport_args");

    private static final org.apache.thrift.protocol.TField REINS_FIELD_DESC = new org.apache.thrift.protocol.TField("reins", org.apache.thrift.protocol.TType.STRUCT, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new dataTransport_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new dataTransport_argsTupleSchemeFactory());
    }

    public Reinstruct reins; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      REINS((short)1, "reins");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // REINS
            return REINS;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.REINS, new org.apache.thrift.meta_data.FieldMetaData("reins", org.apache.thrift.TFieldRequirementType.DEFAULT,
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Reinstruct.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(dataTransport_args.class, metaDataMap);
    }

    public dataTransport_args() {
    }

    public dataTransport_args(
      Reinstruct reins)
    {
      this();
      this.reins = reins;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public dataTransport_args(dataTransport_args other) {
      if (other.isSetReins()) {
        this.reins = new Reinstruct(other.reins);
      }
    }

    public dataTransport_args deepCopy() {
      return new dataTransport_args(this);
    }

    @Override
    public void clear() {
      this.reins = null;
    }

    public Reinstruct getReins() {
      return this.reins;
    }

    public dataTransport_args setReins(Reinstruct reins) {
      this.reins = reins;
      return this;
    }

    public void unsetReins() {
      this.reins = null;
    }

    /** Returns true if field reins is set (has been assigned a value) and false otherwise */
    public boolean isSetReins() {
      return this.reins != null;
    }

    public void setReinsIsSet(boolean value) {
      if (!value) {
        this.reins = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case REINS:
        if (value == null) {
          unsetReins();
        } else {
          setReins((Reinstruct)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case REINS:
        return getReins();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case REINS:
        return isSetReins();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof dataTransport_args)
        return this.equals((dataTransport_args)that);
      return false;
    }

    public boolean equals(dataTransport_args that) {
      if (that == null)
        return false;

      boolean this_present_reins = true && this.isSetReins();
      boolean that_present_reins = true && that.isSetReins();
      if (this_present_reins || that_present_reins) {
        if (!(this_present_reins && that_present_reins))
          return false;
        if (!this.reins.equals(that.reins))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_reins = true && (isSetReins());
      list.add(present_reins);
      if (present_reins)
        list.add(reins);

      return list.hashCode();
    }

    @Override
    public int compareTo(dataTransport_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetReins()).compareTo(other.isSetReins());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetReins()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.reins, other.reins);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("dataTransport_args(");
      boolean first = true;

      sb.append("reins:");
      if (this.reins == null) {
        sb.append("null");
      } else {
        sb.append(this.reins);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
      // check for sub-struct validity
      if (reins != null) {
        reins.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class dataTransport_argsStandardSchemeFactory implements SchemeFactory {
      public dataTransport_argsStandardScheme getScheme() {
        return new dataTransport_argsStandardScheme();
      }
    }

    private static class dataTransport_argsStandardScheme extends StandardScheme<dataTransport_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, dataTransport_args struct) throws TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
            break;
          }
          switch (schemeField.id) {
            case 1: // REINS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.reins = new Reinstruct();
                struct.reins.read(iprot);
                struct.setReinsIsSet(true);
              } else {
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, dataTransport_args struct) throws TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.reins != null) {
          oprot.writeFieldBegin(REINS_FIELD_DESC);
          struct.reins.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class dataTransport_argsTupleSchemeFactory implements SchemeFactory {
      public dataTransport_argsTupleScheme getScheme() {
        return new dataTransport_argsTupleScheme();
      }
    }

    private static class dataTransport_argsTupleScheme extends TupleScheme<dataTransport_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, dataTransport_args struct) throws TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetReins()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetReins()) {
          struct.reins.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, dataTransport_args struct) throws TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.reins = new Reinstruct();
          struct.reins.read(iprot);
          struct.setReinsIsSet(true);
        }
      }
    }

  }

  public static class dataTransport_result implements org.apache.thrift.TBase<dataTransport_result, dataTransport_result._Fields>, java.io.Serializable, Cloneable, Comparable<dataTransport_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("dataTransport_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRING, (short)0);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new dataTransport_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new dataTransport_resultTupleSchemeFactory());
    }

    public String success; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT,
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(dataTransport_result.class, metaDataMap);
    }

    public dataTransport_result() {
    }

    public dataTransport_result(
      String success)
    {
      this();
      this.success = success;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public dataTransport_result(dataTransport_result other) {
      if (other.isSetSuccess()) {
        this.success = other.success;
      }
    }

    public dataTransport_result deepCopy() {
      return new dataTransport_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
    }

    public String getSuccess() {
      return this.success;
    }

    public dataTransport_result setSuccess(String success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((String)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof dataTransport_result)
        return this.equals((dataTransport_result)that);
      return false;
    }

    public boolean equals(dataTransport_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_success = true && (isSetSuccess());
      list.add(present_success);
      if (present_success)
        list.add(success);

      return list.hashCode();
    }

    @Override
    public int compareTo(dataTransport_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("dataTransport_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class dataTransport_resultStandardSchemeFactory implements SchemeFactory {
      public dataTransport_resultStandardScheme getScheme() {
        return new dataTransport_resultStandardScheme();
      }
    }

    private static class dataTransport_resultStandardScheme extends StandardScheme<dataTransport_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, dataTransport_result struct) throws TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.success = iprot.readString();
                struct.setSuccessIsSet(true);
              } else {
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, dataTransport_result struct) throws TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          oprot.writeString(struct.success);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class dataTransport_resultTupleSchemeFactory implements SchemeFactory {
      public dataTransport_resultTupleScheme getScheme() {
        return new dataTransport_resultTupleScheme();
      }
    }

    private static class dataTransport_resultTupleScheme extends TupleScheme<dataTransport_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, dataTransport_result struct) throws TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetSuccess()) {
          oprot.writeString(struct.success);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, dataTransport_result struct) throws TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.success = iprot.readString();
          struct.setSuccessIsSet(true);
        }
      }
    }

  }

}
