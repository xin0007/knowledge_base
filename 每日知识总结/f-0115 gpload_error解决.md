1. 写入路径，或者调用路径无权限

chmod 777 file 

2. permission denied: no privilege to create a readable gpfdist(s) external table

ALTER role itssc_operation CREATEEXTTABLE (type='readable');

3. hostname cannot be resolved

去mpp的服务器，配置 /etc/hosts文件 ip+hostname

4. u001c无法识别

重新拷贝/app/data-integration/plugins/kettle-gpload-plugin/kettle-gpload-plugin-core-9.0.0.0-423.jar 文件

5. 如果遇到gpload.py no file

拷贝进来，stopCarte,

然后在/app/data-integration中执行a

source /usr/local/greenplum-db-clients/greenplum_clients_path.sh && ( ./carte.sh carte-config-master.xml > logs/carte.log 2>&1 &)

重启

