[[DB_TAG]] #db 

Oracle JDBC的三种连接方式:
Service name, SID, TNSNname

1.SERVICE_NAME方式：jdbc:oracle:thin:@//<host>:<port>/<SERVICE_NAME>  

2.SID连接方式：jdbc:oracle:thin:@<host>:<port>:<SID> 
                    或：jdbc:oracle:thin:@<host>:<port>/<SID>

3.TNSName连接方式：jdbc:oracle:thin:@<TNSName>

SERVICE_NAME和SID的比较：
    SID是对内的，是实例级别的一个名字，用来内部之间称呼用。
    SERVICE_NAME是对外的，是数据库级别的一个名字，用来告诉外面的人，我数据库叫"SERVICE_NAME"。