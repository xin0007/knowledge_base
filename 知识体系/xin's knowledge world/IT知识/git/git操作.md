[[IT_TAG]] #IT 

1. git 命令
git branch -v 查看所在branch
git checkout master 切换到master branch


忽略.DS_store 文件

所以，我们只需要在对应的Git目录下，创建一个.gitignore文件，然后配置上.DS_Store即可，步骤如下:

1. 命令行下使用touch .gitignore创建.gitignore文件(已有的直接到第二步)
wdw:test wdw$ touch .gitignore
2. open .gitignore，输入.DS_Store 换行再输入*/.DS_Store

3. 
如果是项目做到一半才开始加入.gitignore,则需要在commit所有已经修改文件后，执行以下命令保证.gitignore开始生效。
git rm -r --cached .
git add .
git commit -m 'update .gitignore'


----

一、生成ssh key

1. ssh-keygen -t rsa -C "XXX"
2. ssh-agent -s
3. ssh-add /Users/mingxinli/.ssh/id_rsa

4. 打开 ~/.ssh/id_rsa
id_rsa.pub

5. 复制到Gitlab上面


二、git 初始化
cd existing_folder
git init
git remote add origin ssh://git@172.16.111.27:222/mingxin.li/itssc_operation_etl_kettle.git


三、git pull

 git pull origin

本地的当前分支自动与对应的origin主机”追踪分支”(remote-tracking branch)进行合并。
如果当前分支只有一个追踪分支，连远程主机名都可以省略。

四、 git push

git add .
git commit -m 'COMMIT'
git push -u origin master

五、 其他
git diff
比较两个版本的不同





