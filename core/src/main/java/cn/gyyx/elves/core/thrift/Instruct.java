package cn.gyyx.elves.core.thrift;
/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */

import org.apache.thrift.EncodingUtils;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import javax.annotation.Generated;
import java.util.*;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-11-11")
public class Instruct implements org.apache.thrift.TBase<Instruct, Instruct._Fields>, java.io.Serializable, Cloneable, Comparable<Instruct> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Instruct");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField IP_FIELD_DESC = new org.apache.thrift.protocol.TField("ip", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField MODE_FIELD_DESC = new org.apache.thrift.protocol.TField("mode", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField APP_FIELD_DESC = new org.apache.thrift.protocol.TField("app", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField FUNC_FIELD_DESC = new org.apache.thrift.protocol.TField("func", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField PARAM_FIELD_DESC = new org.apache.thrift.protocol.TField("param", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField TIMEOUT_FIELD_DESC = new org.apache.thrift.protocol.TField("timeout", org.apache.thrift.protocol.TType.I32, (short)8);
  private static final org.apache.thrift.protocol.TField PROXY_FIELD_DESC = new org.apache.thrift.protocol.TField("proxy", org.apache.thrift.protocol.TType.STRING, (short)9);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new InstructStandardSchemeFactory());
    schemes.put(TupleScheme.class, new InstructTupleSchemeFactory());
  }

  public String id; // required
  public String ip; // required
  public String type; // required
  public String mode; // required
  public String app; // required
  public String func; // required
  public String param; // required
  public int timeout; // required
  public String proxy; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    IP((short)2, "ip"),
    TYPE((short)3, "type"),
    MODE((short)4, "mode"),
    APP((short)5, "app"),
    FUNC((short)6, "func"),
    PARAM((short)7, "param"),
    TIMEOUT((short)8, "timeout"),
    PROXY((short)9, "proxy");

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
        case 1: // ID
          return ID;
        case 2: // IP
          return IP;
        case 3: // TYPE
          return TYPE;
        case 4: // MODE
          return MODE;
        case 5: // APP
          return APP;
        case 6: // FUNC
          return FUNC;
        case 7: // PARAM
          return PARAM;
        case 8: // TIMEOUT
          return TIMEOUT;
        case 9: // PROXY
          return PROXY;
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
  private static final int __TIMEOUT_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.IP, new org.apache.thrift.meta_data.FieldMetaData("ip", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MODE, new org.apache.thrift.meta_data.FieldMetaData("mode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.APP, new org.apache.thrift.meta_data.FieldMetaData("app", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FUNC, new org.apache.thrift.meta_data.FieldMetaData("func", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARAM, new org.apache.thrift.meta_data.FieldMetaData("param", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIMEOUT, new org.apache.thrift.meta_data.FieldMetaData("timeout", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PROXY, new org.apache.thrift.meta_data.FieldMetaData("proxy", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Instruct.class, metaDataMap);
  }

  public Instruct() {
  }

  public Instruct(
    String id,
    String ip,
    String type,
    String mode,
    String app,
    String func,
    String param,
    int timeout,
    String proxy)
  {
    this();
    this.id = id;
    this.ip = ip;
    this.type = type;
    this.mode = mode;
    this.app = app;
    this.func = func;
    this.param = param;
    this.timeout = timeout;
    setTimeoutIsSet(true);
    this.proxy = proxy;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Instruct(Instruct other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetIp()) {
      this.ip = other.ip;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetMode()) {
      this.mode = other.mode;
    }
    if (other.isSetApp()) {
      this.app = other.app;
    }
    if (other.isSetFunc()) {
      this.func = other.func;
    }
    if (other.isSetParam()) {
      this.param = other.param;
    }
    this.timeout = other.timeout;
    if (other.isSetProxy()) {
      this.proxy = other.proxy;
    }
  }

  public Instruct deepCopy() {
    return new Instruct(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.ip = null;
    this.type = null;
    this.mode = null;
    this.app = null;
    this.func = null;
    this.param = null;
    setTimeoutIsSet(false);
    this.timeout = 0;
    this.proxy = null;
  }

  public String getId() {
    return this.id;
  }

  public Instruct setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public String getIp() {
    return this.ip;
  }

  public Instruct setIp(String ip) {
    this.ip = ip;
    return this;
  }

  public void unsetIp() {
    this.ip = null;
  }

  /** Returns true if field ip is set (has been assigned a value) and false otherwise */
  public boolean isSetIp() {
    return this.ip != null;
  }

  public void setIpIsSet(boolean value) {
    if (!value) {
      this.ip = null;
    }
  }

  public String getType() {
    return this.type;
  }

  public Instruct setType(String type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public String getMode() {
    return this.mode;
  }

  public Instruct setMode(String mode) {
    this.mode = mode;
    return this;
  }

  public void unsetMode() {
    this.mode = null;
  }

  /** Returns true if field mode is set (has been assigned a value) and false otherwise */
  public boolean isSetMode() {
    return this.mode != null;
  }

  public void setModeIsSet(boolean value) {
    if (!value) {
      this.mode = null;
    }
  }

  public String getApp() {
    return this.app;
  }

  public Instruct setApp(String app) {
    this.app = app;
    return this;
  }

  public void unsetApp() {
    this.app = null;
  }

  /** Returns true if field app is set (has been assigned a value) and false otherwise */
  public boolean isSetApp() {
    return this.app != null;
  }

  public void setAppIsSet(boolean value) {
    if (!value) {
      this.app = null;
    }
  }

  public String getFunc() {
    return this.func;
  }

  public Instruct setFunc(String func) {
    this.func = func;
    return this;
  }

  public void unsetFunc() {
    this.func = null;
  }

  /** Returns true if field func is set (has been assigned a value) and false otherwise */
  public boolean isSetFunc() {
    return this.func != null;
  }

  public void setFuncIsSet(boolean value) {
    if (!value) {
      this.func = null;
    }
  }

  public String getParam() {
    return this.param;
  }

  public Instruct setParam(String param) {
    this.param = param;
    return this;
  }

  public void unsetParam() {
    this.param = null;
  }

  /** Returns true if field param is set (has been assigned a value) and false otherwise */
  public boolean isSetParam() {
    return this.param != null;
  }

  public void setParamIsSet(boolean value) {
    if (!value) {
      this.param = null;
    }
  }

  public int getTimeout() {
    return this.timeout;
  }

  public Instruct setTimeout(int timeout) {
    this.timeout = timeout;
    setTimeoutIsSet(true);
    return this;
  }

  public void unsetTimeout() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIMEOUT_ISSET_ID);
  }

  /** Returns true if field timeout is set (has been assigned a value) and false otherwise */
  public boolean isSetTimeout() {
    return EncodingUtils.testBit(__isset_bitfield, __TIMEOUT_ISSET_ID);
  }

  public void setTimeoutIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIMEOUT_ISSET_ID, value);
  }

  public String getProxy() {
    return this.proxy;
  }

  public Instruct setProxy(String proxy) {
    this.proxy = proxy;
    return this;
  }

  public void unsetProxy() {
    this.proxy = null;
  }

  /** Returns true if field proxy is set (has been assigned a value) and false otherwise */
  public boolean isSetProxy() {
    return this.proxy != null;
  }

  public void setProxyIsSet(boolean value) {
    if (!value) {
      this.proxy = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case IP:
      if (value == null) {
        unsetIp();
      } else {
        setIp((String)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((String)value);
      }
      break;

    case MODE:
      if (value == null) {
        unsetMode();
      } else {
        setMode((String)value);
      }
      break;

    case APP:
      if (value == null) {
        unsetApp();
      } else {
        setApp((String)value);
      }
      break;

    case FUNC:
      if (value == null) {
        unsetFunc();
      } else {
        setFunc((String)value);
      }
      break;

    case PARAM:
      if (value == null) {
        unsetParam();
      } else {
        setParam((String)value);
      }
      break;

    case TIMEOUT:
      if (value == null) {
        unsetTimeout();
      } else {
        setTimeout((Integer)value);
      }
      break;

    case PROXY:
      if (value == null) {
        unsetProxy();
      } else {
        setProxy((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case IP:
      return getIp();

    case TYPE:
      return getType();

    case MODE:
      return getMode();

    case APP:
      return getApp();

    case FUNC:
      return getFunc();

    case PARAM:
      return getParam();

    case TIMEOUT:
      return getTimeout();

    case PROXY:
      return getProxy();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case IP:
      return isSetIp();
    case TYPE:
      return isSetType();
    case MODE:
      return isSetMode();
    case APP:
      return isSetApp();
    case FUNC:
      return isSetFunc();
    case PARAM:
      return isSetParam();
    case TIMEOUT:
      return isSetTimeout();
    case PROXY:
      return isSetProxy();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Instruct)
      return this.equals((Instruct)that);
    return false;
  }

  public boolean equals(Instruct that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_ip = true && this.isSetIp();
    boolean that_present_ip = true && that.isSetIp();
    if (this_present_ip || that_present_ip) {
      if (!(this_present_ip && that_present_ip))
        return false;
      if (!this.ip.equals(that.ip))
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_mode = true && this.isSetMode();
    boolean that_present_mode = true && that.isSetMode();
    if (this_present_mode || that_present_mode) {
      if (!(this_present_mode && that_present_mode))
        return false;
      if (!this.mode.equals(that.mode))
        return false;
    }

    boolean this_present_app = true && this.isSetApp();
    boolean that_present_app = true && that.isSetApp();
    if (this_present_app || that_present_app) {
      if (!(this_present_app && that_present_app))
        return false;
      if (!this.app.equals(that.app))
        return false;
    }

    boolean this_present_func = true && this.isSetFunc();
    boolean that_present_func = true && that.isSetFunc();
    if (this_present_func || that_present_func) {
      if (!(this_present_func && that_present_func))
        return false;
      if (!this.func.equals(that.func))
        return false;
    }

    boolean this_present_param = true && this.isSetParam();
    boolean that_present_param = true && that.isSetParam();
    if (this_present_param || that_present_param) {
      if (!(this_present_param && that_present_param))
        return false;
      if (!this.param.equals(that.param))
        return false;
    }

    boolean this_present_timeout = true;
    boolean that_present_timeout = true;
    if (this_present_timeout || that_present_timeout) {
      if (!(this_present_timeout && that_present_timeout))
        return false;
      if (this.timeout != that.timeout)
        return false;
    }

    boolean this_present_proxy = true && this.isSetProxy();
    boolean that_present_proxy = true && that.isSetProxy();
    if (this_present_proxy || that_present_proxy) {
      if (!(this_present_proxy && that_present_proxy))
        return false;
      if (!this.proxy.equals(that.proxy))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true && (isSetId());
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_ip = true && (isSetIp());
    list.add(present_ip);
    if (present_ip)
      list.add(ip);

    boolean present_type = true && (isSetType());
    list.add(present_type);
    if (present_type)
      list.add(type);

    boolean present_mode = true && (isSetMode());
    list.add(present_mode);
    if (present_mode)
      list.add(mode);

    boolean present_app = true && (isSetApp());
    list.add(present_app);
    if (present_app)
      list.add(app);

    boolean present_func = true && (isSetFunc());
    list.add(present_func);
    if (present_func)
      list.add(func);

    boolean present_param = true && (isSetParam());
    list.add(present_param);
    if (present_param)
      list.add(param);

    boolean present_timeout = true;
    list.add(present_timeout);
    if (present_timeout)
      list.add(timeout);

    boolean present_proxy = true && (isSetProxy());
    list.add(present_proxy);
    if (present_proxy)
      list.add(proxy);

    return list.hashCode();
  }

  @Override
  public int compareTo(Instruct other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIp()).compareTo(other.isSetIp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ip, other.ip);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMode()).compareTo(other.isSetMode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mode, other.mode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetApp()).compareTo(other.isSetApp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetApp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.app, other.app);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFunc()).compareTo(other.isSetFunc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFunc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.func, other.func);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetParam()).compareTo(other.isSetParam());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParam()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.param, other.param);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTimeout()).compareTo(other.isSetTimeout());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimeout()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timeout, other.timeout);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProxy()).compareTo(other.isSetProxy());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProxy()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.proxy, other.proxy);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Instruct(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ip:");
    if (this.ip == null) {
      sb.append("null");
    } else {
      sb.append(this.ip);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("mode:");
    if (this.mode == null) {
      sb.append("null");
    } else {
      sb.append(this.mode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("app:");
    if (this.app == null) {
      sb.append("null");
    } else {
      sb.append(this.app);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("func:");
    if (this.func == null) {
      sb.append("null");
    } else {
      sb.append(this.func);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("param:");
    if (this.param == null) {
      sb.append("null");
    } else {
      sb.append(this.param);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("timeout:");
    sb.append(this.timeout);
    first = false;
    if (!first) sb.append(", ");
    sb.append("proxy:");
    if (this.proxy == null) {
      sb.append("null");
    } else {
      sb.append(this.proxy);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class InstructStandardSchemeFactory implements SchemeFactory {
    public InstructStandardScheme getScheme() {
      return new InstructStandardScheme();
    }
  }

  private static class InstructStandardScheme extends StandardScheme<Instruct> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Instruct struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // IP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ip = iprot.readString();
              struct.setIpIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.type = iprot.readString();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // MODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.mode = iprot.readString();
              struct.setModeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // APP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.app = iprot.readString();
              struct.setAppIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // FUNC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.func = iprot.readString();
              struct.setFuncIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // PARAM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.param = iprot.readString();
              struct.setParamIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // TIMEOUT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.timeout = iprot.readI32();
              struct.setTimeoutIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 9: // PROXY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.proxy = iprot.readString();
              struct.setProxyIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Instruct struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.ip != null) {
        oprot.writeFieldBegin(IP_FIELD_DESC);
        oprot.writeString(struct.ip);
        oprot.writeFieldEnd();
      }
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeString(struct.type);
        oprot.writeFieldEnd();
      }
      if (struct.mode != null) {
        oprot.writeFieldBegin(MODE_FIELD_DESC);
        oprot.writeString(struct.mode);
        oprot.writeFieldEnd();
      }
      if (struct.app != null) {
        oprot.writeFieldBegin(APP_FIELD_DESC);
        oprot.writeString(struct.app);
        oprot.writeFieldEnd();
      }
      if (struct.func != null) {
        oprot.writeFieldBegin(FUNC_FIELD_DESC);
        oprot.writeString(struct.func);
        oprot.writeFieldEnd();
      }
      if (struct.param != null) {
        oprot.writeFieldBegin(PARAM_FIELD_DESC);
        oprot.writeString(struct.param);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMEOUT_FIELD_DESC);
      oprot.writeI32(struct.timeout);
      oprot.writeFieldEnd();
      if (struct.proxy != null) {
        oprot.writeFieldBegin(PROXY_FIELD_DESC);
        oprot.writeString(struct.proxy);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class InstructTupleSchemeFactory implements SchemeFactory {
    public InstructTupleScheme getScheme() {
      return new InstructTupleScheme();
    }
  }

  private static class InstructTupleScheme extends TupleScheme<Instruct> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Instruct struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetIp()) {
        optionals.set(1);
      }
      if (struct.isSetType()) {
        optionals.set(2);
      }
      if (struct.isSetMode()) {
        optionals.set(3);
      }
      if (struct.isSetApp()) {
        optionals.set(4);
      }
      if (struct.isSetFunc()) {
        optionals.set(5);
      }
      if (struct.isSetParam()) {
        optionals.set(6);
      }
      if (struct.isSetTimeout()) {
        optionals.set(7);
      }
      if (struct.isSetProxy()) {
        optionals.set(8);
      }
      oprot.writeBitSet(optionals, 9);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetIp()) {
        oprot.writeString(struct.ip);
      }
      if (struct.isSetType()) {
        oprot.writeString(struct.type);
      }
      if (struct.isSetMode()) {
        oprot.writeString(struct.mode);
      }
      if (struct.isSetApp()) {
        oprot.writeString(struct.app);
      }
      if (struct.isSetFunc()) {
        oprot.writeString(struct.func);
      }
      if (struct.isSetParam()) {
        oprot.writeString(struct.param);
      }
      if (struct.isSetTimeout()) {
        oprot.writeI32(struct.timeout);
      }
      if (struct.isSetProxy()) {
        oprot.writeString(struct.proxy);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Instruct struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(9);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.ip = iprot.readString();
        struct.setIpIsSet(true);
      }
      if (incoming.get(2)) {
        struct.type = iprot.readString();
        struct.setTypeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.mode = iprot.readString();
        struct.setModeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.app = iprot.readString();
        struct.setAppIsSet(true);
      }
      if (incoming.get(5)) {
        struct.func = iprot.readString();
        struct.setFuncIsSet(true);
      }
      if (incoming.get(6)) {
        struct.param = iprot.readString();
        struct.setParamIsSet(true);
      }
      if (incoming.get(7)) {
        struct.timeout = iprot.readI32();
        struct.setTimeoutIsSet(true);
      }
      if (incoming.get(8)) {
        struct.proxy = iprot.readString();
        struct.setProxyIsSet(true);
      }
    }
  }

}

