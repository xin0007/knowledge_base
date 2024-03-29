[[IT_TAG]] #IT 

```yml
apiVersion: batch/v1
kind: Job
metadata:
  name: datax-speed-test-finc
  labels:
    app.kubernetes.io/name: datax
spec:
  backoffLimit: 6
  completions: 1
  parallelism: 1
  template:
    metadata:      
      labels:        
        job-name: datax-speed-test-finc
    spec:
      containers:
      - args:
        - datax
        - start
        - speed_test_finc.json
        image: harbor-lab.fsg.inner:443/dc/datax:gpdbwrite
        imagePullPolicy: IfNotPresent
        name: datax-251-datamodify        
        securityContext:
          allowPrivilegeEscalation: false
          capabilities: {}
          privileged: false
          readOnlyRootFilesystem: false
          runAsNonRoot: false
        stdin: true
        terminationMessagePath: /dev/termination-log
        terminationMessagePolicy: File
        tty: true
        volumeMounts:
        - mountPath: /job/json
          name: v-nfx-dataxjson
        - mountPath: /data
          name: v-nfx-dataxdata
        - mountPath: /datax/hook
          name: v-nfx-dataxhook
      dnsConfig: {}
      dnsPolicy: ClusterFirst
      restartPolicy: Never
      schedulerName: default-scheduler
      securityContext: {}
      terminationGracePeriodSeconds: 30
      volumes:
      - hostPath:
          path: /app/nfs/datax
          type: ""
        name: v-nfx-dataxjson
      - hostPath:
          path: /app/datax/data
          type: "DirectoryOrCreate"
        name: v-nfx-dataxdata
      - hostPath:
          path: /app/datax/hook
          type: "DirectoryOrCreate"
        name: v-nfx-dataxhook

```