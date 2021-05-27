1. 镜像已经被构建成功
2. 将数据当如/app/nfs/metadata 中
3. 在Default中，导入yaml，拷贝下列数据

以DEV为例

```yaml
apiVersion: batch/v1
kind: Job
metadata:
  name: pyjob-metadata-load-dev
spec:
  ttlSecondsAfterFinished: 100
  template:
    spec:
      containers:
        - name: pyjob-metadata-load-dev
          image: harbor-lab.fsg.inner/ml/pyjob_metadata_load:0.1
          imagePullPolicy: IfNotPresent
          args: ["-s", "DEV", "/metadata/DEV"]
          volumeMounts:
            - mountPath: /metadata
              name: v-nfx-metadata
      restartPolicy: Never
      volumes:
        - hostPath:
            path: /app/nfs/metadata
            type: ""
          name: v-nfx-metadata
  backoffLimit: 4
```





