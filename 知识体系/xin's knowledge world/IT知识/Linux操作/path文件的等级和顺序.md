[[IT_TAG]] #IT 


* rc - run command

用户级（/.bash.rc）
系统级（/etc/profile）

开机时的执行顺序为系统级别，再到用户级别，所以用户级别的文件会覆盖系统级别



* /etc/profile
	The systemwide initialization file, executed for login shells
* /etc/bash.bash_logout
	The systemwide login shell cleanup file, executed when a login shell exits
* ~/.bash_profile
	The personal initialization file, executed for login shells
* ~/.bashrc
	The individual per-interactive-shell startup file
* ~/.bash_logout
	The individual login shell cleanup file, executed when a login shell exits

