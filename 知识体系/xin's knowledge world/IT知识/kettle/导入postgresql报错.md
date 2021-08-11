[[IT_TAG]] #IT 

invalid byte sequence for encoding "UTF8": 0x00

   nvalid byte sequence for encoding "UTF8": 0x00（注意：若不是0x00则很可能是字符集设置有误），是PostgreSQL独有的错误信息，直接原因是varchar型的字段或变量不接受含有'\0'（也即数值0x00、UTF编码'\u0000'）的字符串 。官方给出的解决方法：事先去掉字符串中的'\0'，例如在Java代码中使用str.replaceAll('\u0000', '')，貌似这是目前唯一可行的方法。