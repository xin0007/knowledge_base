[[IT_TAG]] #IT 

如果软件显示文件 damaged
可以用一下command

```shell
xattr -r -d com.apple.quarantine path_to/XXX.app

This will remove the quarantine bit from the Archi application to allow it to run.
```